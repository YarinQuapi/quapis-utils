package xyz.yarinlevi.quapiutils.commands;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import xyz.yarinlevi.quapiutils.commands.fly.ToggleFlyCommand;
import xyz.yarinlevi.quapiutils.commands.privatemessages.commands.MessageCommand;
import xyz.yarinlevi.quapiutils.commands.privatemessages.commands.ReplyCommand;
import xyz.yarinlevi.quapiutils.commands.privatemessages.commands.ToggleMessageCommand;
import xyz.yarinlevi.quapiutils.commands.teleport.TeleportCommand;
import xyz.yarinlevi.quapiutils.utils.version.VersionMatcher;
import xyz.yarinlevi.quapiutils.utils.version.abstracts.VersionWrapper;

import java.util.Arrays;

public class CommandHandler {
    @Getter private final VersionWrapper versionWrapper;


    public CommandHandler() {
        versionWrapper = new VersionMatcher().match();

        Bukkit.getLogger().info("This server is running supported version: " + versionWrapper.getVersion() + "!");

        String fallbackPrefix = "quapiutils"; // eg: "/quapiutils:msg", "/quapiutils:r" ("quapiutils" is fallbackPrefix, "msg" or "r" is the command)

        /*
        Utils commands
         */
        this.register(fallbackPrefix, new ToggleFlyCommand("fly"), "quapiutils.cmd.fly", new String[] {"togglefly"});
        this.register(fallbackPrefix, new TeleportCommand("tp"), "quapiutils.cmd.tp", new String[] {"teleport"});
    
        /*
        Private messages commands
         */
        this.register(fallbackPrefix, new MessageCommand("msg"), "quapiutils.cmd.message", new String[]{"message", "dm", "pm", "privatemessage"});
        this.register(fallbackPrefix, new ReplyCommand("r"), "quapiutils.cmd.reply", new String[] {});
        this.register(fallbackPrefix, new ToggleMessageCommand("togglemsg"),"quapiutils.cmd.togglemsg", new String[]{});
    }

    public void register(String cmdString, Command command, String... aliases) {
        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }

    public void register(String cmdString, Command command, String permission, String... aliases) {
        command.setPermission(permission);

        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }

    public void register(String cmdString, Command command, String permission, String description, String... aliases) {
        command.setPermission(permission);
        command.setDescription(description);

        if (aliases != null) {
            command.setAliases(Arrays.asList(aliases));
        }

        versionWrapper.register(cmdString, command);
    }
}
