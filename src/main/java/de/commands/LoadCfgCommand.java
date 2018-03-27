package de.commands;

import de.config.ConfigDAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class LoadCfgCommand extends AbsCommand {
    public LoadCfgCommand() {
        super("mbLoadCfg");
    }

    @Override
    public String getCommandName() {
        return getCOMMAND_NAME();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("op"))
        {
            ConfigDAO.getInstance().loadConfig();
            return true;
        }
        return false;
    }
}
