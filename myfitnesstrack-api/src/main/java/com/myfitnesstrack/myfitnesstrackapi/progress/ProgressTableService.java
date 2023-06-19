package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import com.myfitnesstrack.myfitnesstrackapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressTableService {


    private final ProgressTableRepository progressTableRepository;
    private final UserRepository userRepository;

    public ProgressTable createProgressEntry(ProgressTableRequest request, User user) {
        ProgressTable progressEntry = new ProgressTable();
        progressEntry.setWeightInKilograms(request.getWeightInKilograms());
        progressEntry.setCaloriesTaken(request.getCaloriesTaken());
        progressEntry.setDate(request.getDate());
        progressEntry.setUser(user);

        return progressTableRepository.save(progressEntry);
    }

    public List<ProgressTable> getAllProgressEntries() {
        return progressTableRepository.findAll();
    }

    public ProgressTable getProgressEntryById(Long id) {
        return progressTableRepository.findById(id).orElse(null);
    }

    public List<ProgressTable> getProgressEntriesByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            return Collections.emptyList();
        }
        return progressTableRepository.findByUserId(userId);
    }

    public ProgressTable updateProgressEntry(Long userId, Long entryId, ProgressTableRequest request) {
        if (!userRepository.existsById(userId)) {
            return null;
        }

        Optional<ProgressTable> optionalProgressEntry = progressTableRepository.findById(entryId);
        if (optionalProgressEntry.isPresent()) {
            ProgressTable progressEntry = optionalProgressEntry.get();

            if (!progressEntry.getUser().getId().equals(userId)) {
                return null;
            }

            progressEntry.setWeightInKilograms(request.getWeightInKilograms());
            progressEntry.setCaloriesTaken(request.getCaloriesTaken());
            progressEntry.setDate(request.getDate());

            return progressTableRepository.save(progressEntry);
        } else {
            return null;
        }
    }

    public boolean deleteProgressEntry(Long id) {
        Optional<ProgressTable> optionalProgressEntry = progressTableRepository.findById(id);
        if (optionalProgressEntry.isPresent()) {
            ProgressTable progressEntry = optionalProgressEntry.get();
            User user = progressEntry.getUser();
            user.getProgressTables().remove(progressEntry);
            userRepository.save(user);

            progressTableRepository.delete(progressEntry);
            return true;
        } else {
            return false;
        }
    }
}
