package com.group8rhea.monopolyserver;

import com.group8rhea.monopolyserver.game.BotPlayer;
import com.group8rhea.monopolyserver.game.Monopoly;
import com.group8rhea.monopolyserver.game.Player;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

//@EntityScan(basePackages = {"com.group8rhea.monopolyserver.model"} )
@EnableJpaRepositories(basePackages = {"com.group8rhea.monopolyserver.repository"})
@SpringBootApplication
public class MonopolyServerApplication {
	private final String appVersion = "1.0";

	public static void main(String[] args) {

		SpringApplication.run(MonopolyServerApplication.class, args);
		List<Player> players = new ArrayList<>();
		for(int i=0 ; i < 2; i++){
			Integer id = new Integer(i);
			Player player = new BotPlayer(id);
			players.add(player);
		}

		Monopoly monopoly = new Monopoly(players);
		monopoly.startGame();

		boolean gameOver = false;
		while(!gameOver){
			gameOver = monopoly.updateGame();
			System.out.println(gameOver);
		}
	}



	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.pathsToMatch("/**")
				.build();
	}
}
