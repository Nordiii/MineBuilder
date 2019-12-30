package de.config;

import com.google.gson.reflect.TypeToken;
import de.events.AbsEvent;
import de.events.BlockPlaceListener;
import de.models.Block;
import de.models.IEntity;

import java.util.HashMap;

public class PlaceBlockExpConfig extends AbsConfig {

    public PlaceBlockExpConfig() {
        super("placeBlockExp.json", TypeToken.getParameterized(HashMap.class, String.class, Block.class).getType());
    }

    @Override
    public Class<? extends AbsEvent> dealsWith() {
        return BlockPlaceListener.class;
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
