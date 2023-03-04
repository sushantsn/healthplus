package com.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.User;
import com.app.model.bind.UserRegistrationModel;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder enc;

	@Override
	public User addUserDetails(User user) {
		//encrypt the pwd
		user.setPassword(enc.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User addUserDetails(UserRegistrationModel userRegistrationModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
