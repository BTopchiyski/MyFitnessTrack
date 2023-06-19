package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementRequest {
    private User user;
    private double weightInKilograms;
    private int age;
    private double height;
    private double waistCircumference;
    private double hipCircumference;
    private double wristCircumference;
    private double forearmCircumference;
}
