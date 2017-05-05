package co.aikar.timings.lib;

import org.bukkit.Bukkit;
import org.spigotmc.CustomTimingsHandler;

class SpigotTiming extends MCTiming {
    private final CustomTimingsHandler timing;

    SpigotTiming(String name) {
        super();
        this.timing = new CustomTimingsHandler(name);
    }

    @Override
    public MCTiming startTiming() {
        if (Bukkit.isPrimaryThread()) {
            timing.startTiming();
        }
        return this;
    }

    @Override
    public void stopTiming() {
        if (Bukkit.isPrimaryThread()) {
            timing.stopTiming();
        }
    }
}
