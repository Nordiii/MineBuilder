package de.commands;

import de.config.ConfigDAO;
import de.events.AbsEvent;
import de.events.EventsDAO;
import de.models.Block;
import de.models.Exp;
import de.models.PlayerDAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.stream.Collectors;

public class AddCommand extends AbsCommand {
    public AddCommand() {
        super("mbAdd");
    }

    @Override
    public String getCommandName() {
        return getCOMMAND_NAME();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("op") || strings.length != 6)
            return false;
        String entityName = strings[1].toUpperCase();
        int entityID = Integer.parseInt(strings[2]);
        int minExp = Integer.parseInt(strings[3]);
        int maxExp = Integer.parseInt(strings[4]);
        int count = Integer.parseInt(strings[5]);


        Class pluginEvent = EventsDAO.getInstance()
                .getPluginEvents()
                .map(AbsEvent::getClass)
                .collect(Collectors.toList())
                .get(Integer.parseInt(strings[0]));

        Exp newItem = new Block(entityName,entityID,count,minExp,maxExp);
        ConfigDAO.getInstance().add(
                pluginEvent
                ,newItem);
        PlayerDAO.getInstance().updateItem(pluginEvent,newItem);
        return true;
    }

}
