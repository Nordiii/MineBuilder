package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.*;
import de.models.Block;
import de.models.PlayerDAO;
import de.models.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MineBuilder extends JavaPlugin {

    private List<Listener> eventsToRegister = Arrays.asList(new BlockBreakListener(),new BlockPlaceListener(),new PlayerJoinListener(),new PlayerInteractionListener());

    public void onEnable(){
        CommandDAO.getInstance();
        ConfigDAO.getInstance();
        eventsToRegister.forEach(e-> this.getServer().getPluginManager().registerEvents(e,this));

        this.getServer().getOnlinePlayers().stream()
                .forEach(p-> PlayerDAO.getInstance().addPlayer(p.getUniqueId()));
    }

    public void onDisable(){

    }

}
