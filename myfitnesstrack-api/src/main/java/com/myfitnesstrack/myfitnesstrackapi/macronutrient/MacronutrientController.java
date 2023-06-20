package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

import com.myfitnesstrack.myfitnesstrackapi.measurement.Macronutrient;
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
@RequestMapping("api/v1/macronutrient")
@RequiredArgsConstructor
public class MacronutrientController {

    private final UserRepository userRepository;
    private final MacronutrientRepository macronutrientRepository;

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

    private MacronutrientResponse getMacronutrientResponse(Macronutrient macronutrient) {
        MacronutrientResponse response = MacronutrientResponse.builder()
                .carbohydrateGrams(macronutrient.getCarbohydrates())
                .carbohydratePercent(macronutrient.getCarbohydratePercent())
                .proteinGrams(macronutrient.getProtein())
                .proteinPercentage(macronutrient.getProteinPercent())
                .fatGrams(macronutrient.getFats())
                .fatPercentage(macronutrient.getFatPercent())
                .build();
        return response;

    }

    @PostMapping
    public ResponseEntity<MacronutrientResponse> saveMacronutrients(
            MacronutrientRequest macronutrientRequest
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

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

