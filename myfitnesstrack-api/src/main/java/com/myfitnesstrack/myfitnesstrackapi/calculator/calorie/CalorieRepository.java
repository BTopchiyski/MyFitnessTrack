package com.myfitnesstrack.myfitnesstrackapi.calculator.calorie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalorieRepository extends JpaRepository<Calorie, Long> {
    Calorie findByUserId(Long id);
}
