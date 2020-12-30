package xyz.yarinlevi.quapiutils.commands.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.player.QPlayer;
import xyz.yarinlevi.quapiutils.utils.StringUtils;

public class TeleportCommand extends Command {
    public TeleportCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        QPlayer p = QuapiUtils.getPlayerManager().onlinePlayers.get(player);

        if (args.length == 0) {
            p.message("NOT_ENOUGH_ARGUMENTS");
            return false;
        } else if (args.length == 1) {
            String playerName = args[0];

            if (!playerName.equalsIgnoreCase(player.getName())) {
                if (Bukkit.getPlayer(playerName) != null) {
                    player.teleport(Bukkit.getPlayer(playerName));
                    p.sendMessage("Teleported to " + playerName);
                    return true;
                } else {
                    p.message("ERR_PLAYER_NOT_FOUND");
                }
            } else {
                p.message("ERR_PLAYER_TP_SELF");
            }
        } else if (args.length == 3) {
            if (StringUtils.isInteger(args[0]) && StringUtils.isInteger(args[1]) && StringUtils.isInteger(args[2])) {
                int[] xyz = new int[3];
                xyz[0] = Integer.parseInt(args[0]);
                xyz[1] = Integer.parseInt(args[1]);
                xyz[2] = Integer.parseInt(args[2]);

                player.teleport(new Location(player.getWorld(), xyz[0], xyz[1], xyz[2]));
                p.sendMessage("Teleported to %s %s %s", xyz[0], xyz[1], xyz[2]);
                return true;
            } else {
                p.sendMessage("One or more of your arguments is invalid, please check again.");
                return false;
            }
        }

        return true;
    }
}
