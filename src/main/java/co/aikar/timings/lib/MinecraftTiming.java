package co.aikar.timings.lib;

import co.aikar.timings.Timing;
import co.aikar.timings.Timings;
import org.bukkit.plugin.Plugin;

class MinecraftTiming extends CommandTiming {
    private final Timing timing;
    MinecraftTiming(Plugin plugin, String name) {
        super();
        this.timing = Timings.of(plugin, name);
    }

    @Override
    public CommandTiming startTiming() {
        timing.startTiming();
        return this;
    }

    @Override
    public void stopTiming() {
        timing.stopTiming();
    }
}
