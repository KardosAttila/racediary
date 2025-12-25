package com.racediary.racediary.dto;

import java.time.LocalDate;

public record RunDeleteRequest(
        LocalDate fromDate,
        LocalDate toDate,
        Double minDistance,
        Double maxDistance
) {}
