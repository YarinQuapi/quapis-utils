package xyz.yarinlevi.quapiutils.privatemessages;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.player.QPlayer;

public class PrivateMessageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean canceled = false;

    private final PrivateMessage privateMessage;

    public PrivateMessageEvent(PrivateMessage privateMessage) {
        this.privateMessage = privateMessage;
    }


    /*
    Setters
     */
    public void setMessage(String message) {
        this.privateMessage.setMessage(message);
    }

    /*
    Getters
     */
    public QPlayer getTo() {
        return this.privateMessage.getTo();
    }

    public String getMessage() {
        return this.privateMessage.getMessage();
    }

    public QPlayer getFrom() {
        return this.privateMessage.getTo();
    }


    public PrivateMessage getPrivateMessage() {
        return privateMessage;
    }

    /*
    Overrides
     */

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
