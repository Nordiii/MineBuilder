package de.commands;

import de.events.BlockBreakListener;
import de.events.BlockPlaceListener;
import de.events.EntityDeathListener;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Objects;


public class CommandDAO {
    private final AbsCommand[] commands = {
            new AddCommand("mbAddBreak", BlockBreakListener.class),
            new AddCommand("mbAddPlace", BlockPlaceListener.class),
            new AddCommand("mbAddKill", EntityDeathListener.class),
            new GetIDCommand(), new LoadCfgCommand()
    };

    private static CommandDAO instance;

    private CommandDAO() {
        loadCommands();
    }

    public static void loadInstance() {
        if (instance == null) {
            instance = new CommandDAO();
        }
    }

    private void loadCommands() {
        Arrays.stream(commands).forEachOrdered(x -> Objects.requireNonNull(Bukkit.getPluginCommand(x.getCommandName())).setExecutor(x));
    }

}
