package com.myfitnesstrack.myfitnesstrackapi.progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressTableRepository extends JpaRepository<ProgressTable, Long> {
    List<ProgressTable> findByUserId(Long userId);
}
