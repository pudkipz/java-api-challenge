package com.booleanuk.java_api_challenge.dishwasher;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Program {
    private final ProgramType programType;
    private final LocalDateTime startedAt;

    public Program(ProgramType programType, LocalDateTime startedAt) {
        this.programType = programType;
        this.startedAt = startedAt;
    }

    public ProgramType getProgramType() {
        return programType;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public double getWaterConsumption() {
        return getProgramType().getWaterConsumption();
    }

    public double getElectricConsumption() {
        return getProgramType().getElectricConsumption();
    }

    public String getName() {
        return getProgramType().getName();
    }

    public int getRuntimeMinutes() {
        return getProgramType().getRuntimeMinutes();
    }

    public long getMinutesLeft(LocalDateTime now) {
        return ChronoUnit.MINUTES.between(now, (startedAt.plusMinutes(getRuntimeMinutes())));
    }
}
