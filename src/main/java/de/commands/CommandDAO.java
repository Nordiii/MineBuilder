package de.commands;

import org.bukkit.Bukkit;

import java.util.Arrays;


public class CommandDAO {
    private AbsCommand[] commands = {new TestCommand(),new AddCommand(),new GetIDCommand(),new LoadCfgCommand()};

    private static CommandDAO instance;

    public static CommandDAO getInstance(){
        if(instance == null)
            return instance = new CommandDAO();
        return instance;
    }

    private CommandDAO(){
        loadCommands();
    }

    private void loadCommands(){
        Arrays.stream(commands).forEachOrdered(x->Bukkit.getPluginCommand(x.getCommandName()).setExecutor(x));
    }

}
