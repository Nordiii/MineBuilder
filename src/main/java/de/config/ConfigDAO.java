package de.config;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConfigDAO
{
    private static ConfigDAO instance;
    private List<IConfig> configs = Arrays.asList(new BreakBlockExpConfig(), new PlaceBlockConfig());

    public static ConfigDAO getInstance() {
        if(instance==null)
             instance = new ConfigDAO();
        return instance;

    }

    private ConfigDAO(){
        loadConfig();
    }

    public void loadConfig(){
        configs.forEach(IConfig::load);
    }

    public <I> Optional get(Class<?> type, I item){
        Optional<IConfig> config = configs.stream().filter(c -> c.dealsWith() == type).findFirst();

        if(config.isPresent())
            return config.get().get(item);

        return Optional.empty();
    }

    public <V> void add(Class<?> type, V item)
    {
        configs.stream().filter(x->type.equals(x.dealsWith()))
                .findFirst()
                .ifPresent(c->c.add(item));
        save();
    }

    private void save(){
        configs.forEach(IConfig::save);
    }
}
