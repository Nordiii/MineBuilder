package de.commands;

import de.models.PlayerDAO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetIDCommand extends AbsCommand {
    public GetIDCommand() {
        super("mbGetId");
    }

    @Override
    public String getCommandName() {
        return getCOMMAND_NAME();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player && commandSender.hasPermission("op"))
            PlayerDAO.getInstance().setBlockInfo(((Player) commandSender));
        return true;
    }
}
