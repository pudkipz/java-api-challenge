package com.booleanuk.java_api_challenge.dishwasher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramRepository {
    private final List<Program> programHistory;
    private LocalDateTime currentProgramStartedAt;

    public ProgramRepository() {
        this.programHistory = new ArrayList<>();
    }

    public Program startProgram(Program program) {  // return bool?
        if (programIsRunning()) {
            return null;
        }
        currentProgramStartedAt = LocalDateTime.now();
        programHistory.add(program);
        return program;
    }

    public Program startProgram(String program) {
        return startProgram(Program.valueOf(program));
    }

    public boolean programIsRunning() {
        LocalDateTime now = LocalDateTime.now();
        return !programHistory.isEmpty()
                && currentProgramStartedAt.plusMinutes(programHistory.getLast().getRuntimeMinutes())
                .isAfter(LocalDateTime.now());
    }

    public List<Program> getAll() {
        return Arrays.asList(Program.values());
    }
}
