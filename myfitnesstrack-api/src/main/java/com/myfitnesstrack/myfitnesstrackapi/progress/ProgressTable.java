package com.myfitnesstrack.myfitnesstrackapi.progress;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
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

    @Column(name = "weight_kg")
    private double weightInKilograms;

    @Column(name = "calories_taken")
    private int caloriesTaken;

    private LocalDate date;

}
