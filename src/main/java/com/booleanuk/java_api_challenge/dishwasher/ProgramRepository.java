package com.booleanuk.java_api_challenge.dishwasher;

import java.time.LocalDateTime;
import java.util.*;

public class ProgramRepository {
    private final List<Program> programHistory;

    public ProgramRepository() {
        this.programHistory = new ArrayList<>();

        // for testing
        programHistory.add(new Program(ProgramType.ECO50, LocalDateTime.now().minusDays(30)));
        programHistory.add(new Program(ProgramType.INTENSIVE70, LocalDateTime.now().minusDays(28)));
        programHistory.add(new Program(ProgramType.CLEANCYCLE, LocalDateTime.now().minusDays(27)));
        programHistory.add(new Program(ProgramType.HALFLOAD, LocalDateTime.now().minusDays(25)));
    }

    public Map<String, Number> getStatistics() {
        // total / avg. water, electricity
        Map<String, Number> stats = new HashMap<>();
        double totalWater = programHistory.stream().map(Program::getWaterConsumption).reduce(0.0, (a, b) -> a + b);
        double totalElectricity = programHistory.stream().map(Program::getElectricConsumption).reduce(0.0, (a, b) -> a + b);
        int totalRuntime = programHistory.stream().map(Program::getRuntimeMinutes).reduce(0, (a, b) -> a + b);
        double avgWater = totalWater / programHistory.size();
        double avgElectricity = totalElectricity / programHistory.size();
        double avgRuntime = (double) totalRuntime / programHistory.size();

        stats.put("total water consumption", totalWater);
        stats.put("total electricity consumption", totalElectricity);
        stats.put("total runtime", totalRuntime);
        stats.put("average water consumption", avgWater);
        stats.put("average electricity consumption", avgElectricity);
        stats.put("average runtime", avgRuntime);
        return stats;
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
