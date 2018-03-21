package de.events;

import de.protection.Protection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener extends AbsEvent implements IEvent,Listener {

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e){
        Protection.getInstance().addProtection(e.getBlockPlaced());
    }

}
