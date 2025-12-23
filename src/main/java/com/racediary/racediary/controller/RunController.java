package com.racediary.racediary.controller;

import com.racediary.racediary.model.RunningRecords;
import com.racediary.racediary.repository.RunRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/api/runs/get")
    public List<RunningRecords> getAll() {
        return runRepository.findAll();
    }

    @PostMapping("/api/runs/create")
    public RunningRecords create(@RequestBody RunningRecords run) {
        return runRepository.save(run);
    }

    @DeleteMapping("/api/runs/deleteAll")
    public String deleteAll() {
        runRepository.deleteAll();
        return "All running records deleted!";
    }

    @DeleteMapping("/api/runs/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        if (!runRepository.existsById(id)) {
            return "Running record with ID " + id + " not found!";
        }
        runRepository.deleteById(id);
        return "Running record with ID " + id + " deleted!";
    }
}
