package com.theseedmc.lobby;

import com.theseedmc.lobby.command.AbstractCommand;
import com.theseedmc.lobby.listeners.ChatListener;
import com.theseedmc.lobby.listeners.PlayerListener;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SeedLobby extends JavaPlugin {

    private static SeedLobby plugin;
    private Permission perms;
    private Chat chat;

    public void onEnable() {

        plugin = this;

        AbstractCommand.registerCommands(this);
        saveDefaultConfig();
        registerEvents();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        setupPermissions();
        setupChat();
    }

    public void onDisable() {
        plugin = null;
    }

    public static synchronized SeedLobby get() {
        return plugin;
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new ChatListener(), this);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Permission getPermissions() {
        return perms;
    }

    public Chat getChat() {
        return chat;
    }
}
