package de.models;

import de.events.BlockBreakListener;
import de.events.BlockPlaceListener;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerWrapper {
    private Player player;

    public boolean isAddFlag() {
        if(addFlag)
        {
            toggleFlag();
            return true;
        }
        return false;
    }

    public void toggleFlag() {
        this.addFlag = !addFlag;
    }

    private boolean addFlag;
    private HashMap<Class,ArrayList<Exp>> configs = new HashMap<>();

    public PlayerWrapper(Player player) {
        this.player = player;
        this.addFlag = false;
        configs.put(BlockBreakListener.class , new ArrayList<>());
        configs.put(BlockPlaceListener.class , new ArrayList<>());
    }

    public boolean matchPlayer(PlayerWrapper player) {
        return this.player == player;

    }

    public <C> int getExp(Class<C> config,Exp item)
    {
        ArrayList<Exp> entities = configs.get(config);

        if(entities.contains(item))
            entities.add(item);

        return entities.stream()
                .filter(item::equals)
                .mapToInt(Exp::getExp)
                .findAny()
                .getAsInt();
    }

}
