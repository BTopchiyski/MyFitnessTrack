package com.myfitnesstrack.myfitnesstrackapi.progress;

import java.util.List;
import java.util.stream.Collectors;

public class ProgressTableResponseMapper {

    public static ProgressTableResponse mapProgressEntryToResponse(ProgressTable progressEntry) {
        return ProgressTableResponse.builder()
                .weight(progressEntry.getWeightInKilograms())
                .calories(progressEntry.getCaloriesTaken())
                .build();
    }

    public static List<ProgressTableResponse> mapProgressEntriesToList(List<ProgressTable> progressEntries) {
        return progressEntries.stream()
                .map(ProgressTableResponseMapper::mapProgressEntryToResponse)
                .collect(Collectors.toList());
    }
}
