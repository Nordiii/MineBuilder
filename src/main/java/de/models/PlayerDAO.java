package de.models;

import de.events.AbsEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class PlayerDAO {
    private static final PlayerDAO ourInstance = new PlayerDAO();

    public static PlayerDAO getInstance() {
        return ourInstance;
    }

    private PlayerDAO() {
    }

    private final Map<UUID, PlayerWrapper> players = new HashMap<>();

    public void updateItem(Class<? extends AbsEvent> event, Exp item) {
        players.forEach((k, p) -> p.updateItem(event, item));
    }

    public <T> int getExp(Class<T> config, Player player, Exp key) {
        PlayerWrapper playerWrapper = players.get(player.getUniqueId());

        return (playerWrapper != null) ? playerWrapper.getExp(config, key) : 0;
    }

    public Optional<PlayerWrapper> getPlayerWrapper(Player player) {
        return Optional.ofNullable(players.get(player.getUniqueId()));
    }

    public synchronized void addPlayer(Player player) {
        players.put(player.getUniqueId(), new PlayerWrapper());
    }

    public Optional<PlayerWrapper> getBlockInfo(Player player) {
        Optional<PlayerWrapper> playerWrapperOptional = Optional.ofNullable(players.get(player.getUniqueId()));
        if (playerWrapperOptional.isPresent())
            if (playerWrapperOptional.get().isAddFlag())
                return playerWrapperOptional;

        return Optional.empty();
    }

    public void setBlockInfo(Player player) {
        PlayerWrapper playerWrapper = players.get(player.getUniqueId());
        if (playerWrapper == null)
            return;
        playerWrapper.toggleFlag();
    }

}
