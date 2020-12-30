package xyz.yarinlevi.quapiutils.commands.privatemessages.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.utils.StringUtils;

public class ToggleMessageCommand extends Command {
    public ToggleMessageCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, this command only supports players currently.");
            return false;
        }


        Player player = (Player) sender;
        QPlayer p = QuapiUtils.getPlayerManager().getOnlinePlayers().get(player);

        if (args.length == 0) {
            p.setPrivateMessage(!p.isPrivateMessage());

            p.sendMessage(StringUtils.colorize("&ePrivate messages set to &b%a&e!", p.isPrivateMessage()));
            return true;
        }

        return false;
    }
}
