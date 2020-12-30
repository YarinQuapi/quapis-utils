package xyz.yarinlevi.quapiutils.utils;

import org.bukkit.ChatColor;

public class StringUtils {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorize(String message, Object... args) {
        return String.format(ChatColor.translateAlternateColorCodes('&', message), args);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
