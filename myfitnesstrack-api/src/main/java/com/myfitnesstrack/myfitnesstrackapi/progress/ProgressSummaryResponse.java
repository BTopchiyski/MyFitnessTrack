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
    @JsonProperty("averageCalories")
    private double averageCaloriesTaken;
    @JsonProperty("averageWeight")
    private double averageWeight;
}
