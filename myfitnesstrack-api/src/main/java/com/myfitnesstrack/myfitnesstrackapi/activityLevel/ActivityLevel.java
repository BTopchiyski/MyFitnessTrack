package com.myfitnesstrack.myfitnesstrackapi.activityLevel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ActivityLevel {

    SEDENTARY("1.2"),
    LIGHTLY_ACTIVE("1.375"),
    MODERATELY_ACTIVE("1.55"),
    VERY_ACTIVE("1.725"),
    EXTRA_ACTIVE("1.9");

    @Getter
    private final String activityLevel;

    public double getNumericValue() {
        return Double.parseDouble(activityLevel);
    }
}
