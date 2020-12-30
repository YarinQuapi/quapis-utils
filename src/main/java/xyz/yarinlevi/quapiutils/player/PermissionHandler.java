package xyz.yarinlevi.quapiutils.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import xyz.yarinlevi.quapiutils.QuapiUtils;

import java.util.HashMap;
import java.util.UUID;

public class PermissionHandler implements Listener {
    private final HashMap<UUID, PermissionAttachment> createdPermissionAttachments = new HashMap<>();
    private final QuapiUtils plugin;

    public PermissionHandler(QuapiUtils plugin) {
        this.plugin = plugin;
    }

    public void setPermission(Player player, String permission, boolean value) {
        getPermissionHandlerForPlayer(player).setPermission(permission, value);
    }

    public boolean getPermissionValue(Player player, String permission) {
        return getPermissionHandlerForPlayer(player).getPermissions().getOrDefault(permission, false);
    }

    private PermissionAttachment getPermissionHandlerForPlayer(Player player) {
        if (!isPlayerInHandler(player)) {
            return createdPermissionAttachments.put(player.getUniqueId(), player.addAttachment(plugin));
        }
        return createdPermissionAttachments.get(player.getUniqueId());
    }

    public void removePlayerFromPermissionHandler(Player player) {
        if (isPlayerInHandler(player)) {
            createdPermissionAttachments.remove(player.getUniqueId());
        }
    }

    private boolean isPlayerInHandler(Player player) {
        return createdPermissionAttachments.containsKey(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        QuapiUtils.getPermissionHandler().removePlayerFromPermissionHandler(event.getPlayer());
    }
}
