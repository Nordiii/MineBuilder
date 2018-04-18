package de.commands;

import de.config.ConfigDAO;
import de.events.BlockBreakListener;
import de.events.BlockPlaceListener;
import de.events.EventsDAO;
import de.models.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        ConfigDAO.getInstance().add(

                EventsDAO.getInstance()
                        .getPluginEvents()
                        .map(e->e.getClass())
                        .collect(Collectors.toList())
                        .get(Integer.parseInt(strings[0]))


                ,new Block(entityName,entityID,count,minExp,maxExp));

        return true;
    }

}
