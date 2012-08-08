package me.eistee2.minebuilder;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class playerPlaceBlock implements Listener {

	//Get Instance for checking players
	playerJoinQuit playerList = playerJoinQuit.getInstance();
	
	//get instance from the class with the informations from the config
	ReadOut info = ReadOut.getInstance();
	
	//Counting exp
	expCalculator expCal = new expCalculator();
	
	
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
					event.getPlayer().giveExp(exp);
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
					event.getPlayer().giveExp(exp);
					expArray[playerID][info.getExpIndex(booleanID, blockInfo)] = replays;
					
				}
			}
		}
		
		//Give money
		if(info.getMoneyBoolean(booleanID)==true)
		{
			String blockInfo = info.checkMoneyID(booleanID, blockID, blockName);
			if(blockInfo != null)
			{
				String[] blockInfoSplit = blockInfo.split(":");
				//How many money you set
				int replays = Integer.parseInt(blockInfoSplit[2]);
				int money = 0;
				//Check if the exp random list contains a random for exp , when not it will set the exp in the settings
				if(info.checkMoneyID(5, 0, blockInfoSplit[1]) != null)
				{
					String[] randomSplit = info.checkMoneyID(5, 0, blockInfoSplit[1]).split(":");
					int between1 = Integer.parseInt(randomSplit[1]);
					int between2 = Integer.parseInt(randomSplit[2]);
					Random ran = new Random();
					money = ran.nextInt((between2 + 1) - between1) + between1;
				}else{
					money = Integer.parseInt(blockInfoSplit[1]);
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
					moneyArray[playerID][info.getMoneyIndex(booleanID, blockInfo)] = replays;
				}
		    }
		}
		
	}
	
	
}
