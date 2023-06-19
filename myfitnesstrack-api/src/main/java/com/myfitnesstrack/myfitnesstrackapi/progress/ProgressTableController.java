package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/progress")
@RequiredArgsConstructor
public class ProgressTableController {

    private final ProgressTableService progressTableService;

    @PostMapping
    public ResponseEntity<ProgressTableResponse> createProgressEntry(
            @RequestBody ProgressTableRequest request,
            @AuthenticationPrincipal User user
    ) {
        ProgressTable savedEntry = progressTableService.createProgressEntry(request, user);
        ProgressTableResponse response = ProgressTableResponseMapper.mapProgressEntryToResponse(savedEntry);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressTableResponse>> getProgressEntriesByUser(@PathVariable("userId") Long userId) {
        List<ProgressTable> progressEntries = progressTableService.getProgressEntriesByUser(userId);
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
