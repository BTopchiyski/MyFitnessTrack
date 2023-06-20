package com.myfitnesstrack.myfitnesstrackapi.calculator.calorie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalorieRequest {

    @JsonProperty("calorie_goal")
    private double calorie;

    private String error;

    public CalorieRequest(double calorieGoal) {
    }
}
