package de.config;

import de.events.BlockPlaceListener;
import de.models.Block;

public class PlaceBlockExpConfig extends AbsConfig<Block, Block> {

    public PlaceBlockExpConfig() {
        super("placeBlockExp.json");
    }

    @Override
    public <T> boolean dealsWith(Class<T> eventClass) {
        return eventClass == BlockPlaceListener.class;
    }

    @Override
    public boolean add(Block value) {
        getConfig().remove(value);
        return getConfig().add(value);
    }

    @Override
    public boolean set(Block key, Block newValue) {
        getConfig().set(getConfig().indexOf(key),newValue);
        return true;
    }

    @Override
    public Block get(Block id) {
        return getConfig().stream()
                .filter(id::equals)
                .findAny()
                .map(Block::new).orElse(null);
    }
}
