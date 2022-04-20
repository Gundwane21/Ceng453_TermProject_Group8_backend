package com.group8rhea.monopolyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@EntityScan(basePackages = {"com.group8rhea.monopolyserver.model"} )
@EnableJpaRepositories(basePackages = {"com.group8rhea.monopolyserver.repository"})
@SpringBootApplication
public class MonopolyServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonopolyServerApplication.class, args);
	}

}
