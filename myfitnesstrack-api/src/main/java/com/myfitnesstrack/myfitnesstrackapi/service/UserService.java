package com.myfitnesstrack.myfitnesstrackapi.service;

import com.myfitnesstrack.myfitnesstrackapi.entity.User;
import com.myfitnesstrack.myfitnesstrackapi.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
