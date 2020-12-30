package xyz.yarinlevi.quapiutils.commands.privatemessages;

import lombok.Getter;
import lombok.Setter;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.utils.StringUtils;

public class PrivateMessage {
    @Getter private final QPlayer from;
    @Getter private final QPlayer to;
    @Getter @Setter private String message;

    public PrivateMessage(QPlayer from1, QPlayer to1, String message) {
        this.from = from1;
        this.to = to1;
        this.message = message;
    }

    public void execute() {
        if (from.isPrivateMessage() && to.isPrivateMessage()) {
            from.setLastMessaged(to);
            to.setLastMessaged(from);

            from.sendMessage(StringUtils.colorize(String.format("&r%s &5>> &r%s&e: &7%s", from.getDisplayName(), to.getDisplayName(), message)));
            to.sendMessage(StringUtils.colorize(String.format("&r%s &5<< &r%s&e: &7%s", to.getDisplayName(), from.getDisplayName(), message)));
        }
    }
}
