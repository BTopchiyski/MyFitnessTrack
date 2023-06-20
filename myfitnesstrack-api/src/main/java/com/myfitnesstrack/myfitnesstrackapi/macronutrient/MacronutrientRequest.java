package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

import com.myfitnesstrack.myfitnesstrackapi.calculator.calorie.Calorie;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacronutrientRequest {

    private User user;
    private double proteinGrams;
    private double proteinPercentage;
    private double carbohydrateGrams;
    private double carbohydratePercent;
    private double fatGrams;
    private double fatPercentage;

}
