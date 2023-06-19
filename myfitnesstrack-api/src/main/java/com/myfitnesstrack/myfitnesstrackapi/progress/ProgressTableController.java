package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/progress")
@RequiredArgsConstructor
public class ProgressTableController {

    private final ProgressTableRepository progressTableRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ProgressTableResponse> createProgressEntry(
            @RequestBody ProgressTableRequest request,
            @AuthenticationPrincipal User user
    ) {
        ProgressTable progressEntry = new ProgressTable();
        progressEntry.setWeightInKilograms(request.getWeightInKilograms());
        progressEntry.setCaloriesTaken(request.getCaloriesTaken());
        progressEntry.setDate(request.getDate());
        progressEntry.setUser(user);

        ProgressTable savedEntry = progressTableRepository.save(progressEntry);
        ProgressTableResponse response = ProgressTableResponseMapper.mapProgressEntryToResponse(savedEntry);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProgressTableResponse>> getAllProgressEntries() {
        List<ProgressTable> progressEntries = progressTableRepository.findAll();
        List<ProgressTableResponse> responses = ProgressTableResponseMapper.mapProgressEntriesToList(progressEntries);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressTableResponse> getProgressEntryById(@PathVariable Long id) {
        ProgressTable progressEntry = progressTableRepository.findById(id).orElse(null);
        if (progressEntry != null) {
            ProgressTableResponse response = ProgressTableResponseMapper.mapProgressEntryToResponse(progressEntry);
            return ResponseEntity.ok(response);
        } else {
            ProgressTableResponse errorResponse = ProgressTableResponse.builder()
                    .error("Progress entry not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressTableResponse>> getProgressEntriesByUser(@PathVariable("userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<ProgressTable> progressEntries = progressTableRepository.findByUserId(userId);
        List<ProgressTableResponse> responses = ProgressTableResponseMapper.mapProgressEntriesToList(progressEntries);

        if (responses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/user/{userId}/{entryId}")
    public ResponseEntity<ProgressTableResponse> updateProgressEntry(
            @PathVariable("userId") Long userId,
            @PathVariable("entryId") Long entryId,
            @RequestBody ProgressTableRequest request
    ) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<ProgressTable> optionalProgressEntry = progressTableRepository.findById(entryId);
        if (optionalProgressEntry.isPresent()) {
            ProgressTable progressEntry = optionalProgressEntry.get();

            if (!progressEntry.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            progressEntry.setWeightInKilograms(request.getWeightInKilograms());
            progressEntry.setCaloriesTaken(request.getCaloriesTaken());
            progressEntry.setDate(request.getDate());
            ProgressTable updatedEntry = progressTableRepository.save(progressEntry);
            ProgressTableResponse response = ProgressTableResponseMapper.mapProgressEntryToResponse(updatedEntry);
            return ResponseEntity.ok(response);
        } else {
            ProgressTableResponse errorResponse = ProgressTableResponse.builder()
                    .error("Progress entry not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProgressTableResponse> deleteProgressEntry(@PathVariable Long id) {
        Optional<ProgressTable> optionalProgressEntry = progressTableRepository.findById(id);
        if (optionalProgressEntry.isPresent()) {
            ProgressTable progressEntry = optionalProgressEntry.get();
            User user = progressEntry.getUser();
            user.getProgressTables().remove(progressEntry);
            userRepository.save(user);

            progressTableRepository.delete(progressEntry);
            return ResponseEntity.noContent().build();
        } else {
            ProgressTableResponse errorResponse = ProgressTableResponse.builder()
                    .error("Progress entry not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
