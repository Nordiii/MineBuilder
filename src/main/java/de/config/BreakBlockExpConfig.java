package de.config;


import com.google.gson.reflect.TypeToken;
import de.events.AbsEvent;
import de.events.BlockBreakListener;
import de.models.Block;
import de.models.IEntity;

import java.util.HashMap;

public class BreakBlockExpConfig extends AbsConfig {

    public BreakBlockExpConfig() {
        super("blockBreakExp.json", TypeToken.getParameterized(HashMap.class, String.class, Block.class).getType());
    }


    @Override
    public Class<? extends AbsEvent> dealsWith() {
        return BlockBreakListener.class;
    }

    @Override
    public void add(IEntity value) {
        getConfig().put(value.getID(), value);
    }

    @Override
    public boolean set(String key, IEntity newValue) {
        getConfig().put(key, newValue);
        return true;
    }

    @Override
    public IEntity get(IEntity value) {
        return new Block(getConfig().get(value.getID()));

    }

}
