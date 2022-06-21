package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.game.Session;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SessionService {

    private static final int SESSION_SIZE = 4;
    private int sessionIDCounter;

    private Map<Integer,Session> activeSessionMap;
    private Map<Integer,Session> waitingSessionMap;

    // {  playerID: Session Object1 ,    }
    private Map<Integer,Integer> playerIDSessionIDMap ;

    SessionService(){
        this.sessionIDCounter = 0;
        this.activeSessionMap = new HashMap<>();
        this.waitingSessionMap = new HashMap<>();
    }

    public Integer createSession(Integer playerID){
        Session session = new Session(sessionIDCounter++,SESSION_SIZE);
        //TODO: Add player into sessions playerQueue
        session.addPlayerQueue(playerID);
        playerIDSessionIDMap.put(playerID,session.getSessionID());
        waitingSessionMap.put(session.getSessionID(),session);
        return session.getSessionID();
    }

    public Set<Integer> getAllWaitingSessions(){
        return waitingSessionMap.keySet();
    }

    public boolean connectToSession(Integer playerID, Integer sessionID){
        Session session = waitingSessionMap.get(sessionID);
        return session.addPlayerQueue(playerID);

    }

}
