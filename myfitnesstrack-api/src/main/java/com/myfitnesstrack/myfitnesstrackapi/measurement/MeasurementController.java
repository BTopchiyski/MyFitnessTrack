package com.myfitnesstrack.myfitnesstrackapi.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<MeasurementResponse> createMeasurement(@RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = measurementService.createMeasurement(measurementRequest);
        MeasurementResponse measurementResponse = MeasurementResponseMapper.mapMeasurementToResponse(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).body(measurementResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementResponse> getMeasurement(@PathVariable Long id) {
        Measurement measurement = measurementService.getMeasurement(id);
        if (measurement != null) {
            MeasurementResponse measurementResponse = MeasurementResponseMapper.mapMeasurementToResponse(measurement);
            return ResponseEntity.ok(measurementResponse);
        } else {
            MeasurementResponse errorResponse = MeasurementResponse.builder()
                    .error("Measurement not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MeasurementResponse>> getMeasurementsByUser(@PathVariable Long userId) {
        List<Measurement> measurements = measurementService.getMeasurementsByUser(userId);
        if (measurements == null) {
            MeasurementResponse errorResponse = MeasurementResponse.builder()
                    .error("No measurements found for user with ID: " + userId)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList(errorResponse));
        }
        List<MeasurementResponse> measurementResponseList = MeasurementResponseMapper.mapMeasurementToResponseToList(measurements);
        return ResponseEntity.ok(measurementResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasurementResponse> updateMeasurement(
            @PathVariable Long id, @RequestBody MeasurementRequest measurementRequest) {
        Measurement measurement = measurementService.updateMeasurement(id, measurementRequest);
        if (measurement != null) {
            MeasurementResponse measurementResponse = MeasurementResponseMapper.mapMeasurementToResponse(measurement);
            return ResponseEntity.ok(measurementResponse);
        } else {
            MeasurementResponse errorResponse = MeasurementResponse.builder()
                    .error("Measurement not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MeasurementResponse> deleteMeasurement(@PathVariable Long id) {
        boolean deleted = measurementService.deleteMeasurement(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            MeasurementResponse errorResponse = MeasurementResponse.builder()
                    .error("Measurement not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}