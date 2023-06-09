package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/progress")
@RequiredArgsConstructor
public class ProgressTableController {

    private final ProgressTableService progressTableService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ProgressTableResponse> createProgressEntry(
            @RequestBody ProgressTableRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            try{
                ProgressTable savedEntry = progressTableService.createProgressEntry(request, user);
                ProgressTableResponse response = ProgressTableResponseMapper.mapProgressEntryToResponse(savedEntry);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (IllegalArgumentException e) {
                ProgressTableResponse errorResponse = ProgressTableResponse.builder()
                        .error(e.getMessage())
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProgressTableResponse>> getAllProgressEntries() {
        List<ProgressTable> progressEntries = progressTableService.getAllProgressEntries();
        List<ProgressTableResponse> responses = ProgressTableResponseMapper.mapProgressEntriesToList(progressEntries);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressTableResponse> getProgressEntryById(@PathVariable Long id) {
        ProgressTable progressEntry = progressTableService.getProgressEntryById(id);
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

    @GetMapping("/user")
    public ResponseEntity<List<ProgressTableResponse>> getProgressEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Long userId = user.getId();
            List<ProgressTable> progressEntries = progressTableService.getProgressEntriesByUser(userId);
            List<ProgressTableResponse> responses = ProgressTableResponseMapper.mapProgressEntriesToList(progressEntries);

            if (responses.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(responses);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/user/weekly")
    public ResponseEntity<ProgressSummaryResponse> getWeeklyProgressEntriesByUser(
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Long userId = user.getId();
            ProgressSummary progressSummary = progressTableService.getWeeklyProgress(userId);
            return getProgressSummaryResponseResponseEntity(progressSummary);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/user/monthly")
    public ResponseEntity<ProgressSummaryResponse> getMonthlyProgressEntriesByUser(
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Long userId = user.getId();
            ProgressSummary progressSummary = progressTableService.getMonthlyProgress(userId);
            return getProgressSummaryResponseResponseEntity(progressSummary);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private ResponseEntity<ProgressSummaryResponse> getProgressSummaryResponseResponseEntity(ProgressSummary progressSummary) {
        if (progressSummary != null) {
            ProgressSummaryResponse progressSummaryResponse = new ProgressSummaryResponse();
            progressSummaryResponse.setAverageWeight(progressSummary.getAverageWeight());
            progressSummaryResponse.setAverageCaloriesTaken(progressSummary.getAverageCaloriesTaken());
            return ResponseEntity.ok(progressSummaryResponse);
        }
        ProgressSummaryResponse errorResponse = ProgressSummaryResponse.builder()
                .error("No progress entries found")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @PutMapping("/user/{userId}/{entryId}")
    public ResponseEntity<ProgressTableResponse> updateProgressEntry(
            @PathVariable("userId") Long userId,
            @PathVariable("entryId") Long entryId,
            @RequestBody ProgressTableRequest request
    ) {
        ProgressTable updatedEntry = progressTableService.updateProgressEntry(userId, entryId, request);
        if (updatedEntry != null) {
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
        boolean deletionResult = progressTableService.deleteProgressEntry(id);
        if (deletionResult) {
            return ResponseEntity.noContent().build();
        } else {
            ProgressTableResponse errorResponse = ProgressTableResponse.builder()
                    .error("Progress entry not found")
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
