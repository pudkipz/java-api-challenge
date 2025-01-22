package com.booleanuk.java_api_challenge.dishwasher;

public enum ProgramType {
    INTENSIVE70 ("Intensive 70", 13.5, 1.35, 150),
    ECO50("Eco 50", 9, 0.65, 60),
    HALFLOAD("Half Load", 10.5, 1.1, 40),
    CLEANCYCLE("Clean Cycle", 14, 1.45, 55);

    private final String name;
    private final double waterConsumption;
    private final double electricConsumption;
    private final int runtimeMinutes;

    ProgramType(String name, double waterConsumption, double electricConsumption, int runtimeMinutes) {
        this.name = name;
        this.waterConsumption = waterConsumption;
        this.electricConsumption = electricConsumption;
        this.runtimeMinutes = runtimeMinutes;
    }

    public String getName() {
        return name;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public double getElectricConsumption() {
        return electricConsumption;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }
}
