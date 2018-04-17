package de.models;

import de.config.ConfigDAO;
import de.events.BlockBreakListener;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerWrapper {
    private Player player;

    public boolean isAddFlag() {
        if(addFlag)
        {
            addFlag = false;
            return true;
        }
        return false;
    }

    public void toggleFlag() {
        this.addFlag = !addFlag;
    }

    private boolean addFlag;
    private HashMap<Class,ArrayList<IEntity>> configs = new HashMap<>();

    public PlayerWrapper(Player playerUUID) {
        this.player = playerUUID;
        this.addFlag = false;
        configs.put(BlockBreakListener.class , new ArrayList<>());
    }

    public boolean matchPlayer(PlayerWrapper player) {
        return this.player == player;

    }

    public <C,K> int getExp(Class<C> config,K item)
    {
        ArrayList<IEntity> test = configs.get(config);

        if(test.indexOf(item) == -1)
            test.add((IEntity) ConfigDAO.getInstance().get(config,item).get());
        IEntity thing = test.get(test.indexOf(item));
        return thing.getExp();
    }

}
