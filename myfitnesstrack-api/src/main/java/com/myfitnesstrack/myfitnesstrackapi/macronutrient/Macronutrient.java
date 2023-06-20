package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.activityLevel.ActivityLevel;
import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "macronutrient")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Macronutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(value = 0, message = "Proteins must be greater than 0")
    @Column(name = "protein")
    private double protein;

    @Min(value = 0, message = "Proteins must be greater than 0")
    @Column(name = "protein_percent")
    private double proteinPercent;

    @Min(value = 0, message = "carbohydrates must be greater than 0")
    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Min(value = 0, message = "carbohydrates must be greater than 0")
    @Column(name = "carbohydratePercent")
    private double carbohydratePercent;

    @Min(value = 0, message = "Fats must be greater than 0")
    @Column(name = "fats")
    private double fats;

    @Min(value = 0, message = "Fats must be greater than 0")
    @Column(name = "fat_percent")
    private double fatPercent;
}
