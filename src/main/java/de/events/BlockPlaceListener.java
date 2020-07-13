package de.events;

import de.config.ConfigDAO;
import de.minebuilder.Settings;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;
import java.util.stream.IntStream;

import static de.minebuilder.Settings.path.DISABLE_PLACE;

public class BlockPlaceListener extends AbsEvent implements IEvent {


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (Settings.getConfig().getBoolean(DISABLE_PLACE.path))
            return;
        if (Protection.getInstance().isProtected(e.getPlayer(), e.getBlock())) {
            return;
        }
        Optional<IEntity> b = ConfigDAO.getInstance().get(this.getClass(), new Block(e.getBlock()));
        b.ifPresent(block -> setExp(e, block));

        Protection.getInstance().addProtection(e.getBlockPlaced());

    }

    private void setExp(BlockPlaceEvent event, IEntity block) {

        int exp = PlayerDAO.getInstance().getExp(
                this.getClass(), event.getPlayer(), block);

        if (exp == 0)
            return;

        //Drop reminder of exp divided by ten
        event.getBlock().getWorld()
                .spawn(event.getBlock().getLocation().add(0, 1, 0), ExperienceOrb.class)
                .setExperience(exp % 10);


        //drop max 10 orbs with exp divided by 10
        int expDividedByTen = exp - (exp % 10) / 10;

        IntStream.range(0, 10).forEach(n ->
                event.getBlock().getWorld()
                        .spawn(event.getBlock().getLocation().add(0, 1, 0), ExperienceOrb.class)
                        .setExperience(expDividedByTen)
        );
    }
}
