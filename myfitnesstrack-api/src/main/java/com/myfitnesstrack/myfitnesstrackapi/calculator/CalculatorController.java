package com.myfitnesstrack.myfitnesstrackapi.calculator;

import com.myfitnesstrack.myfitnesstrackapi.calculator.calorie.*;
import com.myfitnesstrack.myfitnesstrackapi.measurement.Measurement;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/calculate")
@RequiredArgsConstructor
public class CalculatorController {

    private final UserRepository userRepository;
    private final CalorieCalculator calculator;
    private final CalorieRepository repository;

    @PostMapping
    public ResponseEntity<CalorieResponse> createCalorieGoal(
            @RequestBody CalorieRequest calorieRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            double calorieGoal = calorieRequest.getCalorie();
            Calorie calorie = new Calorie();
            calorie.setUser(user);
            calorie.setCalories(calorieGoal);
            user.setCalorieGoal(calorie);
            repository.save(calorie);
            userRepository.save(user);

            CalorieResponse calorieResponse = new CalorieResponse(calorieGoal);
            return ResponseEntity.status(HttpStatus.CREATED).body(calorieResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<CalorieResponse> deleteCalorieGoal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Calorie existingCalorie = repository.findByUserId(user.getId());
            if (existingCalorie == null) {
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Calorie goal not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            repository.delete(existingCalorie);
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping
    public ResponseEntity<CalorieResponse> updateCalorieGoal(@RequestBody CalorieRequest calorieRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (user.getCalorieGoal() == null) {
                // Calorie goal not found, return error response
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Calorie goal not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            double calorieGoal = calorieRequest.getCalorie();
            user.getCalorieGoal().setCalories(calorieGoal);
            userRepository.save(user);

            CalorieResponse response = CalorieResponse.builder()
                    .calorie(calorieGoal)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/current")
    public ResponseEntity<CalorieResponse> getCalorieGoal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Optional<Double> calorieGoalOptional = Optional.ofNullable(user.getCalorieGoal())
                    .map(Calorie::getCalories);

            if (calorieGoalOptional.isPresent()) {
                double calorieGoal = calorieGoalOptional.get();
                CalorieResponse response = CalorieResponse.builder()
                        .calorie(calorieGoal)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Calorie goal not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/maintain")
    public ResponseEntity<CalorieResponse> getCalorieMaintain() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Measurement measurement = user.getMeasurement();
            if (measurement != null){
                double calorieGoal = calculator.calculateMaintainCaloriest(measurement);
                CalorieResponse response = CalorieResponse.builder()
                        .calorie(calorieGoal)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Measurements not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/gain")
    public ResponseEntity<CalorieResponse> getCalorieGain() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Measurement measurement = user.getMeasurement();
            if (measurement != null){
                double calorieGoal = calculator.calculateMaintainCaloriest(measurement);
                CalorieResponse response = CalorieResponse.builder()
                        .calorie(calorieGoal)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Measurements not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/lose")
    public ResponseEntity<CalorieResponse> getCalorieLose() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Measurement measurement = user.getMeasurement();
            if (measurement != null){
                double calorieGoal = calculator.calculateMaintainCaloriest(measurement);
                CalorieResponse response = CalorieResponse.builder()
                        .calorie(calorieGoal)
                        .build();
                return ResponseEntity.ok(response);
            } else {
                CalorieResponse errorResponse = CalorieResponse.builder()
                        .error("Measurements not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
