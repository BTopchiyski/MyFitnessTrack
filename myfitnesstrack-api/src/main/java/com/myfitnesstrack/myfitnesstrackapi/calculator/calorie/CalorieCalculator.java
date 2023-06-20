package com.myfitnesstrack.myfitnesstrackapi.calculator.calorie;

import com.myfitnesstrack.myfitnesstrackapi.calculator.BodyFatCalculator;
import com.myfitnesstrack.myfitnesstrackapi.measurement.Measurement;
import com.myfitnesstrack.myfitnesstrackapi.user.Gender;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CalorieCalculator {

    private final BodyFatCalculator bodyFatCalculator;
    private final UserRepository userRepository;

    public double calculateBMR(Measurement measurement){
        double leanBodyMass = bodyFatCalculator.calculateLeanBodyMass(measurement);
        double activityLevel = measurement.getActivityLevel().getNumericValue();
        double ageFactor = this.calculateAgeFactor(measurement);
        double bmr = 370 - ageFactor + (21.6 * leanBodyMass);
        return bmr  * activityLevel;
    }

    public double calculateAgeFactor(Measurement measurement){
        Optional<User> user = userRepository.findById(measurement.getUser().getId());
        Gender gender = user.get().getGender();
        if(gender.equals(Gender.MALE)){
            return 6.8 * measurement.getAge();
        }
        return 4.7 * measurement.getAge();
    }


    public double calculateMaintainCaloriest(Measurement measurement){
        return Math.round(this.calculateBMR(measurement));
    }

    public double calculateWeightLossCalories(Measurement measurement){
        double maintainCalories = this.calculateBMR(measurement);
        double mildWeightLoss = maintainCalories * 15 / 100;
        return Math.round(maintainCalories - mildWeightLoss);
    }

    public double calculateWeightGainCalories(Measurement measurement){
        double maintainCalories = this.calculateBMR(measurement);
        return Math.round(maintainCalories + 400);
    }

}
