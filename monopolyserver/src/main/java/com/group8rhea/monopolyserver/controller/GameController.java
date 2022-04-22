package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.game.Monopoly;
import com.group8rhea.monopolyserver.game.MonopolyPlayer;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import com.group8rhea.monopolyserver.game.Player;
@RestController
@RequestMapping("/api/game")
@Tag(name="Game Start API", description = "Starts a Game")
@Hidden()
public class GameController {
    @GetMapping(value = "/singleplayer")
    @Operation(description = "Sends a request to the server to start a single player game.")
    @ApiResponse(responseCode = "200", description = "Returns the server socket for TCP connection.")
    public Monopoly startSinglePlayerGame() {
//        return new Monopoly(new ArrayList<>());
        return null;
    }

    @GetMapping(value = "/matchmaking", produces = "application/json")
    @Operation(description = "Sends a request to the server to start a multiplayer game.")
    @ApiResponse(responseCode = "200", description = "Returns the server socket for TCP connection.")
    public String getGame(@PathVariable int id) {
        return "slm" + String.valueOf(id);
    }


}
