package co.aikar.timings.lib;

import org.spigotmc.CustomTimingsHandler;

class SpigotTiming extends CommandTiming {
    private final CustomTimingsHandler timing;

    SpigotTiming(String name) {
        super();
        this.timing = new CustomTimingsHandler(name);
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
