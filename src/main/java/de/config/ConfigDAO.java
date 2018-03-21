package de.config;

import java.util.Arrays;
import java.util.Optional;

public class ConfigDAO
{
    private static ConfigDAO instance;
    private IConfig[] configs = {new BreakBlockExpConfig()};

    public static ConfigDAO getInstance() {
        if(instance==null)
            return instance = new ConfigDAO();
        return instance;
    }

    private ConfigDAO(){
        loadConfig();
    }

    private void loadConfig(){
        Arrays.stream(configs).forEach(x->x.load());
    }

    public <V,I> Optional<V> get(Class<?> type, I item){
        return Arrays.stream(configs).filter(x->type.equals(x.dealsWith())).limit(1)
                .findFirst()
                .get()
                .get(item);
    }

    public <V> boolean add(Class<?> type, V item)
    {
        return Arrays.stream(configs).filter(x->type.equals(x.dealsWith())).limit(1)
                .findFirst()
                .get()
                .add(item);
    }

    public void save(){
        Arrays.stream(configs).forEach(x->x.save());
    }
}
