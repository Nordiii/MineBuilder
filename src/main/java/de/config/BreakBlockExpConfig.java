package de.config;


import de.events.BlockBreakListener;
import de.models.Block;

import java.util.Optional;

public class BreakBlockExpConfig extends AbsConfig<Block,Block>{

    public BreakBlockExpConfig(){
        super("blockBreakExp.json");
    }

    @Override
    public <T> Class<T> dealsWith() {
        return (Class<T>) BlockBreakListener.class;
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
    public Optional<Block> get(Block id) {
        Optional<Block> t = getConfig().stream().filter(id::equals).findAny();
        return t.map(Block::new);
    }

}
