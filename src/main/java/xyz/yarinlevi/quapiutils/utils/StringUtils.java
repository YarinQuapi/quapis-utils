package xyz.yarinlevi.quapiutils.utils;

import org.bukkit.ChatColor;

public class StringUtils {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
