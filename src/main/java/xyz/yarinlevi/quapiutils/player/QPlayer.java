package xyz.yarinlevi.quapiutils.player;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.files.FileUtils;

import java.io.File;
import java.io.IOException;

public class QPlayer implements Listener {
    final protected Player player;

    private final File playerFile;
    private final FileConfiguration data;

    @Getter private boolean isPrivateMessage = true;
    @Nullable @Getter @Setter private QPlayer lastMessaged;

    private final BukkitTask task;


    public QPlayer(Player basePlayer) {
        Bukkit.getServer().getPluginManager().registerEvents(this, QuapiUtils.getInstance());

        this.player = basePlayer;

        this.playerFile = new File(QuapiUtils.getInstance().getDataFolder() + "/players", player.getUniqueId().toString() + ".yml");
        this.data = YamlConfiguration.loadConfiguration(playerFile);

        FileUtils.registerData(playerFile, data);

        boolean isFirstLogin = true;
        if (data.contains("isFirstLogin")) {
            isFirstLogin = data.getBoolean("isFirstLogin");
        }

        if (isFirstLogin) {
            data.set("isFirstLogin", false);
            data.set("privateMessage", isPrivateMessage);
        } else {
            isPrivateMessage = data.getBoolean("privateMessage");
        }

        task = Bukkit.getScheduler().runTaskTimerAsynchronously(QuapiUtils.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    data.save(playerFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0L, 900L);
    }


    public boolean isOnline() {
        return player.isOnline();
    }

    public void message(String messageKey) {
        this.player.sendMessage(QuapiUtils.getInstance().getMessageHandler().getMessage(messageKey));
    }

    public void sendMessage(String message) {
        this.player.sendMessage(message);
    }

    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    /*
    EVENTS
     */

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer() == player) {
            this.task.cancel();
            FileUtils.save(playerFile, data);
        }
    }
}
