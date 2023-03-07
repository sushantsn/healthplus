package com.app.service;

import com.app.entities.User;
import com.app.model.bind.UserRegistrationModel;

public interface UserService {
 User addUserDetails(UserRegistrationModel userRegistrationModel);

User addUserDetails(User user);
}
