package com.sew;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sew.service.UserPointSearchService;
import com.sew.service.UserPointService;
import com.sew.service.UserService;
import com.sew.service.impl.UserPointSearchServiceImpl;
import com.sew.service.impl.UserPointServiceImpl;
import com.sew.service.impl.UserServiceImpl;
import com.sew.util.PreloadDataService;
import com.sew.util.service.PreloadDataServiceImpl;

/**
 * Configuration to register bean for service layer. It is purposely to handle this way to have more control on the bean configuration
 * @author sarwo.wibowo
 *
 */
@Configuration
public class BeanConfig {
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public UserPointService userPointService() {
		return new UserPointServiceImpl();
	}
	
	@Bean
	public UserPointSearchService userPointSearchService() {
		return new UserPointSearchServiceImpl();
	}
	
	@Bean
	public PreloadDataService preloadDataService() {
		return new PreloadDataServiceImpl();
	}
}
