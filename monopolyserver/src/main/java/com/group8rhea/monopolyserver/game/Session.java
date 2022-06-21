package com.group8rhea.monopolyserver.game;

import lombok.Getter;

import java.util.Map;
import java.util.Queue;

public class Session {
    @Getter
    private Integer sessionID;

    private int capacity;
    private Queue<Integer> playerIDQueue;
    private Map<Player,Integer> playerMap;
    private Map<Integer,Player> placeOwnerMap;

    public Session(Integer sessionID,int size){
        this.sessionID =  sessionID;
        this.capacity = size;
    }

    // TODO: Implement a way to generate Player given playerID
    public boolean addPlayerQueue(Integer playerID){
        if (capacity > playerIDQueue.size()  ){
            playerIDQueue.add(playerID);
            return  true;
        }
        return false;
    };

}
