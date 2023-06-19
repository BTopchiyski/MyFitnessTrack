package com.myfitnesstrack.myfitnesstrackapi.progress;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressTableRequest {
    private double weightInKilograms;
    private int caloriesTaken;
    private LocalDate date;

}
