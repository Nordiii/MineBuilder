package de.config;

import com.google.gson.reflect.TypeToken;
import de.events.AbsEvent;
import de.events.EntityDeathListener;
import de.models.Block;
import de.models.IEntity;

import java.util.HashMap;

public class EntityDeathExpConfig extends AbsConfig {
    EntityDeathExpConfig() {
        super("entityDeathExp.json", TypeToken.getParameterized(HashMap.class, String.class, Block.class).getType());
    }

    @Override
    public Class<? extends AbsEvent> dealsWith() {
        return EntityDeathListener.class;
    }

    @Override
    public void add(IEntity value) {
        getConfig().put(value.getID(), value);
    }

    @Override
    public boolean set(String key, IEntity newValue) {
        getConfig().put(key, newValue);
        return false;
    }

    @Override
    public IEntity get(IEntity key) {
        return new Block(getConfig().get(key.getID()));
    }
}
