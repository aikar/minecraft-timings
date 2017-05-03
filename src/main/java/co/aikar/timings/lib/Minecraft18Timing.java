package co.aikar.timings.lib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * I thought that changing return type was ABI safe, and in 1.9 I changed it from
 * void to Timing....
 *
 * Well I was wrong, so for 1.8 servers, we need to use reflection to get the void return type instead.
 */
class Minecraft18Timing extends MCTiming {
    private final Object timing;
    private static Method startTiming;
    private static Method stopTiming;
    private static Method of;

    static {
        try {
            Class<?> timing = Class.forName("co.aikar.timings.Timing");
            Class<?> timings = Class.forName("co.aikar.timings.Timings");
            startTiming = timing.getDeclaredMethod("startTiming");
            stopTiming = timing.getDeclaredMethod("stopTiming");
            of = timings.getDeclaredMethod("of", Plugin.class, String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            Bukkit.getLogger().severe("Timings18 failed to initialize correctly. Stuff's going to be broken.");
        }
    }

    Minecraft18Timing(Plugin plugin, String name) throws InvocationTargetException, IllegalAccessException {
        super();
        this.timing = of.invoke(null, plugin, name);
    }

    @Override
    public MCTiming startTiming() {
        try {
            if (startTiming != null) {
                startTiming.invoke(timing);
            }
        } catch (IllegalAccessException | InvocationTargetException ignored) {}
        return this;
    }

    @Override
    public void stopTiming() {
        try {
            if (stopTiming != null) {
                stopTiming.invoke(timing);
            }
        } catch (IllegalAccessException | InvocationTargetException ignored) {}
    }
}
