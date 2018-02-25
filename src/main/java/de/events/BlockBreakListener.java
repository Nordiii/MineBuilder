package de.events;

import de.config.ConfigDAO;
import de.models.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements IEvent,Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        System.out.println(e.getBlock().getType().name());
        System.out.println(e.getBlock().getType().getId());
        System.out.println(e.getBlock().getData());
        System.out.println(ConfigDAO.getInstance().get(this.getClass(),new Block(e.getBlock())).isPresent());

    }

}
