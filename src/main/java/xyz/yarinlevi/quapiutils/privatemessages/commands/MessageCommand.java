package xyz.yarinlevi.quapiutils.privatemessages.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.privatemessages.PrivateMessageUtils;

public class MessageCommand extends Command {
    public MessageCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        QPlayer p = QuapiUtils.getPlayerManager().onlinePlayers.get(player);

        if (args.length > 1) {
            if (Bukkit.getPlayer(args[0]) != null) {

                QPlayer t = QuapiUtils.getPlayerManager().onlinePlayers.get(Bukkit.getPlayer(args[0]));

                if (t != p) {

                    StringBuilder messageArgs = new StringBuilder();

                    for (int i = 1; args.length > i; i++) {
                        messageArgs.append(args[i]);
                    }

                    PrivateMessageUtils.sendPrivateMessage(p, t, messageArgs.toString());
                    return true;
                } else {
                    p.message("ERR_MSG_SELF");
                }
            } else {
                p.message("ERR_PLAYER_NOT_FOUND");
            }
        } else {
            p.message("NOT_ENOUGH_ARGUMENTS");
        }

        return true;
    }
}
