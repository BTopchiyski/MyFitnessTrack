package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final UserRepository userRepository;

    @Transactional
    public Measurement createMeasurement(MeasurementRequest measurementRequest) {
        User user = userRepository.findByEmail(measurementRequest.getUser().getEmail()).orElse(null);
        if (user == null) {
            return null;
        }
        Measurement measurement = Measurement.builder()
                .weightInKilograms(measurementRequest.getWeightInKilograms())
                .age(measurementRequest.getAge())
                .height(measurementRequest.getHeight())
                .waistCircumference(measurementRequest.getWaistCircumference())
                .hipCircumference(measurementRequest.getHipCircumference())
                .neckCircumference(measurementRequest.getNeckCircumference())
                .wristCircumference(measurementRequest.getWristCircumference())
                .forearmCircumference(measurementRequest.getForearmCircumference())
                .activityLevel(measurementRequest.getActivityLevel())
                .build();
        measurement.setUser(user);
        measurementRepository.save(measurement);

        user.setMeasurement(measurement);
        userRepository.save(user);
        return measurement;
    }

    public Measurement getMeasurement(Long id) {
        return measurementRepository.findById(id).orElse(null);
    }

    public Measurement getMeasurementsByUser(Long userId) {
        return measurementRepository.findByUserId(userId);
    }

    @Transactional
    public Measurement updateMeasurement(Long id, MeasurementRequest measurementRequest) {
        Measurement measurement = measurementRepository.findById(id).orElse(null);
        if (measurement == null) {
            return null;
        }
        measurement.setWeightInKilograms(measurementRequest.getWeightInKilograms());
        measurement.setAge(measurementRequest.getAge());
        measurement.setHeight(measurementRequest.getHeight());
        measurement.setWaistCircumference(measurementRequest.getWaistCircumference());
        measurement.setHipCircumference(measurementRequest.getHipCircumference());
        measurement.setWristCircumference(measurementRequest.getWristCircumference());
        measurement.setForearmCircumference(measurementRequest.getForearmCircumference());
        measurement.setActivityLevel(measurementRequest.getActivityLevel());
        Measurement updatedMeasurement = measurementRepository.save(measurement);
        return updatedMeasurement;
    }

    @Transactional
    public boolean deleteMeasurement(Long id) {
        Measurement measurement = measurementRepository.findById(id).orElse(null);
        if (measurement == null) {
            return false;
        }
        measurementRepository.delete(measurement);
        return true;
    }
}
