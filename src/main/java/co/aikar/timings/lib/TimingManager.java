package co.aikar.timings.lib;

import org.bukkit.plugin.Plugin;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class TimingManager {

    private static TimingType timingProvider;
    private static final Object LOCK = new Object();

    private final Plugin plugin;

    private TimingManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("WeakerAccess")
    public static TimingManager of(Plugin plugin) {
        return new TimingManager(plugin);
    }

    @SuppressWarnings("WeakerAccess")
    public MCTiming ofStart(String name) {
        return of(name).startTiming();
    }

    @SuppressWarnings("WeakerAccess")
    public MCTiming of(String name) {
        if (timingProvider == null) {
            synchronized (LOCK) {
                if (timingProvider == null) {
                    try {
                        Class<?> clazz = Class.forName("co.aikar.timings.Timing");
                        Method startTiming = clazz.getMethod("startTiming");
                        if (startTiming.getReturnType() != clazz) {
                            timingProvider = TimingType.MINECRAFT_18;
                        } else {
                            timingProvider = TimingType.MINECRAFT;
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException ignored1) {
                        try {
                            Class.forName("org.spigotmc.CustomTimingsHandler");
                            timingProvider = TimingType.SPIGOT;
                        } catch (ClassNotFoundException ignored2) {
                            timingProvider = TimingType.EMPTY;
                        }
                    }
                }
            }
        }

        return timingProvider.newTiming(plugin, name);
    }
}
