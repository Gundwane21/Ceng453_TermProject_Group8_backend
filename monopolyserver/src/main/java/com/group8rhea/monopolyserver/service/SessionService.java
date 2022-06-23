package com.group8rhea.monopolyserver.service;

import com.group8rhea.sessionlibrary.Session;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

@Service
public class SessionService {

    private static final int SESSION_SIZE = 4;
    private int sessionIDCounter;

    private Map<Integer, Session> activeSessionMap;
    private Map<Integer,Session> waitingSessionMap;

    // {  playerID: Session Object1 ,    }
    private Map<String,Integer> playerUsernameSessionIDMap ;

    SessionService(){
        this.sessionIDCounter = 0;
        this.activeSessionMap = new HashMap<>();
        this.waitingSessionMap = new HashMap<>();
        this.playerUsernameSessionIDMap = new HashMap<>();
    }

    public Integer createSession(){
        Session session = new Session(sessionIDCounter++, SESSION_SIZE);
        waitingSessionMap.put(session.getSessionID(),session);
        return session.getSessionID();
    }

    public Set<Integer> getAllWaitingSessions(){
        return waitingSessionMap.keySet();
    }

    public boolean connectToSession(String username, Integer sessionID){
        Session session = waitingSessionMap.get(sessionID);
        return session.addPlayerQueue(username);
    }

    public boolean updateSessionAsActive(String username, Integer sessionID) {
        Session currentSession = waitingSessionMap.get(sessionID);
        if(currentSession.getPlayerQueue().peek().getUsername().equals(username)){
            System.out.println(waitingSessionMap.remove(sessionID));
            activeSessionMap.put(sessionID, currentSession);
            return true;
        }
        return false;
    }
    public void deleteSession(Integer sessionID) {
        waitingSessionMap.remove(sessionID);
        activeSessionMap.remove(sessionID);
    }

    public boolean updateSession(Integer sessionID, Session updatedSession) {
        if(activeSessionMap.put(sessionID, updatedSession)!=null) {
            return true;
        } else {
            return false;
        }
    }

    public Session getActiveSession(Integer sessionID) {
        return activeSessionMap.get(sessionID);
    }

    public ByteArrayResource getWaitingSession(Integer sessionID) {
        if(waitingSessionMap.get(sessionID)==null) {
            System.out.println(sessionID);
            return null;
        }
        try( ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(waitingSessionMap.get(sessionID));
            out.flush();
            byte[] array = bos.toByteArray();
            ByteArrayResource resource = new ByteArrayResource(array);
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
