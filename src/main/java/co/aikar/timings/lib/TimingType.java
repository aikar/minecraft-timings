package co.aikar.timings.lib;

import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

enum TimingType {
    SPIGOT() {
        @Override
        CommandTiming newTiming(Plugin plugin, String command) {
            return new SpigotTiming(command);
        }
    },
    MINECRAFT() {
        @Override
        CommandTiming newTiming(Plugin plugin, String command) {
            return new MinecraftTiming(plugin, command);
        }
    },
    MINECRAFT_18() {
        @Override
        CommandTiming newTiming(Plugin plugin, String command) {
            try {
                return new Minecraft18Timing(plugin, command);
            } catch (InvocationTargetException | IllegalAccessException e) {
                return new EmptyTiming();
            }
        }
    },
    EMPTY();

    CommandTiming newTiming(Plugin plugin, String command) {
        return new EmptyTiming();
    }
}
