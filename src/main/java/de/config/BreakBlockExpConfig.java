package de.config;


import de.events.BlockBreakListener;
import de.models.Block;

public class BreakBlockExpConfig extends AbsConfig<Block,Block>{

    public BreakBlockExpConfig(){
        super("blockBreakExp.json");
    }


    @Override
    public <T> boolean dealsWith(Class<T> eventClass) {
        return eventClass == BlockBreakListener.class;
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
    public Block get(Block block) {
        return getConfig().stream()
                .filter(block::equals)
                .findAny()
                .map(Block::new).orElse(null);

    }

}
