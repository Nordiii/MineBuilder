package me.eistee2.minebuilder;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerJoinQuit implements Listener {
private static playerJoinQuit instance = null;

public static playerJoinQuit getInstance()
{
	if(instance == null)
	{
		instance = new playerJoinQuit();
	}
	
	return instance;
}



ArrayList<String> playerName = new ArrayList<String>(); //Contains all player which are online currently I get the index from them and save all blocks into an IntArray


@EventHandler(priority = EventPriority.NORMAL)
private void PlayerJoin(PlayerJoinEvent event)
{
	// If playerName contains "free Place" it will get replaced with the player name ,else the player name will get added
	if(playerName.contains("free Place"))
	{
		playerName.set(playerName.indexOf("free Place"), event.getPlayer().getName());
	}else
	{
		playerName.add(event.getPlayer().getName());
	}
	
}

@EventHandler(priority = EventPriority.NORMAL)
private void PlayerQuit(PlayerQuitEvent event)
{
	//When a Player Quit his name get replaced with "free Place"
	playerName.set(playerName.indexOf(event.getPlayer().getName()), "free Place");
}


//This will check if any player is online this is for the case that the server/plugin gets reloaded
public void PluginReloaded()
{
	playerName.removeAll(playerName);
	for(Player OnlinePlayer : Bukkit.getOnlinePlayers())
	{
		playerName.add(OnlinePlayer.getName());
	}
}


//Returns the index of the player for saving the blocks / mobs
public int getPlayerIndex(String playername)
{
	return playerName.indexOf(playername);
}


}
