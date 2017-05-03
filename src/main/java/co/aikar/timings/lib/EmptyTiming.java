package co.aikar.timings.lib;


class EmptyTiming extends CommandTiming {
    EmptyTiming() {
        super();
    }

    @Override
    public final CommandTiming startTiming() {
        return this;
    }

    @Override
    public final void stopTiming() {

    }
}
