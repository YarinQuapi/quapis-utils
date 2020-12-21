package xyz.yarinlevi.quapiutils.helpers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import xyz.yarinlevi.quapiutils.privatemessages.commands.MessageCommand;
import xyz.yarinlevi.quapiutils.privatemessages.commands.ReplyCommand;
import xyz.yarinlevi.quapiutils.utils.version.VersionMatcher;
import xyz.yarinlevi.quapiutils.utils.version.abstracts.VersionWrapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandManager {
    @Getter private final VersionWrapper versionWrapper;


    public CommandManager() {
        versionWrapper = new VersionMatcher().match();

        Bukkit.getLogger().info("This server is running supported version: " + versionWrapper.getVersion() + "!");

        String fallbackPrefix = "quapi"; // eg: "/quapi:msg", "/quapi:r" ("quapi" is fallbackPrefix, "msg" or "r" is the command)

        this.register(fallbackPrefix, new MessageCommand("msg"), Arrays.asList("message", "dm", "pm", "privatemessage")); // Without aliases nor permission
        this.register(fallbackPrefix, new ReplyCommand("r"), Collections.singletonList("reply")); // With aliases, no permission
    }

    public void register(String cmdString, Command command) {
        versionWrapper.register(cmdString, command);
    }

    public void register(String cmdString, Command command, List<String> aliases) {
        command.setAliases(aliases);

        versionWrapper.register(cmdString, command);
    }
}
