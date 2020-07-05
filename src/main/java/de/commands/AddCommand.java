package de.commands;

import de.config.ConfigDAO;
import de.events.AbsEvent;
import de.models.Block;
import de.models.IEntity;
import de.models.PlayerDAO;
import de.models.PlayerWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;

public class AddCommand extends AbsCommand {
    private final Class<? extends AbsEvent> pluginEvent;

    public AddCommand(String name, Class<? extends AbsEvent> pluginEvent) {
        super(name);
        this.pluginEvent = pluginEvent;
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
            return addIEntityPlayer(commandSender, strings);
        else
            return addIEntityServer(commandSender, strings);

    }

    private boolean addIEntityPlayer(CommandSender commandSender, String[] strings) {
        if (strings.length != 3)
            return false;

        int minExp = Integer.parseInt(strings[0]);
        int maxExp = Integer.parseInt(strings[1]);
        int count = Integer.parseInt(strings[2]);

        if (minExp > maxExp) {
            commandSender.sendMessage("[MineBuilder] The minimum exp to drop must be smaller or equals to the maximum exp");
            return false;
        }


        Optional<PlayerWrapper> player = PlayerDAO.getInstance().getPlayerWrapper((Player) commandSender);
        if (!player.isPresent())
            return false;

        String material = player.get().getStoredMaterial();
        if (Objects.isNull(material)) {
            commandSender.sendMessage("[MineBuilder] Use /mbgetid to select a material, use this command after");
            return false;
        }

        IEntity newItem = new Block(player.get().getStoredMaterial(), count, minExp, maxExp);
        ConfigDAO.getInstance().add(
                pluginEvent
                , newItem);
        PlayerDAO.getInstance().updateItem(pluginEvent, newItem);

        player.get().setStoredMaterial(null);

        return true;
    }

    private boolean addIEntityServer(CommandSender commandSender, String[] strings) {
        if (strings.length != 4)
            return false;


        String entityName = strings[0];
        int minExp = Integer.parseInt(strings[1]);
        int maxExp = Integer.parseInt(strings[2]);
        int count = Integer.parseInt(strings[3]);

        if (minExp > maxExp) {
            commandSender.sendMessage("[MineBuilder] The minimum exp to drop must be smaller or equals to the maximum exp");
            return false;
        }

        IEntity newItem = new Block(entityName, count, minExp, maxExp);
        ConfigDAO.getInstance().add(
                pluginEvent
                , newItem);

        PlayerDAO.getInstance().updateItem(pluginEvent, newItem);
        return true;
    }


}
