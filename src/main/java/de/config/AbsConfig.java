package de.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.models.Block;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsConfig<T, K> implements IConfig<T, K> {
    private List<T> config;
    private final String fileName;
    private final String path = "plugins/MineBuilder/";

    AbsConfig(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void load() {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        File f = new File(path + fileName);
        if (!f.exists())
            config = new ArrayList<>();
        else
            try {
                config = g.fromJson(new String(Files.readAllBytes(f.toPath())), new TypeToken<ArrayList<Block>>() {
                }.getType());
            } catch (IOException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
    }

    @Override
    public void save() {
        Gson g = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();


        File f = new File(path + fileName);
        try (PrintWriter out = new PrintWriter(f)) {
            f.createNewFile();
            out.write(g.toJson(config));
        } catch (IOException e) {
            Bukkit.getLogger().severe(e.getMessage());
        }

    }

    public List<T> getConfig() {
        return this.config;
    }

}
