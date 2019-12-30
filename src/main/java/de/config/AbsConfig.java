package de.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.models.IEntity;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsConfig implements IConfig {
    private final Type token;
    private final String fileName;
    private final String path = "plugins/MineBuilder/";
    private Map<String, IEntity> config;

    AbsConfig(String fileName, Type type) {
        this.fileName = fileName;
        this.token = type;
    }

    @Override
    public void load() {
        Gson g = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        File f = new File(path + fileName);
        if (!f.exists())
            config = new HashMap<>();
        else
            try {
                config = g.fromJson(new String(Files.readAllBytes(f.toPath())), token);
            } catch (IOException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
    }

    @Override
    public void save() {
        Gson g = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();


        File f = new File(path + fileName);
        try (PrintWriter out = new PrintWriter(f)) {
            boolean ignoreVal = f.createNewFile();
            out.write(g.toJson(config));
        } catch (IOException e) {
            Bukkit.getLogger().severe(e.getMessage());
        }

    }

    @Override
    public void printConfig() {
        config.values().forEach(System.out::println);
    }

    public Map<String, IEntity> getConfig() {
        return this.config;
    }

}
