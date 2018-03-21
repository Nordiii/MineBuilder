package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.BlockBreakListener;
import de.events.BlockPlaceListener;
import de.events.PlayerInteractionListener;
import de.events.PlayerJoinListener;
import de.models.Block;
import de.models.PlayerDAO;
import de.models.Settings;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Optional;

public class MineBuilder extends JavaPlugin {
    public void onEnable(){
        CommandDAO.getInstance();
        ConfigDAO.getInstance();
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractionListener(),this);

        for(Player p:this.getServer().getOnlinePlayers())
            PlayerDAO.getInstance().addPlayer(p.getUniqueId());

    }

    public void onDisable(){
        ConfigDAO.getInstance().save();
    }

    public void testFunc(Optional<?> b){

    }
}
