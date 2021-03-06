package pl.mikigal.bytesectors.client.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String fixColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> fixColors(List<String> messages) {
        List<String> colored = new ArrayList<>();
        for (String message : messages) {
            colored.add(fixColors(message));
        }

        return colored;
    }

    public static void sendMessage(CommandSender sender, Object message) {
        sender.sendMessage(fixColors(message.toString()));
    }

    public static void log(Object object) {
        Bukkit.getConsoleSender().sendMessage(fixColors("&4[ByteSectorsClient] &c" + object.toString()));
    }

     public static void log(Exception exception) {
        log("&4&l[EXCEPTION] " + exception.getMessage());
        throw new RuntimeException(exception);
     }
}
