package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/macronutrient")
@RequiredArgsConstructor
public class MacronutrientController {

    private final UserRepository userRepository;
    private final MacronutrientRepository macronutrientRepository;
    private final MacroCalculator macroCalculator;

    @GetMapping
    public ResponseEntity<MacronutrientResponse> getMacronutrients() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Macronutrient macronutrient = user.getMacronutrient();
            if (macronutrient != null) {
                MacronutrientResponse response = getMacronutrientResponse(macronutrient);
                return ResponseEntity.ok(response);
            } else {
                MacronutrientResponse errorResponse = MacronutrientResponse.builder()
                        .error("Macronutrients not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MacronutrientResponse>> getAllMacronutrients() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Measurement measurement = user.getMeasurement();
            macroCalculator.getAllSplits(measurement);
            if( measurement != null) {
                List<MacronutrientSplit> splits = macroCalculator.getAllSplits(measurement);
                List<MacronutrientResponse> responses = splits.stream()
                        .map(this::mapToMacronutrientResponse)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(responses);
            }

            MacronutrientResponse errorResponse = MacronutrientResponse.builder()
                    .error("Measurements not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList(errorResponse));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private MacronutrientResponse mapToMacronutrientResponse(MacronutrientSplit split) {
        MacronutrientResponse response = new MacronutrientResponse();
        response.setProteinGrams(split.getProteins());
        response.setProteinPercentage(split.getProteinPercentage());
        response.setCarbohydrateGrams(split.getCarbohydrates());
        response.setFatGrams(split.getFats());
        response.setFatPercentage(split.getFatPercentage());
        response.setGoal(split.getGoal());

        return response;
    }

    private MacronutrientResponse getMacronutrientResponse(Macronutrient macronutrient) {
        MacronutrientResponse response = MacronutrientResponse.builder()
                .carbohydrateGrams(macronutrient.getCarbohydrates())
                .carbohydratePercent(macronutrient.getCarbohydratePercent())
                .proteinGrams(macronutrient.getProtein())
                .proteinPercentage(macronutrient.getProteinPercent())
                .fatGrams(macronutrient.getFats())
                .fatPercentage(macronutrient.getFatPercent())
                .goal(macronutrient.getGoal())
                .build();
        return response;

    }

    @PostMapping
    public ResponseEntity<MacronutrientResponse> saveMacronutrients(
            @RequestBody MacronutrientRequest macronutrientRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (user.getMacronutrient() != null) {
                macronutrientRepository.delete(user.getMacronutrient());
                Macronutrient macronutrient = createMacronutrients(macronutrientRequest, user);
                macronutrientRepository.save(macronutrient);
                user.setMacronutrient(macronutrient);
                userRepository.save(user);
                MacronutrientResponse response = getMacronutrientResponse(macronutrient);
                return ResponseEntity.ok(response);
            }

            Macronutrient macronutrient = createMacronutrients(macronutrientRequest, user);
            macronutrientRepository.save(macronutrient);
            userRepository.save(user);
            MacronutrientResponse response = getMacronutrientResponse(macronutrient);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Macronutrient createMacronutrients(MacronutrientRequest macronutrientRequest, User user) {
        Macronutrient macronutrient = new Macronutrient();
        macronutrient.setCarbohydrates(macronutrientRequest.getCarbohydrateGrams());
        macronutrient.setCarbohydratePercent(macronutrientRequest.getCarbohydratePercent());
        macronutrient.setProtein(macronutrientRequest.getProteinGrams());
        macronutrient.setProteinPercent(macronutrientRequest.getProteinPercentage());
        macronutrient.setFats(macronutrientRequest.getFatGrams());
        macronutrient.setFatPercent(macronutrientRequest.getFatPercentage());
        macronutrient.setGoal(macronutrientRequest.getGoal());
        macronutrient.setUser(user);
        return macronutrient;
    }

    @DeleteMapping
    public ResponseEntity<MacronutrientResponse> deleteMacronutrients() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Macronutrient macronutrient = macronutrientRepository.findByUserId(user.getId());
            if (macronutrient == null) {
                MacronutrientResponse errorResponse = MacronutrientResponse.builder()
                        .error("Macronutrients not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            macronutrientRepository.delete(macronutrient);
            userRepository.save(user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping
    public ResponseEntity<MacronutrientResponse> updateMacronutrients(
            @RequestBody MacronutrientRequest macronutrientRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (user.getMacronutrient() == null) {
                MacronutrientResponse errorResponse = MacronutrientResponse.builder()
                        .error("Macronutrients not found")
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            Macronutrient macronutrient = createMacronutrients(macronutrientRequest, user);
            user.setMacronutrient(macronutrient);
            userRepository.save(user);

            MacronutrientResponse response = getMacronutrientResponse(macronutrient);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

