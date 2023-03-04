package com.app.service;

import javax.validation.Valid;

import com.app.entities.User;
import com.app.model.bind.UserRegistrationModel;

public interface UserService {
 User addUserDetails(UserRegistrationModel userRegistrationModel);

User addUserDetails(User user);
}
