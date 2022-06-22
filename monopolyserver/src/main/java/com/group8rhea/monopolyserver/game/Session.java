package com.group8rhea.monopolyserver.game;

import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Session {
    private Integer sessionID;

    private int capacity;
    private Queue<Integer> playerIDQueue;
    private Map<Player,Integer> playerMap;
    private Map<Integer,Player> placeOwnerMap;

    public Queue<Integer> getPlayerIDQueue() {
        return playerIDQueue;
    }

    public Session(Integer sessionID, int size){
        this.sessionID =  sessionID;
        this.capacity = size;
        this.playerIDQueue = new LinkedList<>();
    }

    // TODO: Implement a way to generate Player given playerID
    public boolean addPlayerQueue(Integer playerID){
        if (capacity > playerIDQueue.size()  ){
            playerIDQueue.add(playerID);
            return  true;
        }
        return false;
    };

    public Integer getSessionID() {
        return sessionID;
    }
}
