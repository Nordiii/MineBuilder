package de.config;


import de.events.BlockBreakListener;
import de.models.Block;
import de.models.IEntity;

public class BreakBlockExpConfig extends AbsConfig {

    public BreakBlockExpConfig() {
        super("blockBreakExp.json");
    }


    @Override
    public <T> boolean dealsWith(Class<T> eventClass) {
        return eventClass == BlockBreakListener.class;
    }

    @Override
    public boolean add(IEntity value) {
        getConfig().remove(value);
        return getConfig().add(value);
    }

    @Override
    public boolean set(IEntity key, IEntity newValue) {
        getConfig().set(getConfig().indexOf(key), newValue);
        return true;
    }

    @Override
    public IEntity get(IEntity block) {
        return getConfig().stream()
                .filter(block::equals)
                .findAny()
                .map(Block::new).orElse(null);

    }

}
