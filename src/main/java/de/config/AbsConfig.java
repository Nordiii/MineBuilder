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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsConfig<T,K> implements IConfig<T,K> {
        private List<T> config;
        private final String fileName;
        private final String path;

        public AbsConfig(String fileName,String path){
            this.fileName = fileName;
            this.path = path;
        }

        @Override
        public void load() {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            File f = new File(path+fileName);
            if(!f.exists())

                config = new ArrayList<>();
            else
                try {
                    config = g.fromJson(new String(Files.readAllBytes(f.toPath())), new TypeToken<ArrayList<Block>>(){ }.getType());
                }catch (IOException e)
                {
                    Bukkit.getLogger().severe(e.getMessage());
                }
        }

        @Override
        public void save() {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            File f = new File(path+fileName);

            try
            {
                Files.createDirectories(Paths.get("plugins/MineBuilder"));
                f.createNewFile();
                PrintWriter out = new PrintWriter(f);
                out.write(g.toJson(config));
                out.close();
            }catch (IOException e)
            {
                Bukkit.getLogger().severe(e.getMessage());
            }

        }

        protected List<T> getConfig(){
            return this.config;
        }

}
