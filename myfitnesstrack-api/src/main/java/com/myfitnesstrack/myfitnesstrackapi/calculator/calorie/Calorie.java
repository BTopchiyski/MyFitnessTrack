package com.myfitnesstrack.myfitnesstrackapi.calculator.calorie;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "calorie_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(value = 0, message = "Calorie goal must be a positive value")
    @Column(name = "calorie_goal")
    private double calories;
}
