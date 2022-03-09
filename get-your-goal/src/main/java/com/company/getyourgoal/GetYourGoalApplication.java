package com.company.getyourgoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GetYourGoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetYourGoalApplication.class, args);
	}

}
