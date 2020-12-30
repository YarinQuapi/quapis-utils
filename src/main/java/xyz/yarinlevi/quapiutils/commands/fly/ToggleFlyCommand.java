package xyz.yarinlevi.quapiutils.commands.fly;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.utils.StringUtils;

public class ToggleFlyCommand extends Command {
    public ToggleFlyCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sorry, this command must be executed by a player.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.hasPermission("quapiutils.fly.toggled")) {
                QPlayer p = QuapiUtils.getPlayerManager().getOnlinePlayers().get(player);
                p.setFlyToggled(!player.getAllowFlight());
                player.setAllowFlight(p.isFlyToggled());
                p.sendMessage(StringUtils.colorize("&Fly was set to &b%a&e!", p.isPrivateMessage()));
            } else {
                player.setAllowFlight(!player.getAllowFlight());
                player.sendMessage(StringUtils.colorize("&Fly was set to &b%a&e!", player.getAllowFlight()));
            }
            return true;
        }

        return false;
    }
}
