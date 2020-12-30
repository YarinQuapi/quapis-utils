package xyz.yarinlevi.quapiutils.commands.privatemessages;

import org.bukkit.Bukkit;
import xyz.yarinlevi.quapiutils.player.QPlayer;

public class PrivateMessageUtils {
    public static void sendPrivateMessage(QPlayer from, QPlayer to, String message) {
        PrivateMessage privateMessage = new PrivateMessage(from, to, message);

        if (privateMessage.getFrom().isPrivateMessage() && privateMessage.getTo().isPrivateMessage()) {

            PrivateMessageEvent event = new PrivateMessageEvent(privateMessage);

            Bukkit.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                event.getPrivateMessage().execute();
            }
        } else {
            if (!privateMessage.getFrom().isPrivateMessage()) {
                privateMessage.getFrom().sendMessage("You have private messages disabled!");
            } else if (!privateMessage.getTo().isPrivateMessage()) {
                privateMessage.getFrom().sendMessage("%s has private messages disabled!", privateMessage.getTo().getDisplayName());
            }
        }
    }
}
