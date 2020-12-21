package xyz.yarinlevi.quapiutils.player;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {
    @Getter public final HashMap<Player, QPlayer> onlinePlayers = new HashMap<>();
}
