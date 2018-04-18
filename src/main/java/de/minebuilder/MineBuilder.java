package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.*;
import de.models.PlayerDAO;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class MineBuilder extends JavaPlugin {

    private List<Listener> eventsToRegister = Arrays.asList(new BlockBreakListener(),new BlockPlaceListener(),new PlayerJoinListener(),new PlayerInteractionListener());

    public void onEnable(){
        CommandDAO.getInstance();
        ConfigDAO.getInstance();
        EventsDAO.getInstance()
                .getEvents()
                .forEach(this::registerEvents);

        this.getServer().getOnlinePlayers().stream()
                .forEach(p-> PlayerDAO.getInstance().addPlayer(p));
    }

    public void onDisable(){

    }

    private void registerEvents(Listener listener)
    {
        this.getServer().getPluginManager().registerEvents(listener,this);
    }

}
