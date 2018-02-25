package de.commands;

import org.bukkit.command.CommandExecutor;

public abstract class AbsCommand implements ICommand, CommandExecutor {
    private final String COMMAND_NAME;
    public AbsCommand(String COMMAND_NAME){
        this.COMMAND_NAME = COMMAND_NAME;
    }

    protected String getCOMMAND_NAME(){
        return COMMAND_NAME;
    }
}
