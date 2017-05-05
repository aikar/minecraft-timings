package co.aikar.timings.lib;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

enum TimingType {
    SPIGOT(true) {
        @Override
        MCTiming newTiming(Plugin plugin, String command) {
            return new SpigotTiming(command);
        }
    },
    MINECRAFT() {
        @Override
        MCTiming newTiming(Plugin plugin, String command) {
            return new MinecraftTiming(plugin, command);
        }
    },
    MINECRAFT_18() {
        @Override
        MCTiming newTiming(Plugin plugin, String command) {
            try {
                return new Minecraft18Timing(plugin, command);
            } catch (InvocationTargetException | IllegalAccessException e) {
                return new EmptyTiming();
            }
        }
    },
    EMPTY();

    private final boolean useCache;

    public boolean useCache() {
        return useCache;
    }

    TimingType() {
        this(false);
    }
    TimingType(boolean useCache) {
        this.useCache = useCache;
    }

    MCTiming newTiming(Plugin plugin, String command) {
        return new EmptyTiming();
    }
}
