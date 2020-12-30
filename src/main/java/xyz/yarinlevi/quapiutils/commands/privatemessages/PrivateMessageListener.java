package xyz.yarinlevi.quapiutils.commands.privatemessages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.yarinlevi.quapiutils.utils.StringUtils;

public class PrivateMessageListener implements Listener {

    @EventHandler
    public void onPrivateMessage(PrivateMessageEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("quapiutils.directmessages.listen")) {
                player.sendMessage(StringUtils.colorize("&6&lDM -> &r&d%s &5>> &b%s&e: &7%s", event.getFrom().getDisplayName(), event.getTo().getDisplayName(), event.getMessage()));
            }
        }
    }
}
