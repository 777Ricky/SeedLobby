package com.theseedmc.lobby.command.commands;

import com.theseedmc.lobby.command.AbstractCommand;
import com.theseedmc.lobby.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TransferCommand extends AbstractCommand {

    public TransferCommand() {
        super("transfer", "seedlobby.user", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length != 1 || args[0] == null) {
            player.sendMessage(Utils.replaceColors("&c&lERROR: &7/transfer <melon|pumpkin|mushroom>"));
            return;
        }

        Utils.sendPlayer(player, args[0]);
    }
}
