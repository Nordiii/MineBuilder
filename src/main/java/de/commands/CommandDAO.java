package de.commands;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Objects;


public class CommandDAO {
    private final AbsCommand[] commands = {new AddCommand(), new GetIDCommand(), new LoadCfgCommand()};

    private static CommandDAO instance;

    public static void loadInstance(){
        if(instance == null) {
            instance = new CommandDAO();
        }
    }

    private CommandDAO(){
        loadCommands();
    }

    private void loadCommands(){
        Arrays.stream(commands).forEachOrdered(x -> Objects.requireNonNull(Bukkit.getPluginCommand(x.getCommandName())).setExecutor(x));
    }

}
