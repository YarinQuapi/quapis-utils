package xyz.yarinlevi.quapiutils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.quapiutils.commands.privatemessages.PrivateMessageListener;
import xyz.yarinlevi.quapiutils.commands.CommandHandler;
import xyz.yarinlevi.quapiutils.messages.MessageHandler;
import xyz.yarinlevi.quapiutils.player.PlayerListener;
import xyz.yarinlevi.quapiutils.player.PermissionHandler;
import xyz.yarinlevi.quapiutils.player.PlayerManager;
import xyz.yarinlevi.quapiutils.player.QPlayer;

public final class QuapiUtils extends JavaPlugin {
    @Getter private static QuapiUtils instance;

    @Getter private static MessageHandler messageHandler;
    @Getter private static CommandHandler commandManager;
    @Getter private static PlayerManager playerManager;
    @Getter private static PermissionHandler permissionHandler;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveResource("messages.yml", false);
        messageHandler = new MessageHandler();

        commandManager = new CommandHandler();

        playerManager = new PlayerManager();

        permissionHandler = new PermissionHandler(instance);


        // Listener registration
        Listener[] listeners = new Listener[] {
                new PlayerListener(),
                new PrivateMessageListener(),
                permissionHandler
        };
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }


        // Online player reload fix
        Bukkit.getOnlinePlayers().forEach((player -> playerManager.onlinePlayers.put(player, new QPlayer(player))));
    }

    @Override
    public void onDisable() {
        playerManager.onlinePlayers.forEach((key, value) -> {
            value.save();
        });
    }
}
