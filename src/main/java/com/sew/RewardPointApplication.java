package com.sew;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sew.util.PreloadDataService;

/**
 * Main app, entry point of the application
 * 
 * @author sarwo.wibowo
 *
 */
@SpringBootApplication
public class RewardPointApplication {

	@Autowired(required = false)
	private PreloadDataService preloadDataService;
	
	@PostConstruct
	void init() {
		if(preloadDataService != null) {
			preloadDataService.preloadData();
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RewardPointApplication.class, args);
	}

}
