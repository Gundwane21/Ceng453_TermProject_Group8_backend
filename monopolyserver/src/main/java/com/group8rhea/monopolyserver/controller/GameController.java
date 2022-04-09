package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.game.Monopoly;
import com.group8rhea.monopolyserver.game.MonopolyPlayer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.group8rhea.monopolyserver.game.Player;
@RestController
@RequestMapping("/api/game")
public class GameController {
    @GetMapping(value = "/singleplayer")
    public Monopoly startSinglePlayerGame() {
//        return new Monopoly(new ArrayList<>());
        return null;
    }

    @GetMapping(value = "/matchmaking", produces = "application/json")
    public String getGame(@PathVariable int id) {
        return "slm" + String.valueOf(id);
    }


}
