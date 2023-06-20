package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<MeasurementResponse> createMeasurement(
            @RequestBody MeasurementRequest measurementRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            measurementRequest.setUser(user);
        }
        Measurement measurement = measurementService.createMeasurement(measurementRequest);
        MeasurementResponse measurementResponse = MeasurementResponseMapper.mapMeasurementToResponse(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).body(measurementResponse);
    }

    @GetMapping
    public ResponseEntity<MeasurementResponse> getMeasurement()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Measurement measurement = measurementService.getMeasurementsByUser(user.getId());
            if (measurement != null) {
                MeasurementResponse measurementResponse = MeasurementResponseMapper.mapMeasurementToResponse(measurement);
                return ResponseEntity.ok(measurementResponse);
            } else {
                MeasurementResponse errorResponse = MeasurementResponse.builder()
                        .error("Measurement not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasurementResponse> updateMeasurement(
            @PathVariable Long id,
            @RequestBody MeasurementRequest measurementRequest
    ) {
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
    public ResponseEntity<MeasurementResponse> deleteMeasurement(
            @PathVariable Long id
    ) {
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
