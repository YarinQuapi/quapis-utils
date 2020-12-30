package xyz.yarinlevi.quapiutils.utils.version;

import org.bukkit.Bukkit;
import xyz.yarinlevi.quapiutils.QuapiUtils;
import xyz.yarinlevi.quapiutils.utils.version.abstracts.VersionWrapper;
import xyz.yarinlevi.quapiutils.utils.version.wrappers.*;

import java.util.Arrays;
import java.util.List;

public class VersionMatcher {
    private final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

    private final List<Class<? extends VersionWrapper>> versions = Arrays.asList(
            Wrapper1_16_R3.class, // 1.16.4
            Wrapper1_16_R2.class, // 1.16.3
            Wrapper1_15_R1.class, // 1.15.2
            Wrapper1_14_R3.class, // 1.14.4
            Wrapper1_8_R3.class // 1.8.8
    );

    public VersionWrapper match() {
        try {
            return versions.stream()
                    .filter(version -> version.getSimpleName().substring(7).equals(serverVersion))
                    .findFirst().orElseThrow(() -> new RuntimeException("Your server version is not supported by Quapi's Utils version: " + QuapiUtils.getInstance().getDescription().getVersion() + "!"))
                    .newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }
}