package me.eistee2.minebuilder;

import java.io.File;
import java.io.InputStream;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import com.avaje.ebean.EbeanServer;

public class playerPlaceBlock implements Listener, Plugin {

	//Get Instance for checking players
	playerJoinQuit playerList = playerJoinQuit.getInstance();
	
	//get instance from the class with the informations from the config
	ReadOut info = ReadOut.getInstance();
	
	//Counting exp
	expCalculator expCal = new expCalculator();
	
	//get instance for saving blocks
	BlockSaver save = BlockSaver.getInstance();
	
	Plugin wg = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	Plugin ma = Bukkit.getServer().getPluginManager().getPlugin("MobArena");
	
    int[][] expArray;
    int[][] moneyArray;
     
	public void iniInts()
	{
		moneyArray = new int[info.getMaxPlayer()][200];
		expArray = new int[info.getMaxPlayer()][200];
		for(int iniplayers = 0; iniplayers < info.getMaxPlayer(); iniplayers++)
		{
			for(int inislots = 0; inislots < 200; inislots++)
			{
				moneyArray[iniplayers][inislots] = -1;
				expArray[iniplayers][inislots] = -1;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	private void PlayerBlockPlace(BlockPlaceEvent event)
	{
		MineBuilder Miner = new MineBuilder();
		int booleanID = 2;
		//Check if you are in a protectet area (Spawn area, WorldGuard and MobArena)
		if(event.getPlayer().getLocation().distanceSquared(event.getPlayer().getWorld().getSpawnLocation()) < Math.pow(Bukkit.getServer().getSpawnRadius(), 2.2))
		{
			return;
		}
		
		if(wg != null)
		{
			if(wg.isEnabled() && Miner.getWorldGuard().canBuild(event.getPlayer(), event.getBlock().getLocation())==false)
			{
				return;
			}
		}
		if(ma != null)
		{
			if(ma.isEnabled() && Miner.maHandler.inRegion(event.getPlayer().getLocation())==true)
			{
				return;
			}
		}
		if(info.getMaxBlockSaved() != 0  && !(event.getBlock().getType().name() == "CROPS") && !(event.getBlock().getType().name() == "MELON_STEM")  && !(event.getBlock().getType().name() == "PUMPKIN_STEM") )
		{
			save.addLocation(event.getBlock().getLocation());
		}
		
		if(info.getPermissionStat() == true)
		{
			if(!event.getPlayer().hasPermission("minebuilder.vip")){
				return;
			}
		}
		//Get BlockID/name and player name
		int blockID = event.getBlock().getTypeId();
		String blockName = event.getBlock().getType().toString();
		int playerID = playerList.getPlayerIndex(event.getPlayer().getName());
		//Give exp
		if(info.getExpBoolean(booleanID)==true)
		{
			String blockInfo = info.checkExpID(booleanID, blockID, blockName);
			if(blockInfo != null)
			{
				String[] blockInfoSplit = blockInfo.split(":");
				//How many exp you set
				int replays = Integer.parseInt(blockInfoSplit[2]);
				int exp = 0;
				//Check if the exp random list contains a random for exp , when not it will set the exp in the settings
				if(info.checkExpID(5, 0, blockInfoSplit[1]) != null)
				{
					String[] randomSplit = info.checkExpID(5, 0, blockInfoSplit[1]).split(":");
					int between1 = Integer.parseInt(randomSplit[1]);
					int between2 = Integer.parseInt(randomSplit[2]);
					Random ran = new Random();
	                exp = ran.nextInt((between2 + 1) - between1) + between1;
				}else{
					exp = Integer.parseInt(blockInfoSplit[1]);
				}
				if(replays == 1)
				{
					
					exp = expCal.CalculateExp(event.getPlayer().getLevel(), exp, event.getPlayer().getName());
					if(info.getSpawnOrbs(2) == true)
					{
						Location expSpawn = event.getBlock().getLocation().add(0, 1, 0);
						for(int amount = 0 ; amount < exp;amount++)
						{
						  ExperienceOrb orb = event.getPlayer().getWorld().spawn(expSpawn, ExperienceOrb.class);
						  orb.setFallDistance(-0.01F);
						  orb.setExperience(1);
						}
					}
					else
					{
						event.getPlayer().giveExp(exp);
					} 
				}
				else if(expArray[playerID][info.getExpIndex(booleanID, blockInfo)] == -1)
				{
					expArray[playerID][info.getExpIndex(booleanID, blockInfo)] = replays-1;
				}
				else if(expArray[playerID][info.getExpIndex(booleanID, blockInfo)] > 0)
				{
					expArray[playerID][info.getExpIndex(booleanID, blockInfo)] -= 1;
				}
				
				if(expArray[playerID][info.getExpIndex(booleanID, blockInfo)] == 0)
				{
					exp = expCal.CalculateExp(event.getPlayer().getLevel(), exp, event.getPlayer().getName());
					if(info.getSpawnOrbs(2) == true)
					{
						Location expSpawn = event.getBlock().getLocation().add(0, 1, 0);
						for(int amount = 0 ; amount < exp;amount++)
						{
						  ExperienceOrb orb = event.getPlayer().getWorld().spawn(expSpawn, ExperienceOrb.class);
						  orb.setFallDistance(-0.01F);
						  orb.setExperience(1);
						}
					}
					else
					{
						event.getPlayer().giveExp(exp);
					}
					expArray[playerID][info.getExpIndex(booleanID, blockInfo)] = replays;
					
				}
			}
		}
		//Give money
		if(info.getMoneyBoolean(booleanID)==true && Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
		{
			String blockInfo = info.checkMoneyID(booleanID, blockID, blockName);
			if(blockInfo != null)
			{
				String[] blockInfoSplit = blockInfo.split(":");
				//How many money you set
				int replays = Integer.parseInt(blockInfoSplit[2]);
				float money = 0;
				//Check if the exp random list contains a random for exp , when not it will set the exp in the settings
				if(info.checkMoneyID(5, 0, blockInfoSplit[1]) != null)
				{
					String[] randomSplit = info.checkMoneyID(5, 0, blockInfoSplit[1]).split(":");
					int between1 = Integer.parseInt(randomSplit[1]);
					int between2 = Integer.parseInt(randomSplit[2]);
					Random ran = new Random();
					money = ran.nextInt((between2 + 1) - between1) + between1;
				}else{
					money = Float.parseFloat(blockInfoSplit[1]);
				}
				if(replays == 1)
				{
					if(MineBuilder.economy.hasAccount(event.getPlayer().getName()))
					{
						MineBuilder.economy.depositPlayer(event.getPlayer().getName(), money);
					}
				}
				else if(moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] == -1)
				{
					moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] = replays-1;
				}
				else if(moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] > 0)
				{
					moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] -= 1;
				}
				
				if(moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] == 0)
				{
					if(MineBuilder.economy.hasAccount(event.getPlayer().getName()))
					{
						MineBuilder.economy.depositPlayer(event.getPlayer().getName(), money);
					}
				}
		    }
		}
		if(event.getBlock().getType().name().equalsIgnoreCase("SUGAR_CANE_BLOCK"))
		{
			final Location sugarblock = event.getBlock().getLocation();
	      Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {

       	   public void run() 
       	   {
        		 save.checkLocation(sugarblock);
       	   }
       	}, 60);
	   }
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileConfiguration getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDataFolder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EbeanServer getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginDescriptionFile getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginLoader getPluginLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getResource(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNaggable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reloadConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDefaultConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveResource(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNaggable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
