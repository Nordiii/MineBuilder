package de.models;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerDAO {
    private static PlayerDAO ourInstance = new PlayerDAO();

    public static PlayerDAO getInstance() {
        return ourInstance;
    }

    private PlayerDAO() {
    }

    private ArrayList<Player> players = new ArrayList<>();

    public  <T,K> int getExp(Class<T> config, UUID player , K key){
        for(Player p : players)
            if(p.getPlayer() == player)
                return p.getExp(config,key);

        return 0;
    }

    public void addPlayer(UUID player){
        players.add(new Player(player));
    }

    public boolean getBlockInfo(UUID player){
        for(Player p : players)
            if(p.getPlayer() == player)
                return p.isAddFlag();

        return false;
    }

    public void setBlockInfo(UUID player){
        for(Player p : players)
            if(p.getPlayer() == player)
                p.setAddFlag(true);
    }

}
