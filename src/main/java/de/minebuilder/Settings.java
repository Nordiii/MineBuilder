package de.minebuilder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class Settings {
    private static FileConfiguration config;
    private static Settings instance;
    final private File file;

    private Settings(File config) {
        file = config;
        Settings.config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static synchronized Settings createInstance(File config) {
        if (instance != null)
            return instance;
        instance = new Settings(config);
        return instance;
    }

    public static synchronized Settings getInstance() {
        return Objects.requireNonNull(instance);
    }

    public synchronized void reloadSetting() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public enum path {
        DISABLE_PLACE("disable.place"),
        DISABLE_BREAK("disable.break"),
        SET_DEFAULT_EXP_0_BREAK("set-default-exp-to-zero.break"),
        DISABLE_KILL("disable.kill"),
        SET_DEFAULT_EXP_0_KILL("set-default-exp-to-zero.kill"),
        PROTECTION_COUNT("protect-blocks-count");

        public final String path;

        path(String path) {
            this.path = path;
        }
    }
}
