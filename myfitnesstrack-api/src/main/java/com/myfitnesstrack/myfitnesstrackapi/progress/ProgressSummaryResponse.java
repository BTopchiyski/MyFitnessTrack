package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressSummaryResponse {
    private double averageCaloriesTaken;
    private double averageWeight;
    private String error;
}
