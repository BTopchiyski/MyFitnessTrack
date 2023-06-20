package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(value = 0, message = "Weight must be a positive value")
    @Column(name = "weight_kg")
    private double weightInKilograms;

    @Min(value = 0, message = "Calories taken must be a positive value")
    @Column(name = "calories_taken")
    private double caloriesTaken;

    @NotNull(message = "Date is required")
    private LocalDate date;

}
