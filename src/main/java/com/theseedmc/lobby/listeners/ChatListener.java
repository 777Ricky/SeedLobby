package com.theseedmc.lobby.listeners;

import com.theseedmc.lobby.SeedLobby;
import com.theseedmc.lobby.utils.Utils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class ChatListener implements Listener {

/*    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String group = SeedLobby.get().getPermissions().getPrimaryGroup(player);
        String prefix = SeedLobby.get().getChat().getPlayerPrefix(player);
        String suffix = SeedLobby.get().getChat().getPlayerSuffix(player);
        String message = event.getMessage();

        if (group.equalsIgnoreCase("default") || group.isEmpty()) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.default").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("nomad")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.nomad").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("commoner")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.commoner").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("settler")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.settler").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("bishop")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.bishop").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("owner")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.owner").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("noble")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.noble").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("mod")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.mod").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("duke")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.duke").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("baron")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.baron").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("admin")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.admin").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        } else if (group.equalsIgnoreCase("archduke")) {
            event.setFormat(Utils.replaceColors(SeedLobby.get().getConfig().getString("chat.group-formats.archduke").replaceAll("%prefix%", prefix)).replaceAll("%playername%",
                    player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
        }
    }*/

    @EventHandler
    public void onLobbyChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String group = SeedLobby.get().getPermissions().getPrimaryGroup(player);
        String prefix = SeedLobby.get().getChat().getPlayerPrefix(player);
        String suffix = SeedLobby.get().getChat().getPlayerSuffix(player);
        String message = event.getMessage();
        ConfigurationSection config = SeedLobby.get().getConfig();

        if (config.getString("chat.group-formats." + group) == null) {
            event.setFormat(Utils.replaceColors(Objects.requireNonNull(config.getString("chat.default")).replaceAll("%prefix", prefix))
                    .replaceAll("%playername", player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
            return;
        }

        event.setFormat(Utils.replaceColors(Objects.requireNonNull(config.getString("chat.group-formats." + group)).replaceAll("%prefix", prefix))
                .replaceAll("%playername", player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
    }
}
