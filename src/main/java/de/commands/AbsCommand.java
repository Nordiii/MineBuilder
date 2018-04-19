package de.commands;

import org.bukkit.command.CommandExecutor;

abstract class AbsCommand implements ICommand, CommandExecutor {
    private final String COMMAND_NAME;
    AbsCommand(String COMMAND_NAME){
        this.COMMAND_NAME = COMMAND_NAME;
    }

    String getCOMMAND_NAME(){
        return COMMAND_NAME;
    }
}
