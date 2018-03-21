package de.events;

import de.models.PlayerDAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onBlockBreak(PlayerJoinEvent e){
        PlayerDAO.getInstance().addPlayer(e.getPlayer().getUniqueId());
    }

}
