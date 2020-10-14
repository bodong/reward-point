package com.sew.util.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sew.domain.User;
import com.sew.service.UserService;
import com.sew.util.PreloadDataService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sarwo.wibowo
 *
 */
@Slf4j
public class PreloadDataServiceImpl implements PreloadDataService {

	@Autowired
	private UserService userService;
	
	@Override
	public void preloadData() { 
		// FIXME: in real world, should not do this. This is just for the sake of quick data setup
		log.info("initiate pre load data.");
		loadUser();
		log.info("preload data is initiated.");
	}

	private void loadUser() {
		User davidBeckham = User.builder().email("david@becks.com").firstName("David").lastName("Beckham").build();
		User luisFigo = User.builder().email("luis@figo.com").firstName("Luis").lastName("Figo").build();
		User edgarDavids = User.builder().email("e.davids@bull.com").firstName("Edgar").lastName("Davids").build();
		User nataliePortman = User.builder().email("natalie@blackswan.com").firstName("Natalie").lastName("Portman").build();
		User salmaHayek = User.builder().email("salma@hayek.com").firstName("Salma").lastName("Hayek").build();
		
		createIfAbsent(davidBeckham);
		createIfAbsent(luisFigo);
		createIfAbsent(edgarDavids);
		createIfAbsent(nataliePortman);
		createIfAbsent(salmaHayek);
	}

	private void createIfAbsent(User user) {
		User existing = userService.getUserByEmail(user.getEmail());
		if(existing == null) {
			userService.createUser(user);
		}
	}

}
