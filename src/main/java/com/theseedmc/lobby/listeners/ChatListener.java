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

    @EventHandler
    public void onLobbyChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String group = Utils.getFormat(player);
        String prefix = SeedLobby.get().getChat().getPlayerPrefix(player);
        String suffix = SeedLobby.get().getChat().getPlayerSuffix(player);
        String message = event.getMessage();

        if (SeedLobby.get().getConfig().getString("chat.group-formats." + group.toLowerCase()) == null) {
            event.setFormat(Utils.replaceColors(Objects.requireNonNull(SeedLobby.get().getConfig().getString("chat.default")).replaceAll("%prefix%", prefix))
                    .replaceAll("%playername%", player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
            return;
        }

        event.setFormat(Utils.replaceColors(Objects.requireNonNull(SeedLobby.get().getConfig().getString("chat.group-formats." + group.toLowerCase())).replaceAll("%prefix%", prefix))
                .replaceAll("%playername%", player.getDisplayName()).replaceAll("%suffix%", suffix) + message);
    }
}
