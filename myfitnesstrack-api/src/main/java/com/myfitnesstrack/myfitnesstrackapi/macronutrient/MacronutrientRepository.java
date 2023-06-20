package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MacronutrientRepository extends JpaRepository<Macronutrient, Long> {
    Macronutrient findByUserId(Long id);
}
