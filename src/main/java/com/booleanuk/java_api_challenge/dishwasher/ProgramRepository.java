package com.booleanuk.java_api_challenge.dishwasher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramRepository {
    private final List<Program> programHistory;

    public ProgramRepository() {
        this.programHistory = new ArrayList<>();
    }

    public List<Program> getProgramHistory() {
        return programHistory;
    }

    public Program getCurrentProgram(LocalDateTime now) {
        if (programIsRunning(now)) {
            return programHistory.getLast();
        }
        return null;
    }

    public Program cancelProgram(LocalDateTime now) {
        if (programIsRunning(now)) {
            return programHistory.removeLast();
        }
        return null;
    }

    public Program startProgram(ProgramType program, LocalDateTime now) {
        if (programIsRunning(now)) {
            return null;
        }
        Program p = new Program(program, now);
        programHistory.add(p);
        if (programHistory.size() > 150) programHistory.removeFirst();
        return p;
    }

    public Program startProgram(String program, LocalDateTime now) {
        return startProgram(ProgramType.valueOf(program), now);
    }

    public boolean programIsRunning(LocalDateTime now) {
        return !programHistory.isEmpty()
                && programHistory.getLast().getStartedAt().plusMinutes(
                        programHistory.getLast().getProgramType().getRuntimeMinutes())
                .isAfter(now);
    }

    public List<ProgramType> getAll() {
        return Arrays.asList(ProgramType.values());
    }
}
