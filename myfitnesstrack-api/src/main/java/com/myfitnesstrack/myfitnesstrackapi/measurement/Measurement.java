package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(value = 0, message = "Weight must be a positive value")
    @Column(name = "weight_kg")
    private double weightInKilograms;

    @Min(value = 0, message = "Age must be a positive value")
    private int age;

    @Min(value = 0, message = "Height must be a positive value")
    private double height;

    @Min(value = 0, message = "Waist circumference must be a positive value")
    @Column(name = "waist_circumference")
    private double waistCircumference;

    @Min(value = 0, message = "Hip circumference must be a positive value")
    @Column(name = "hip_circumference")
    private double hipCircumference;

    @Min(value = 0, message = "Wrist circumference must be a positive value")
    @Column(name = "wrist_circumference")
    private double wristCircumference;

    @Min(value = 0, message = "Forearm circumference must be a positive value")
    @Column(name = "forearm_circumference")
    private double forearmCircumference;
}
