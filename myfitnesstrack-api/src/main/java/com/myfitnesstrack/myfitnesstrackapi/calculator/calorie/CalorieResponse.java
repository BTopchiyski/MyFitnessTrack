package com.myfitnesstrack.myfitnesstrackapi.calculator.calorie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalorieResponse {

    @JsonProperty("calorie_goal")
    private double calorie;

    private String error;

    public CalorieResponse(double calorieGoal) {
    }
}
