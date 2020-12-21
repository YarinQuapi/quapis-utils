package xyz.yarinlevi.quapiutils.privatemessages.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.privatemessages.PrivateMessageUtils;

public class ReplyCommand extends Command {
    public ReplyCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, this command only supports players currently.");
            return false;
        }

        Player player = (Player) sender;
        QPlayer p = QuapiUtils.getPlayerManager().onlinePlayers.get(player);

        if (args.length == 0) {
            p.message("NOT_ENOUGH_ARGUMENTS");
            return false;
        } else {
            StringBuilder messageArgs = new StringBuilder();

            for (int i = 0; args.length > i; i++) {
                messageArgs.append(args[i]);
            }

            if (p.getLastMessaged() != null) {
                QPlayer t = p.getLastMessaged();
                if (t.isOnline()) {
                    PrivateMessageUtils.sendPrivateMessage(p, t, messageArgs.toString());
                    return true;
                } else {
                    p.message("NO_RECENT_MSG");
                    p.setLastMessaged(null);
                    return false;
                }
            } else {
                p.message("NO_RECENT_MSG");
            }
        }
        return true;
    }
}
