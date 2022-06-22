package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.dto.HttpResponseDto;
import com.group8rhea.monopolyserver.service.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/sessions")
@Tag(name="Multiple Session API", description = "Controlls the session of games")
public class SessionController {
    private SessionService sessionService;

    SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/{playerID}")
    public HttpResponseDto createSession(@PathVariable String playerID) {
        sessionService.createSession(Integer.valueOf(playerID));
        return new HttpResponseDto(HttpStatus.CREATED, "", "Session created");

    }

    @GetMapping(value = "")
    public Set<Integer> getAllWaitingSessions() {
        return sessionService.getAllWaitingSessions();
    }

    @PutMapping(value = "/{sessionID}/{playerID}")
    public HttpResponseDto connectSession(@PathVariable("sessionID") String sessionID, @PathVariable String playerID) {
        boolean canAdd = sessionService.connectToSession(Integer.valueOf(playerID), Integer.valueOf(sessionID));
        if (canAdd) {

            return new HttpResponseDto(HttpStatus.OK, "", "New player is added to the waiting session");
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Session is full no more player"
        );
    }

    @GetMapping(value = "/{sessionID}/{playerID}/startGame")
    public HttpResponseDto startGame(@PathVariable("sessionID") Integer sessionID, @PathVariable("playerID") Integer playerID) {
        if (sessionService.updateSessionAsActive(playerID, sessionID)) {
            return new HttpResponseDto(HttpStatus.OK, "", "Game started");
        }
        throw new ResponseStatusException(
                HttpStatus.METHOD_NOT_ALLOWED, "Only creater of the session can start the game!"
        );
    }

    @DeleteMapping(value = "/{sessionID}")
    public HttpResponseDto deleteSession(@PathVariable("sessionID") Integer sessionID) {
        sessionService.deleteSession(sessionID);
        return new HttpResponseDto(HttpStatus.OK, "", "Session Deleted");
    }
}
