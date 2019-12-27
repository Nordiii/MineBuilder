package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.EventsDAO;
import de.models.PlayerDAO;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MineBuilder extends JavaPlugin {

    public void onEnable(){
        CommandDAO.loadInstance();
        ConfigDAO.getInstance();
        EventsDAO.getInstance()
                .getEvents()
                .forEach(this::registerEvents);

        this.getServer().getOnlinePlayers()
                .forEach(PlayerDAO.getInstance()::addPlayer);
    }

    public void onDisable(){

    }

    private void registerEvents(Listener listener)
    {
        this.getServer().getPluginManager().registerEvents(listener,this);
    }

}
