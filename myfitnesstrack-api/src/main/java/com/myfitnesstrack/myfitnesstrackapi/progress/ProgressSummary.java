package com.myfitnesstrack.myfitnesstrackapi.progress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgressSummary {
    private double averageCaloriesTaken;
    private double averageWeight;
}
