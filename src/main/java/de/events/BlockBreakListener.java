package de.events;

import de.config.ConfigDAO;
import de.minebuilder.Settings;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

import static de.minebuilder.Settings.path.DISABLE_BREAK;
import static de.minebuilder.Settings.path.SET_DEFAULT_EXP_0_BREAK;

public class BlockBreakListener extends AbsEvent implements IEvent {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if (Settings.getConfig().getBoolean(SET_DEFAULT_EXP_0_BREAK.path))
            e.setExpToDrop(0);
        if (Settings.getConfig().getBoolean(DISABLE_BREAK.path))
            return;
        Optional<IEntity> b = ConfigDAO.getInstance().get(this.getClass(), new Block(e.getBlock()));
        b.ifPresent(block -> setExp(e, block));
    }

    private void setExp(BlockBreakEvent event, IEntity block) {
        if (Protection.getInstance().isProtected(event.getPlayer(), event.getBlock()))
            return;

        event.setExpToDrop(PlayerDAO.getInstance().getExp(this.getClass(), event.getPlayer(), block));
    }
}
