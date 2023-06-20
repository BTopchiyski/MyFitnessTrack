package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressTableResponse {
    private double weight;
    private int calories;

    private String error;
}
