package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressTableRepository extends JpaRepository<ProgressTable, Long> {
    List<ProgressTable> findByUserId(Long userId);

    List<ProgressTable> findByUserAndDateAfter(Optional<User> user, LocalDate sevenDaysAgo);

    Optional<ProgressTable> findByUserAndDate(User user, LocalDate currentDate);

    List<ProgressTable> findAllByUserId(Long id);
}
