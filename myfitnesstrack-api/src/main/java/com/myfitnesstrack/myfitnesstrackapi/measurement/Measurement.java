package com.myfitnesstrack.myfitnesstrackapi.measurement;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.persistence.*;
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

    @Column(name = "weight_kg")
    private double weightInKilograms;

    private int age;
    private double height;

    @Column(name = "waist_circumference")
    private double waistCircumference;

    @Column(name = "hip_circumference")
    private double hipCircumference;

    @Column(name = "wrist_circumference")
    private double wristCircumference;

    @Column(name = "forearm_circumference")
    private double forearmCircumference;
}
