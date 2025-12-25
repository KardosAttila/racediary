package com.racediary.racediary.controller;

import com.racediary.racediary.dto.RunDeleteRequest;
import com.racediary.racediary.dto.RunRequest;
import com.racediary.racediary.dto.RunResponse;
import com.racediary.racediary.service.RunService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RunController {

    private final RunService service;

    public RunController(RunService service) {
        this.service = service;
    }

    @GetMapping("/api/runs/get")
    public ResponseEntity<List<RunResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/api/runs/create")
    public ResponseEntity<RunResponse> create(@Valid @RequestBody RunRequest request) {
        RunResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/runs/" + created.getId()))
                .body(created);
    }

    @DeleteMapping("/api/runs/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/runs/delete")
    public ResponseEntity<Integer> deleteByFilter(@RequestBody RunDeleteRequest filter) {
        int deleted = service.deleteByFilter(filter);
        return ResponseEntity.ok(deleted);
    }

    @DeleteMapping("/api/runs/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}