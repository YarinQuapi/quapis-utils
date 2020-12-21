package xyz.yarinlevi.quapiutils.messages;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.files.FileUtils;

import java.io.File;
import java.util.HashMap;

public class MessageHandler {
    private final HashMap<String, String> messages = new HashMap<>();
    @Getter private final File file;
    @Getter private final FileConfiguration data;

    public MessageHandler() {
        file = new File(QuapiUtils.getInstance().getDataFolder(), "messages.yml");
        data = YamlConfiguration.loadConfiguration(file);

        FileUtils.registerData(file, data);
        ConfigurationSection messageSection = data.getConfigurationSection("messages");

        if (messageSection != null) {
            QuapiUtils.getInstance().getLogger().warning("Loading messages..");
            messageSection.getKeys(false).forEach(key -> messages.put(key, ChatColor.translateAlternateColorCodes('&', messageSection.getString(key))));
            QuapiUtils.getInstance().getLogger().info("Successfully loaded " + messages.size() + " messages out of " + messageSection.getKeys(false).size() + " messages.");
        } else {
            QuapiUtils.getInstance().getLogger().severe("No messages were loaded! please check the file for errors.");
        }
    }

    public void testLoadedMessages() {
        StringBuilder stringBuilder = new StringBuilder("Loaded messages: \n");
        messages.forEach((key, value) -> stringBuilder.append(key).append(": ").append(value).append("\n"));
        QuapiUtils.getInstance().getLogger().warning(stringBuilder.toString());
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, "The messageKey: " + key + " is not present in the dictionary.");
    }
}
