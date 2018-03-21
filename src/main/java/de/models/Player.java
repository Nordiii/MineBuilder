package de.models;

import de.config.ConfigDAO;
import de.events.BlockBreakListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Player {
    private UUID player;

    public boolean isAddFlag() {
        if(addFlag)
        {
            addFlag = false;
            return true;
        }
        return false;
    }

    public void setAddFlag(boolean addFlag) {
        this.addFlag = addFlag;
    }

    private boolean addFlag;
    private HashMap<Class,ArrayList<IEntity>> configs = new HashMap<>();

    public Player(UUID player) {
        this.player = player;
        this.addFlag = false;
        configs.put(BlockBreakListener.class , new ArrayList<>());
    }

    public UUID getPlayer() {
        return player;
    }

    public <C,K> int getExp(Class<C> config,K item)
    {
        ArrayList<IEntity> test = configs.get(config);

        if(test.indexOf(item) == -1)
            //new Block((Block)ConfigDAO.getInstance().get(config,item).get())
            test.add((IEntity) ConfigDAO.getInstance().get(config,item).get());
        IEntity thing = test.get(test.indexOf(item));
        return thing.getExp();
    }

}
