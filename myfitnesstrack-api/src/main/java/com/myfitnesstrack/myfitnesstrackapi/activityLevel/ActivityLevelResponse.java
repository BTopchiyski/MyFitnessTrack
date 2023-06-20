package com.myfitnesstrack.myfitnesstrackapi.activityLevel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityLevelResponse {
    private String activityLevel;
    private String activityValue;
}
