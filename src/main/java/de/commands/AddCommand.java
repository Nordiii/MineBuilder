package de.commands;

import de.config.ConfigDAO;
import de.events.AbsEvent;
import de.events.EventsDAO;
import de.models.Block;
import de.models.Exp;
import de.models.PlayerDAO;
import de.models.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;
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
        if (!commandSender.hasPermission("op"))
            return false;

        if (commandSender instanceof Player)
            return addIEntityPlayer((Player) commandSender, strings);
        else
            return addIEntityServer(commandSender, strings);

    }

    private boolean addIEntityPlayer(CommandSender commandSender, String[] strings) {
        if (strings.length != 4)
            return false;

        int eventIndex = Integer.parseInt(strings[0]);
        int minExp = Integer.parseInt(strings[1]);
        int maxExp = Integer.parseInt(strings[2]);
        int count = Integer.parseInt(strings[3]);

        Class pluginEvent = EventsDAO.getInstance()
                .getPluginEvents()
                .map(AbsEvent::getClass)
                .collect(Collectors.toList())
                .get(eventIndex);

        Optional<PlayerWrapper> player = PlayerDAO.getInstance().getPlayerWrapper((Player) commandSender);
        if (!player.isPresent())
            return false;

        String material = player.get().getStoredMaterial();
        if (Objects.isNull(material)) {
            commandSender.sendMessage("[MineBuilder] Use /mbgetid to select a material, use this command after");
            return false;
        }

        Exp newItem = new Block(player.get().getStoredMaterial(), count, minExp, maxExp);
        ConfigDAO.getInstance().add(
                pluginEvent
                , newItem);
        PlayerDAO.getInstance().updateItem(pluginEvent, newItem);

        player.get().setStoredMaterial(null);

        return true;
    }

    private boolean addIEntityServer(CommandSender commandSender, String[] strings) {
        if (strings.length != 6)
            return false;


        int eventIndex = Integer.parseInt(strings[0]);
        String entityName = strings[1];
        int minExp = Integer.parseInt(strings[2]);
        int maxExp = Integer.parseInt(strings[3]);
        int count = Integer.parseInt(strings[4]);

        Class pluginEvent = EventsDAO.getInstance()
                .getPluginEvents()
                .map(AbsEvent::getClass)
                .collect(Collectors.toList())
                .get(eventIndex);

        Exp newItem = new Block(entityName, count, minExp, maxExp);
        ConfigDAO.getInstance().add(
                pluginEvent
                , newItem);
        PlayerDAO.getInstance().updateItem(pluginEvent, newItem);
        return true;
    }


}
