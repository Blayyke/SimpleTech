package me.xa5.simpletech;

public enum MachineStatus {
    /**
     * Machine has power, but there may not be an available output slot available, or another requirement was not met.
     */
    IDLE,

    /**
     * Machine has power, and all requirements are met, and the machine is currently processing an input.
     */
    PROCESSING,

    /**
     * Machine has no power and cannot run.
     */
    OUT_OF_POWER,
}