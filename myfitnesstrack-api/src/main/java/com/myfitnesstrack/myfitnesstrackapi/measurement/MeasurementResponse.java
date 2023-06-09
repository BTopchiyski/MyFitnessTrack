package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myfitnesstrack.myfitnesstrackapi.activityLevel.ActivityLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementResponse {
    @JsonProperty("id")
    private Long id;

    private String userEmail;

    @JsonProperty("weightInKilograms")
    private double weightInKilograms;

    @JsonProperty("age")
    private int age;

    @JsonProperty("height")
    private double height;

    @JsonProperty("waistCircumference")
    private double waistCircumference;

    @JsonProperty("hipCircumference")
    private double hipCircumference;

    @JsonProperty("wristCircumference")
    private double wristCircumference;

    @JsonProperty("forearmCircumference")
    private double forearmCircumference;

    @JsonProperty("neckCircumference")
    private double neckCircumference;

    @JsonProperty("activityLevel")
    private ActivityLevel activityLevel;

    private String error;
}
