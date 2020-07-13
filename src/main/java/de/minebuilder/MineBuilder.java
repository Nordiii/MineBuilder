package de.minebuilder;

import de.commands.CommandDAO;
import de.config.ConfigDAO;
import de.events.EventsDAO;
import de.models.PlayerDAO;
import de.protection.Protection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MineBuilder extends JavaPlugin {

    public void onEnable() {
        File settings = new File(getDataFolder(), "settings.yml");
        if (!settings.exists()) {
            this.saveResource("settings.yml", false);
            new File(getDataFolder(), "settings.yml");
        }
        Settings.createInstance(new File(getDataFolder(), "settings.yml"));
        Protection.createInstance(this);
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
