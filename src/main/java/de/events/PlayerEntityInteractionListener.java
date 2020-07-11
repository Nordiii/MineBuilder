package de.events;

import de.models.PlayerDAO;
import de.models.PlayerWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;

public class PlayerEntityInteractionListener implements Listener {

    @EventHandler
    public void onPlayerEntityInteraction(PlayerInteractEntityEvent e) {
        PlayerDAO.getInstance().getBlockInfo(e.getPlayer())
                .ifPresent(player -> notify(e, player));
    }

    private void notify(PlayerInteractEntityEvent event, PlayerWrapper player) {
        String materialName = Objects.requireNonNull(event.getRightClicked().getType().name());
        player.setStoredMaterial(materialName);
        event.getPlayer().sendMessage("Stored the Mob: " + materialName);
    }
}
