package xyz.yarinlevi.quapiutils.utils.version.wrappers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import xyz.yarinlevi.quapiutils.utils.version.abstracts.VersionWrapper;

public class Wrapper1_8_R3 implements VersionWrapper {
    private final CraftServer server = (CraftServer) Bukkit.getServer();

    @Override
    public void register(String cmdString, Command command) {
        server.getCommandMap().register(cmdString, command);
    }

    @Override
    public String getVersion() {
        return null;
    }
}
