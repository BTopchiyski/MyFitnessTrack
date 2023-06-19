package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressTableResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("weightInKilograms")
    private double weightInKilograms;

    @JsonProperty("caloriesTaken")
    private int caloriesTaken;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String error;
}
