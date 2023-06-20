package com.myfitnesstrack.myfitnesstrackapi.calculator;

import com.myfitnesstrack.myfitnesstrackapi.measurement.Measurement;
import com.myfitnesstrack.myfitnesstrackapi.user.Gender;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BodyFatCalculator {

    private final UserRepository userRepository;

    public double calculateBodyFat(Measurement measurement){
        Optional<User> user = userRepository.findById(measurement.getUser().getId());
        Gender gender = user.get().getGender();
        if(gender.equals(Gender.MALE)){
            return this.calculateBodyFatMale(measurement);
        }
        return this.calculateBodyFatFemale(measurement);
    }

    public double calculateBodyFatMale(Measurement measurement){
        double bfp = (495 / (1.0324 - (0.19077 * Math.log10(measurement.getWaistCircumference() - measurement.getNeckCircumference())) + (0.15456 * Math.log10(measurement.getHeight())))) - 450;
        return bfp;
    }

    public double calculateBodyFatFemale(Measurement measurement){
        double bfp = (495 / (1.29579 - (0.35004 * Math.log10(measurement.getWaistCircumference() + measurement.getHipCircumference() - measurement.getNeckCircumference())) + (0.22100 * Math.log10(measurement.getHeight())))) - 450;
        return bfp;
    }

    public double calculateLeanBodyMass(Measurement measurement){
        double weight = measurement.getWeightInKilograms();
        double bfp = this.calculateBodyFat(measurement);
        double bodyFatInKilograms = (weight * bfp)/100;
        return weight - bodyFatInKilograms;
    }

}
