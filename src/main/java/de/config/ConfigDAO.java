package de.config;

import de.events.AbsEvent;
import de.models.IEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConfigDAO {
    private static ConfigDAO instance;
    private final Map<Class<? extends AbsEvent>, IConfig> configs = new HashMap<Class<? extends AbsEvent>, IConfig>() {
        {
            ;
            put(new BreakBlockExpConfig().dealsWith(), new BreakBlockExpConfig());
            put(new PlaceBlockExpConfig().dealsWith(), new PlaceBlockExpConfig());
        }
    };

    private ConfigDAO() {
        checkConfigFolder();
        loadConfig();
    }

    public static ConfigDAO getInstance() {
        if (instance == null)
            instance = new ConfigDAO();
        return instance;

    }

    public void checkConfigFolder() {
        try {
            Files.createDirectories(Paths.get("plugins/MineBuilder"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        configs.values().forEach(IConfig::load);
    }

    public Optional<IEntity> get(Class<?> type, IEntity item) {
        IEntity iEntity = configs.get(type).get(item);
        if (iEntity.getID() == null)
            return Optional.empty();
        return Optional.ofNullable(iEntity);
    }

    public void add(Class<?> type, IEntity item) {
        configs.get(type).add(item);

        save();
    }

    private void save() {
        configs.values().forEach(IConfig::save);
    }
}
