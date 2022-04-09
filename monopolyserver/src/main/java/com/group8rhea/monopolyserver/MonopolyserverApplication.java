package com.group8rhea.monopolyserver;

//import java.lang.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@EntityScan(basePackages = {"com.group8rhea.monopolyserver.model"} )
@EnableJpaRepositories(basePackages = {"com.group8rhea.monopolyserver.repository"})
@SpringBootApplication
public class MonopolyserverApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	public static void main(String[] args) {
		SpringApplication.run(MonopolyserverApplication.class, args);
	}

}
