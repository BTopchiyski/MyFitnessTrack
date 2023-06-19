package com.myfitnesstrack.myfitnesstrackapi.measurement;

import java.util.List;
import java.util.stream.Collectors;

public class MeasurementResponseMapper {

    public static MeasurementResponse mapMeasurementToResponse(Measurement measurement) {
        return MeasurementResponse.builder()
                .id(measurement.getId())
                .userEmail(measurement.getUser().getEmail())
                .weightInKilograms(measurement.getWeightInKilograms())
                .age(measurement.getAge())
                .height(measurement.getHeight())
                .waistCircumference(measurement.getWaistCircumference())
                .hipCircumference(measurement.getHipCircumference())
                .wristCircumference(measurement.getWristCircumference())
                .forearmCircumference(measurement.getForearmCircumference())
                .build();
    }

    public static List<MeasurementResponse> mapMeasurementToResponseToList(List<Measurement> measurements) {
        return measurements.stream()
                .map(MeasurementResponseMapper::mapMeasurementToResponse)
                .collect(Collectors.toList());
    }
}