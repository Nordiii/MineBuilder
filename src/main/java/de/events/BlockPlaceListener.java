package de.events;

import de.config.ConfigDAO;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;
import java.util.stream.IntStream;

public class BlockPlaceListener extends AbsEvent implements IEvent,Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Protection.getInstance().addProtection(e.getBlockPlaced());


        Optional<Block> b = ConfigDAO.getInstance().get(this.getClass(),new Block(e.getBlock()));

        if(!b.isPresent())
            return;

//        if(Protection.getInstance().isProtected(e.getBlock()))
//        {
//            return;
//        }

        int exp = PlayerDAO.getInstance().getExp(
                this.getClass(),e.getPlayer(),b.get());
        if(exp > 0)
        IntStream.range(0,exp).forEach(n ->
            e.getBlock().getWorld()
                    .spawn(e.getBlock().getLocation(),ExperienceOrb.class)
                    .setExperience(exp)
        );


    }

}
