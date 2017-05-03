package co.aikar.timings.lib;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

enum TimingType {
    SPIGOT() {
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

    MCTiming newTiming(Plugin plugin, String command) {
        return new EmptyTiming();
    }
}
