package com.theseedmc.lobby;

import com.theseedmc.lobby.command.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SeedLobby extends JavaPlugin {

    private static SeedLobby plugin;

    public void onEnable() {
        saveDefaultConfig();
        registerEvents();
        AbstractCommand.registerCommands(this);

        plugin = this;

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public void onDisable() {
        plugin = null;
    }

    public static synchronized SeedLobby get() {
        return plugin;
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

    }
}
