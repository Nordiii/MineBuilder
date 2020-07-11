package de.events;

import de.config.ConfigDAO;
import de.minebuilder.Settings;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Optional;

import static de.minebuilder.Settings.path.DISABLE_KILL;
import static de.minebuilder.Settings.path.SET_DEFAULT_EXP_0_KILL;

public class EntityDeathListener extends AbsEvent implements IEvent {

    @EventHandler
    public void onEntityDead(EntityDeathEvent e) {
        if (Settings.getConfig().getBoolean(SET_DEFAULT_EXP_0_KILL.path))
            e.setDroppedExp(0);
        if (Settings.getConfig().getBoolean(DISABLE_KILL.path))
            return;

        Optional<IEntity> mob = ConfigDAO.getInstance().get(this.getClass(), new Block(e.getEntityType()));
        mob.ifPresent(mobKilled -> {
            Player killer = e.getEntity().getKiller();
            if (killer == null)
                return;
            e.setDroppedExp(PlayerDAO.getInstance().getExp(this.getClass(), killer, mobKilled));
        });

    }


}
