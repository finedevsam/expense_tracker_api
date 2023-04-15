package com.samson.expensetrackerapi.service;

import com.samson.expensetrackerapi.entity.User;
import com.samson.expensetrackerapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel user);
    User readUser();

    User updateUser(UserModel user);
    void deleteUser();

    User getLoggedInUser();
}
