package xyz.yarinlevi.quapiutils.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yarinlevi.quapiutils.QuapiUtils;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        QPlayer player = new QPlayer(p);

        QuapiUtils.getPlayerManager().onlinePlayers.put(p, player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        QuapiUtils.getPlayerManager().onlinePlayers.remove(p);
    }
}
