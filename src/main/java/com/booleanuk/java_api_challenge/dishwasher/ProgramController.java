package com.booleanuk.java_api_challenge.dishwasher;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dishwasher")
public class ProgramController {
    ProgramRepository repository;

    public ProgramController(ProgramRepository repository) {
        this.repository = repository;
    }

    public ProgramController() {
        this.repository = new ProgramRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProgramType> getAll() {  // make String?
        return repository.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Program startProgram(@RequestParam(name="program") String program) {
        return repository.startProgram(program, LocalDateTime.now());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public Program cancelProgram() {
        return repository.cancelProgram(LocalDateTime.now());
    }


}

