package de.events;

import de.config.ConfigDAO;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

public class BlockBreakListener extends AbsEvent implements IEvent,Listener  {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Optional<Block> b = ConfigDAO.getInstance().get(this.getClass(),new Block(e.getBlock()));

        if(!b.isPresent())
            return;

        if(Protection.getInstance().isProtected(e.getBlock()))
        {
            e.setExpToDrop(0);
            return;
        }

        e.setExpToDrop(PlayerDAO.getInstance().getExp(this.getClass(),e.getPlayer(),b.get()));

    }

}
