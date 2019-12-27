package de.events;

import de.models.PlayerDAO;
import de.models.PlayerWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractionListener implements Listener {
    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null)
            return;

        PlayerDAO.getInstance().getBlockInfo(e.getPlayer())
                .ifPresent(player -> notifiy(e, player));

    }

    private void notifiy(PlayerInteractEvent event, PlayerWrapper player) {
        String materialName = event.getClickedBlock().getBlockData().getAsString().split(":")[1].split("\\[")[0];
        player.setStoredMaterial(materialName);
        event.getPlayer().sendMessage("Stored the Material: " + materialName);
    }
}
