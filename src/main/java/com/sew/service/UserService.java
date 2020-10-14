package com.sew.service;

import com.sew.domain.User;

/**
 * Service layer for user data manipulation
 * @author sarwo.wibowo
 *
 */
public interface UserService {
	
	/**
	 * Create a requested user
	 * @param user
	 * @return {@link User} if valid request, null otherwise
	 */
	User createUser(User user);
	
	/**
	 * Get user by email.
	 * @param userEmail
	 * @return {@link User} if user exist, null otherwise
	 */
	User getUserByEmail(String userEmail);
}
