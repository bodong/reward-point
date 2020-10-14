package com.sew.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.common.base.Preconditions;
import com.sew.domain.User;
import com.sew.repository.UserRepository;
import com.sew.service.UserService;
import com.sew.util.EmailValidator;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(@NonNull User user) {
		Preconditions.checkArgument(!StringUtils.isEmpty(user.getFirstName()), "First name is required");
		Preconditions.checkArgument(EmailValidator.validEmail(user.getEmail()), "Valid email is required");
		
		userRepository.save(user);
		log.info("User record {} is created", user);
		return user;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		Preconditions.checkArgument(EmailValidator.validEmail(userEmail), "Valid email is required");
		return userRepository.findByEmail(userEmail);
	}

}
