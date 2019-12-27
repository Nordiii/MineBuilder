package de.events;

import de.config.ConfigDAO;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

public class BlockBreakListener extends AbsEvent implements IEvent {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setExpToDrop(0);
        Optional<IEntity> b = ConfigDAO.getInstance().get(this.getClass(), new Block(e.getBlock()));
        b.ifPresent(block -> setExp(e, block));
    }

    private void setExp(BlockBreakEvent event, IEntity block) {
        if (Protection.getInstance().isProtected(event.getBlock()))
            return;

        event.setExpToDrop(PlayerDAO.getInstance().getExp(this.getClass(), event.getPlayer(), block));
    }
}
