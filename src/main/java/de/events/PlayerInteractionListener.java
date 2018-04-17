package de.events;

import de.models.PlayerDAO;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractionListener implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerInteractEvent e){
        if(e.getClickedBlock()!=null)
            if(PlayerDAO.getInstance().getBlockInfo(e.getPlayer()))
                e.getPlayer().sendMessage("Clicked on:\nID:"+e.getClickedBlock().getType().getId() +"\nMaterial:"+ e.getClickedBlock().getType());
    }
}
