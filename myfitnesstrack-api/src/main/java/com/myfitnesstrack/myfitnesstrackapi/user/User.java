package com.myfitnesstrack.myfitnesstrackapi.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myfitnesstrack.myfitnesstrackapi.calculator.calorie.Calorie;
import com.myfitnesstrack.myfitnesstrackapi.macronutrient.Macronutrient;
import com.myfitnesstrack.myfitnesstrackapi.measurement.Measurement;
import com.myfitnesstrack.myfitnesstrackapi.progress.ProgressTable;
import com.myfitnesstrack.myfitnesstrackapi.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<ProgressTable> progressTables;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Measurement measurement;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Macronutrient macronutrient;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Calorie calorieGoal;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Gender getGender() { return gender; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
