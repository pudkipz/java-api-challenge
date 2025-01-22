package com.booleanuk.java_api_challenge.dishwasher;

import java.time.LocalDateTime;

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
}
