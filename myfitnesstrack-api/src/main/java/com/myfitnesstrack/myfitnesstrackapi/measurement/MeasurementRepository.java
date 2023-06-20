package com.myfitnesstrack.myfitnesstrackapi.measurement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Measurement findByUserId(Long userId);
}
