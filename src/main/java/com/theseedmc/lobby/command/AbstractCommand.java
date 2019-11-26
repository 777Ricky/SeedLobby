package com.theseedmc.lobby.command;

import com.theseedmc.lobby.SeedLobby;
import com.theseedmc.lobby.command.commands.TransferCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public abstract class AbstractCommand implements CommandExecutor {

    private String commandName;
    private String permission;
    private boolean canConsoleUse;

    public AbstractCommand(String commandName, String permission, boolean canConsoleUse) {
        this.commandName = commandName;
        this.permission = permission;
        this.canConsoleUse = canConsoleUse;
        SeedLobby.get().getCommand(commandName).setExecutor(this);
    }

    public static JavaPlugin plugin;

    public static void registerCommands(JavaPlugin pl) {
        plugin = pl;

        new TransferCommand();
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getLabel().equalsIgnoreCase(commandName))
            return true;
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lERROR: &7No permission."));
            return true;
        }
        if (!canConsoleUse && !(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command sorry!");
            return true;
        }
        execute(sender, args);
        return true;
    }
}
