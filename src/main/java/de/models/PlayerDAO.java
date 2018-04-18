package de.models;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerDAO {
    private static PlayerDAO ourInstance = new PlayerDAO();

    public static PlayerDAO getInstance() {
        return ourInstance;
    }

    private PlayerDAO() {
    }

    private List<PlayerWrapper> players = new ArrayList<>();

    public <T> void updateItem(Class<T> event,Exp item){
        players.stream().forEach(p->p.updateItem(event, item));
    }
    public  <T> int getExp(Class<T> config, Player player , Exp key){
        Optional<PlayerWrapper> playerStats = players.stream()
                                    .filter(new PlayerWrapper(player)::matchPlayer)
                                    .findAny();
        if(playerStats.isPresent())
            return playerStats.get().getExp(config,key);
        return 0;
    }

    public void addPlayer(Player player){
        players.add(new PlayerWrapper(player));
    }

    public boolean getBlockInfo(Player player){
        return players.stream().filter(PlayerWrapper::isAddFlag)
                        .anyMatch(new PlayerWrapper(player)::matchPlayer);
    }

    public void setBlockInfo(Player player){
        players.stream().filter(new PlayerWrapper(player)::matchPlayer)
                        .findAny()
                        .ifPresent(PlayerWrapper::toggleFlag);
    }

}
