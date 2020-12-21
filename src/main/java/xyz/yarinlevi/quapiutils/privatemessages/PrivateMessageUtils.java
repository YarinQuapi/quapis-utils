package xyz.yarinlevi.quapiutils.privatemessages;

import org.bukkit.Bukkit;
import xyz.yarinlevi.quapiutils.player.QPlayer;

public class PrivateMessageUtils {
    public static void sendPrivateMessage(QPlayer from, QPlayer to, String message) {
        PrivateMessage privateMessage = new PrivateMessage(from, to, message);

        PrivateMessageEvent event = new PrivateMessageEvent(privateMessage);

        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            event.getPrivateMessage().execute();
        }
    }
}
