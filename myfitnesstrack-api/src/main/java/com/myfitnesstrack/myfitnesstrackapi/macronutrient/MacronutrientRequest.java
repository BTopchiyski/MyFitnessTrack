package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacronutrientRequest {

    private double proteinGrams;
    private double proteinPercentage;
    private double carbohydrateGrams;
    private double carbohydratePercent;
    private double fatGrams;
    private double fatPercentage;

}
