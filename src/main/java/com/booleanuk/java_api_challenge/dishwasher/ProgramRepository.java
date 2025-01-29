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

    private double getTotalWaterConsumption() {
        return programHistory.stream().map(Program::getWaterConsumption).reduce(0.0, (a, b) -> a + b);
    }

    private double getTotalElectricityConsumption() {
        return programHistory.stream().map(Program::getElectricConsumption).reduce(0.0, (a, b) -> a + b);
    }

    private int getTotalRuntime() {
        return programHistory.stream().map(Program::getRuntimeMinutes).reduce(0, (a, b) -> a + b);
    }

    public Map<String, Number> getStatistics() {
        // total / avg. water, electricity
        Map<String, Number> stats = new HashMap<>();
        double avgWater = getTotalWaterConsumption() / programHistory.size();
        double avgElectricity = getTotalElectricityConsumption() / programHistory.size();
        double avgRuntime = (double) getTotalRuntime() / programHistory.size();

        stats.put("total water consumption", getTotalWaterConsumption());
        stats.put("total electricity consumption", getTotalElectricityConsumption());
        stats.put("total runtime", getTotalRuntime());
        stats.put("average water consumption", avgWater);
        stats.put("average electricity consumption", avgElectricity);
        stats.put("average runtime", avgRuntime);
        return stats;
    }

    public List<Program> getProgramHistory() {
        return programHistory;
    }

    public ProgramResponse getCurrentProgram(LocalDateTime now) {
        ProgramResponse response = new ProgramResponse(null);
        if (programIsRunning(now)) {
            response = new ProgramResponse(programHistory.getLast());
        }

        // Rinse aid
        if (getTotalWaterConsumption() >= 40) {
            response.addMessage("Out of Rinse Aid!");
        } else if (getTotalWaterConsumption() > 30) {
            response.addMessage("Rinse Aid low. Amount left: " + (1 - (getTotalWaterConsumption() / 40)) + "L.");
        }

        // Salt
        if (getTotalWaterConsumption() >= 60) response.addMessage("Out of salt!");
        else if (getTotalWaterConsumption() > 50) {
            response.addMessage("Salt levels low. Amount left: " + (3 - (getTotalWaterConsumption() / 60)) + "L.");
        }

        // Tablets
        if (programHistory.size() >= 63) {
            response.addMessage("Out of tablets!");
        }
        else if (programHistory.size() > 58) {
            response.addMessage("Almost of out tablets. Number of tablets left: " + (63 - programHistory.size()));
        }

        // Clean cycle
        int timeSinceClean = 0;
        for (Program p : programHistory.reversed()) {
            timeSinceClean += p.getRuntimeMinutes();
            if (p.getProgramType() == ProgramType.CLEANCYCLE) {
                break;
            }
        }
        if (timeSinceClean / 60 >= 50) {
            response.addMessage("Clean cycle recommended. Time since last CC: " + timeSinceClean / 60);
        }

        return response;
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
