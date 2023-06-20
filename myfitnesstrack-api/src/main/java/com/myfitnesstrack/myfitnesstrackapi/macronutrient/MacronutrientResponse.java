package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacronutrientResponse {

    @JsonProperty("protein_grams")
    private double proteinGrams;
    @JsonProperty("protein_percent")
    private double proteinPercentage;
    @JsonProperty("carbohydrate_grams")
    private double carbohydrateGrams;
    @JsonProperty("carbohydrate_percent")
    private double carbohydratePercent;
    @JsonProperty("fat_grams")
    private double fatGrams;
    @JsonProperty("fat_percent")
    private double fatPercentage;

    private String error;
}
