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
            return instance = new ConfigDAO();
        return instance;
    }

    private ConfigDAO(){
        loadConfig();
    }

    public void loadConfig(){
        configs.forEach(x->x.load());
    }

    public <I> Optional get(Class<?> type, I item){
        Optional<IConfig> config = configs.stream().filter(c -> c.dealsWith() == type).findFirst();

        if(config.isPresent())
            return config.get().get(item);

        return Optional.empty();
    }

    public <V> boolean add(Class<?> type, V item)
    {
        boolean worked = configs.stream().filter(x->type.equals(x.dealsWith())).limit(1)
                .findFirst()
                .get()
                .add(item);
        save();
        return worked;
    }

    public void save(){
        configs.stream().forEach(x->x.save());
    }
}
