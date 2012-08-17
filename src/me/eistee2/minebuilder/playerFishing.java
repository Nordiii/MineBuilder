package me.eistee2.minebuilder;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.Plugin;

public class playerFishing implements Listener {

	
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
		moneyArray = new int[info.getMaxPlayer()][1];
		expArray = new int[info.getMaxPlayer()][1];
		for(int iniplayers = 0; iniplayers < info.getMaxPlayer(); iniplayers++)
		{
			for(int inislots = 0; inislots < 1; inislots++)
			{
				moneyArray[iniplayers][inislots] = -1;
				expArray[iniplayers][inislots] = -1;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	private void PlayerFish(PlayerFishEvent event)
	{
		if(!(event.getState() == PlayerFishEvent.State.CAUGHT_FISH))
		{
			return;
		}
		
		
		int booleanID = 4;
		//Check if you are in a protectet area (Spawn area, WorldGuard and MobArena)
		if(info.getPermissionStat() == true)
		{
			if(!event.getPlayer().hasPermission("minebuilder.vip")){
				return;
			}
		}
		
		//Get BlockID/name and player name
		int blockID = 0;
		String blockName = "Fish";
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
				else if(expArray[playerID][0] == -1)
				{
					expArray[playerID][0] = replays-1;
				}
				else if(expArray[playerID][0] > 0)
				{
					expArray[playerID][0] -= 1;
				}
				
				if(expArray[playerID][0] == 0)
				{
					exp = expCal.CalculateExp(event.getPlayer().getLevel(), exp, event.getPlayer().getName());
					event.getPlayer().giveExp(exp);
					expArray[playerID][0] = replays;
					
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
				else if(moneyArray[playerID][0] == -1)
				{
					moneyArray[playerID][0] = replays-1;
				}
				else if(moneyArray[playerID][0] > 0)
				{
					moneyArray[playerID][0] -= 1;
				}
				
				if(moneyArray[playerID][0] == 0)
				{
					if(MineBuilder.economy.hasAccount(event.getPlayer().getName()))
					{
						MineBuilder.economy.depositPlayer(event.getPlayer().getName(), money);
					}
					moneyArray[playerID][0] = replays;
				}
		    }
		}
		
		
	}
	
	
}

