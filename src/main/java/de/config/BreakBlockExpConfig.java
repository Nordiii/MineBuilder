package de.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.events.BlockBreakListener;
import de.models.Block;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BreakBlockExpConfig implements IConfig<Block,Block>{
    List<Block> blockConfig;


    @Override
    public void load() {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        File f = new File("plugins/MineBuilder/exp.json");
        if(!f.exists())
            blockConfig = new ArrayList<>();
        else
            try {
                blockConfig = g.fromJson(new String(Files.readAllBytes(f.toPath())), new TypeToken<ArrayList<Block>>(){ }.getType());
            }catch (IOException e)
            {
                Bukkit.getLogger().severe(e.getMessage());
            }
    }

    @Override
    public void save() {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        File f = new File("plugins/MineBuilder/exp.json");

        try
        {
            Files.createDirectories(Paths.get("plugins/MineBuilder"));
            f.createNewFile();
            PrintWriter out = new PrintWriter(f);
            out.write(g.toJson(blockConfig));
            out.close();
        }catch (IOException e)
        {
            Bukkit.getLogger().severe(e.getMessage());
        }

    }

    @Override
    public <T> Class<T> dealsWith() {
        return (Class<T>) BlockBreakListener.class;
    }

    @Override
    public boolean add(Block value) {
        return false;
    }

    @Override
    public boolean set(Block key, Block newValue) {
        return false;
    }

    @Override
    public Optional<Block> get(Block id) {
        return blockConfig.stream().filter(x->id.equals(x)).findAny();
    }
}
