package com.myfitnesstrack.myfitnesstrackapi.auth;

import com.myfitnesstrack.myfitnesstrackapi.user.Gender;
import com.myfitnesstrack.myfitnesstrackapi.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
}
