package de.config;

import de.models.IEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConfigDAO {
    private static ConfigDAO instance;
    private final List<IConfig> configs = Arrays.asList(new BreakBlockExpConfig(), new PlaceBlockExpConfig());

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
        configs.forEach(IConfig::load);
    }

    public <I> Optional<IEntity> get(Class<?> type, I item) {
        return configs.stream()
                .filter(c -> c.dealsWith(type))
                .findFirst()
                .map(c -> c.get(item));
    }

    public <V> boolean add(Class<?> type, V item) {
        boolean result = configs.stream()
                .filter(c -> c.dealsWith(type))
                .findFirst()
                .map(c -> c.add(item))
                .orElse(false);

        save();

        return result;
    }

    private void save() {
        configs.forEach(IConfig::save);
    }
}
