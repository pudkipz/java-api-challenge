package com.booleanuk.java_api_challenge.dishwasher;

public class ProgramDoNotUse {
    private final String name;
    private final double waterConsumption;
    private final double electricConsumption;
    private final int runtime;  // minutes

    public ProgramDoNotUse(String name, double waterConsumption, double electricConsumption, int runtime) {
        this.name = name;
        this.waterConsumption = waterConsumption;
        this.electricConsumption = electricConsumption;
        this.runtime = runtime;
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

    public int getRuntime() {
        return runtime;
    }
}
