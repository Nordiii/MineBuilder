package de.events;

import de.config.ConfigDAO;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;

public class BlockPlaceListener extends AbsEvent implements IEvent,Listener {

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e)
    {
        Protection.getInstance().addProtection(e.getBlockPlaced());


        Optional<Block> b = ConfigDAO.getInstance().get(this.getClass(),new Block(e.getBlock()));

        if(!b.isPresent())
            return;

        if(Protection.getInstance().isProtected(e.getBlock()))
        {
            return;
        }

        e.getPlayer().giveExp(PlayerDAO.getInstance().getExp(this.getClass(),e.getPlayer().getUniqueId(),b.get())) ;

    }

}
