package xyz.yarinlevi.quapiutils.utils.version.abstracts;

import org.bukkit.command.Command;

public interface VersionWrapper {
    void register(String cmdString, Command command);

    void register(String cmdString, Command command, String[] aliases);

    String getVersion();
}
