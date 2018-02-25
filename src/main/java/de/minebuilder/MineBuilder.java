package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.BlockBreakListener;
import de.models.Block;
import de.models.Settings;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class MineBuilder extends JavaPlugin {
    public void onEnable(){
        CommandDAO.getInstance();
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);

    }

    public void onDisable(){

    }

    public void testFunc(Optional<?> b){

    }
}
