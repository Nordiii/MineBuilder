package de.commands;

import org.bukkit.Bukkit;

import java.util.Arrays;


public class CommandDAO {
    private AbsCommand[] commands = {new AddCommand(),new GetIDCommand(),new LoadCfgCommand()};

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
        Arrays.stream(commands).forEachOrdered(x->Bukkit.getPluginCommand(x.getCommandName()).setExecutor(x));
    }

}
