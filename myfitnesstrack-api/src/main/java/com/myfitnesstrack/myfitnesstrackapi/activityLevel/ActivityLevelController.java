package com.myfitnesstrack.myfitnesstrackapi.activityLevel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/activity-level")
public class ActivityLevelController {

    @GetMapping
    public ResponseEntity<List<ActivityLevelResponse>> getActivityLevels() {
        List<ActivityLevelResponse> activityLevels = new ArrayList<>();
        for (ActivityLevel activityLevel : ActivityLevel.values()) {
            ActivityLevelResponse response = new ActivityLevelResponse();
            response.setActivityLevel(activityLevel.name());
            response.setActivityValue(activityLevel.getActivityLevel());
            activityLevels.add(response);
        }
        return ResponseEntity.ok(activityLevels);
    }
}
