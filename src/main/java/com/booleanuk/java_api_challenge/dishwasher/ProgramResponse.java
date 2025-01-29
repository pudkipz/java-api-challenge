package com.booleanuk.java_api_challenge.dishwasher;

import java.util.ArrayList;
import java.util.List;

public class ProgramResponse {
    private Program program;
    private List<String> messages;

    public ProgramResponse(Program program) {
        this.program = program;
        messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
