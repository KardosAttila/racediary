package com.racediary.racediary.repository;

import com.racediary.racediary.model.RunningRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<RunningRecords, Long> {
}
