package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.game.Monopoly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group8rhea.monopolyserver.network.SessionManager;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/game")
public class GameController {
    private SessionManager sm = SessionManager.getSessionManager();

    @GetMapping(value = "/singleplayer")
    public Monopoly startSinglePlayerGame(HttpServletRequest request) {
        // return new Monopoly(new ArrayList<>());
        String username = request.getRemoteUser();
        //TODO: send client a message giving the socket number
        return null;
    }

    @GetMapping(value = "/matchmaking", produces = "application/json")
    public String getGame(@PathVariable int id) {
        return "slm" + String.valueOf(id);
    }


}
