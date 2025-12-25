package com.racediary.racediary.service;

import com.racediary.racediary.dto.RunDeleteRequest;
import com.racediary.racediary.dto.RunRequest;
import com.racediary.racediary.dto.RunResponse;
import com.racediary.racediary.exception.ResourceNotFoundException;
import com.racediary.racediary.model.RunningRecords;
import com.racediary.racediary.repository.RunRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {

    private final RunRepository repo;

    public RunService(RunRepository repo) {
        this.repo = repo;
    }

    public List<RunResponse> findAll() {
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RunResponse create(RunRequest req) {
        RunningRecords entity = new RunningRecords();
        entity.setDate(req.getDate());
        entity.setDistanceKm(req.getDistanceKm());
        entity.setDurationMinutes(req.getDurationMinutes());
        entity.setNotes(req.getNotes());

        return toResponse(repo.save(entity));
    }

    @Transactional
    public int deleteByFilter(RunDeleteRequest filter) {

        List<RunningRecords> toDelete = repo.findAll().stream()
                .filter(run -> filter.fromDate() == null || !run.getDate().isBefore(filter.fromDate()))
                .filter(run -> filter.toDate() == null || !run.getDate().isAfter(filter.toDate()))
                .filter(run -> filter.minDistance() == null || run.getDistanceKm() >= filter.minDistance())
                .filter(run -> filter.maxDistance() == null || run.getDistanceKm() <= filter.maxDistance())
                .toList();

        repo.deleteAll(toDelete);

        return toDelete.size();
    }

    public void delete(Long id){
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Run not found: " + id);
        }
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    private RunResponse toResponse(RunningRecords r) {
        RunResponse res = new RunResponse();
        res.setId(r.getId());
        res.setDate(r.getDate());
        res.setDistanceKm(r.getDistanceKm());
        res.setDurationMinutes(r.getDurationMinutes());
        res.setNotes(r.getNotes());
        return res;
    }
}