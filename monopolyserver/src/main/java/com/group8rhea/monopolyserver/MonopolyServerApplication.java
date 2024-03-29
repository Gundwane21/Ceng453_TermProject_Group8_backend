package com.group8rhea.monopolyserver;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan(basePackages = {"com.group8rhea.monopolyserver.model"} )
@EnableJpaRepositories(basePackages = {"com.group8rhea.monopolyserver.repository"})
@SpringBootApplication
public class MonopolyServerApplication {
	private final String appVersion = "1.0";

	public static void main(String[] args) {
		SpringApplication.run(MonopolyServerApplication.class, args);
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.pathsToMatch("/**")
				.build();
	}
}
