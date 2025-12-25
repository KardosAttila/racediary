package com.racediary.racediary.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class RunRequest {
    @NotNull
    private LocalDate date;

    @Min(1)
    private double distanceKm;

    @Min(1)
    private int durationMinutes;

    @Size(max = 255)
    private String notes;

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    @Min(1)
    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(@Min(1) double distanceKm) {
        this.distanceKm = distanceKm;
    }

    @Min(1)
    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(@Min(1) int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public @Size(max = 255) String getNotes() {
        return notes;
    }

    public void setNotes(@Size(max = 255) String notes) {
        this.notes = notes;
    }
}
