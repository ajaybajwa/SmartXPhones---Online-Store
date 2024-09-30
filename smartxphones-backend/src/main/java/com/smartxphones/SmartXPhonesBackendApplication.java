package com.smartxphones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SmartXPhonesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartXPhonesBackendApplication.class, args);
		
	}

}
