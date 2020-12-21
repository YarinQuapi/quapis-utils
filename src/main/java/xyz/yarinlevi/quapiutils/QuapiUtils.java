package xyz.yarinlevi.quapiutils;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.quapiutils.helpers.CommandManager;
import xyz.yarinlevi.quapiutils.messages.MessageHandler;
import xyz.yarinlevi.quapiutils.player.EventListener;
import xyz.yarinlevi.quapiutils.player.PlayerManager;

public final class QuapiUtils extends JavaPlugin {
    @Getter private static QuapiUtils instance;

    @Getter private MessageHandler messageHandler;
    @Getter private CommandManager commandManager;
    @Getter private static PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveResource("messages.yml", false);
        messageHandler = new MessageHandler();

        commandManager = new CommandManager();

        playerManager = new PlayerManager();

        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
