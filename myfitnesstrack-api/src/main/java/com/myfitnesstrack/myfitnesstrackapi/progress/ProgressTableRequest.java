package com.myfitnesstrack.myfitnesstrackapi.progress;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressTableRequest {
    private double weight;
    private int calories;
    //private LocalDate date; get date in service

}
