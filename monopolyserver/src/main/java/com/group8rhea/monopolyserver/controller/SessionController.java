package com.group8rhea.monopolyserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group8rhea.monopolyserver.dto.HttpResponseDto;
import com.group8rhea.monopolyserver.service.SessionService;
import com.group8rhea.sessionlibrary.Session;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.Set;

@RestController
@RequestMapping("/api/sessions")
@Tag(name="Multiple Session API", description = "Controlls the session of games")
public class SessionController {
    private SessionService sessionService;

    SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/{username}")
    public HttpResponseDto createSession(@PathVariable String username) {
        sessionService.createSession(username);
        return new HttpResponseDto(HttpStatus.CREATED, "", "Session created");

    }

    @GetMapping(value = "")
    public String getAllWaitingSessions() {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        String json = null;
        sb.append("{\"result\": ");
        try {
            json = mapper.writeValueAsString(sessionService.getAllWaitingSessions());
            sb.append(json).append("}");
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @PutMapping(value = "/{sessionID}/{username}")
    public HttpResponseDto connectSession(@PathVariable("sessionID") String sessionID, @PathVariable String username) {
        boolean canAdd = sessionService.connectToSession(username, Integer.valueOf(sessionID));
        if (canAdd) {
            return new HttpResponseDto(HttpStatus.OK, "", "New player is added to the waiting session");
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Session is full no more player"
        );
    }

    @GetMapping(value = "/{sessionID}/{username}/startGame")
    public HttpResponseDto startGame(@PathVariable("sessionID") Integer sessionID, @PathVariable("playerID") String username) {
        if (sessionService.updateSessionAsActive(username, sessionID)) {
            return new HttpResponseDto(HttpStatus.OK, "", "Game started");
        }
        throw new ResponseStatusException(
                HttpStatus.METHOD_NOT_ALLOWED, "Only creater of the session can start the game!"
        );
    }

    @PutMapping(value = "/{sessionID}")
    public HttpResponseDto updateSession(@PathVariable("sessionID") Integer sessionID, @RequestBody byte[] sessionByteArray) {
        Session updatedSession = null;
        ObjectInput in = null;
        try ( ByteArrayInputStream bis = new ByteArrayInputStream(sessionByteArray)){
            in = new ObjectInputStream(bis);
            updatedSession = (Session) in.readObject();
            System.out.println(updatedSession.getSessionID());
            if(sessionService.updateSession(sessionID, updatedSession)) {
                return new HttpResponseDto(HttpStatus.OK, "", "Session updated");
            }else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Session couldn't updated. SessionID is invalid!"
                );
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Session couldn't updated. SessionID is invalid!"
        );
    }

    @GetMapping(value = "/{sessionID}/waiting")
    public ResponseEntity<Resource> getWaitingSession(@PathVariable("sessionID") Integer sessionID) throws IOException{
        ByteArrayResource resource = sessionService.getWaitingSession(sessionID);
        if(resource!=null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename("session")
                                    .build().toString())
                    .body(resource);
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Couldn't find session. SessionID might be invalid!"
        );
    }

    @GetMapping(value = "/{sessionID}/active")
    public ResponseEntity<Resource> getActiveSession(@PathVariable("sessionID") Integer sessionID) throws IOException{
        if(sessionService.getActiveSession(sessionID)==null) {
            System.out.println(sessionID);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Couldn't find session. SessionID might be invalid!"
            );
        }
        try( ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(sessionService.getActiveSession(sessionID));
            out.flush();
            byte[] array = bos.toByteArray();
            ByteArrayResource resource = new ByteArrayResource(array);
            System.out.println("HEYO");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename("session")
                                    .build().toString())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Couldn't find session. SessionID might be invalid!"
        );
    }

    @DeleteMapping(value = "/{sessionID}")
    public HttpResponseDto deleteSession(@PathVariable("sessionID") Integer sessionID) {
        sessionService.deleteSession(sessionID);
        return new HttpResponseDto(HttpStatus.OK, "", "Session Deleted");
    }
}
