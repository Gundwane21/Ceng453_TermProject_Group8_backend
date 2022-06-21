package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.dto.HttpResponseDto;
import com.group8rhea.monopolyserver.game.Monopoly;
import com.group8rhea.monopolyserver.service.SessionService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Tag(name="Multiple Session API", description = "Controlls the session of games")
public class SessionController {

    private SessionService sessionService;

    SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/createSession")
    public HttpResponseDto createSession(@RequestBody String playerID){
        sessionService.createSession(Integer.valueOf(playerID));
        return new HttpResponseDto(HttpStatus.CREATED,"","Session created");
    }

    @GetMapping(value = "/waitingSessions")
    public Set<Integer> getAllWaitingSessions(){
       return sessionService.getAllWaitingSessions();
    }

    @PutMapping(value = "/waitingSessions/{sessionID}")
    public HttpResponseDto connectSession(@PathVariable("sessionID") String sessionID, @RequestBody String playerID){
        boolean canAdd = sessionService.connectToSession(Integer.valueOf(playerID),Integer.valueOf(sessionID));
        if(canAdd){
            return new HttpResponseDto(HttpStatus.OK,"","New player is added to the waiting session");
        }
        return new HttpResponseDto(HttpStatus.BAD_REQUEST,"","Session is full no more player ");
    }

}

