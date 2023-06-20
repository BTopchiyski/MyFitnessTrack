package com.myfitnesstrack.myfitnesstrackapi.calculator;

import com.myfitnesstrack.myfitnesstrackapi.measurement.Measurement;
import com.myfitnesstrack.myfitnesstrackapi.user.Gender;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MacroCalcolator {

    UserRepository userRepository;
    CalorieCalculator calorieCalculator;

    public MacronutrientSplit getWeightLossSplit(Measurement measurement){
        double calories = calorieCalculator.calculateWeightLossCalories(measurement);
        MacronutrientSplit macronutrientSplit = new MacronutrientSplit();
        Gender gender = this.getGender(measurement);
        macronutrientSplit.setCalories(calories);

        if(gender.equals(Gender.MALE)){
            setPercentages(macronutrientSplit, 40, 40, 20);
        } else if (gender.equals(Gender.FEMALE)){
            setPercentages(macronutrientSplit, 35, 35, 30);
        }
        return this.setValues(macronutrientSplit);
    }

    public MacronutrientSplit getWeightGainSplit(Measurement measurement){
        double calories = calorieCalculator.calculateWeightGainCalories(measurement);
        MacronutrientSplit macronutrientSplit = new MacronutrientSplit();
        Gender gender = this.getGender(measurement);
        macronutrientSplit.setCalories(calories);

        if(gender.equals(Gender.MALE)){
            setPercentages(macronutrientSplit, 30, 40, 30);
        } else if (gender.equals(Gender.FEMALE)){
            setPercentages(macronutrientSplit, 30, 40, 30);
        }
        return this.setValues(macronutrientSplit);
    }

    public MacronutrientSplit getWeightMaintainSplit(Measurement measurement){
        double calories = calorieCalculator.calculateMaintainCaloriest(measurement);
        MacronutrientSplit macronutrientSplit = new MacronutrientSplit();
        Gender gender = this.getGender(measurement);
        macronutrientSplit.setCalories(calories);

        if(gender.equals(Gender.MALE)){
            setPercentages(macronutrientSplit, 35, 35, 30);
        } else if (gender.equals(Gender.FEMALE)){
            setPercentages(macronutrientSplit, 25, 40, 35);
        }
        return this.setValues(macronutrientSplit);
    }

    private void setPercentages(MacronutrientSplit macronutrientSplit, double proteinPercentage, double carbohydratePercentage, double fatPercentage) {
        macronutrientSplit.setProteinPercentage(proteinPercentage);
        macronutrientSplit.setCarbohydratePercentage(carbohydratePercentage);
        macronutrientSplit.setFatPercentage(fatPercentage);
    }

    private MacronutrientSplit setValues(MacronutrientSplit macronutrientSplit) {
        macronutrientSplit.setProteins();
        macronutrientSplit.setCarbohydrates();
        macronutrientSplit.setFats();
        return macronutrientSplit;
    }

    public Gender getGender(Measurement measurement){
        Optional<User> user = userRepository.findById(measurement.getUser().getId());
        return user.get().getGender();
    }
}
