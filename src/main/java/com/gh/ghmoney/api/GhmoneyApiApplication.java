package com.gh.ghmoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.gh.ghmoney.api.config.property.GhMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(value = GhMoneyApiProperty.class)
public class GhmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhmoneyApiApplication.class, args);
		
	}

}
