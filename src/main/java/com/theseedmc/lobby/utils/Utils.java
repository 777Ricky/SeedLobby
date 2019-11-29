package com.theseedmc.lobby.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.theseedmc.lobby.SeedLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Utils {

    public static String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static void teleportSpawn(Player player) {
        World world = Bukkit.getWorld(Objects.requireNonNull(SeedLobby.get().getConfig().getString("Spawn.world")));

        double x = SeedLobby.get().getConfig().getDouble("Spawn.x");
        double y = SeedLobby.get().getConfig().getDouble("Spawn.y");
        double z = SeedLobby.get().getConfig().getDouble("Spawn.z");
        float yaw = (float) SeedLobby.get().getConfig().getDouble("Spawn.yaw");
        float pitch = (float) SeedLobby.get().getConfig().getDouble("Spawn.pitch");

        Location location = new Location(world, x, y, z);

        location.setYaw(yaw);
        location.setPitch(pitch);

        player.teleport(location);
        Bukkit.getScheduler().runTaskLater(SeedLobby.get(), () -> player.teleport(location), 2);
    }

    public static void sendMOTD(Player player) {
        player.sendMessage(replaceColors("&7&m=------------------------------------------="));
        player.sendMessage(replaceColors("&7&l»                &fWelcome to &a&lThe Seed&f."));
        player.sendMessage(replaceColors("&7&l»"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),  "tellraw " + player.getName() + " [\"\",{\"text\":\"»          " + ChatColor.GRAY + "[\",\"color\":\"gray\",\"bold\":\"true\"},{\"text\":\"MELON\",\"color\":\"green\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/transfer melon\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to connect!\",\"color\":\"gray\"}]}}},{\"text\":\"]  \",\"color\":\"gray\",\"bold\":false},{\"text\":\"[\",\"color\":\"gray\"},{\"text\":\"PUMPKIN\",\"color\":\"gold\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/transfer pumpkin\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to connect!\",\"color\":\"gray\"}]}}},{\"text\":\"]  \",\"color\":\"gray\",\"bold\":false},{\"text\":\"[\",\"color\":\"gray\"},{\"text\":\"MUSHROOM\",\"color\":\"light_purple\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/transfer mushroom\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to connect!\",\"color\":\"gray\"}]}}},{\"text\":\"]\",\"color\":\"gray\",\"bold\":false}]");
        player.sendMessage(replaceColors("&7&m=------------------------------------------="));
    }

    public static void sendPlayer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        if (server.equalsIgnoreCase("melon")) {

            player.performCommand("bungeeplayer joinqueue melon");

        } else if (server.equalsIgnoreCase("pumpkin")) {

            player.performCommand("bungeeplayer joinqueue pumpkin");

        } else if (server.equalsIgnoreCase("mushroom")) {

            player.performCommand("bungeeplayer joinqueue mushroom");

        } else if (server.equalsIgnoreCase("lobby-1")) {

            player.performCommand("bungeeplayer joinqueue lobby-1");

        } else if (server.equalsIgnoreCase("lobby-2")) {

            player.performCommand("bungeeplayer joinqueue lobby-2");

        } else if (server.equalsIgnoreCase("lobby-3")) {

            player.performCommand("bungeeplayer joinqueue lobby-3");

        } else {

            player.sendMessage(replaceColors("&c&lERROR: &7Usage: /transfer <melon|pumpkin|mushroom>"));

        }
    }

    public static String getFormat(Player player) {
        return SeedLobby.get().getPermissions().getPrimaryGroup(player);
    }
}
