package co.aikar.timings.lib;

abstract public class CommandTiming implements AutoCloseable {
    public abstract CommandTiming startTiming();
    public abstract void stopTiming();

    @Override
    public void close() {
        stopTiming();
    }
}

