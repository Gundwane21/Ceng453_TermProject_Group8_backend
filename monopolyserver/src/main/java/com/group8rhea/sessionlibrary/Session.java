package com.group8rhea.sessionlibrary;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Session implements Serializable {
    private static final long serialVersionUID = 2737714290822048884L;
    private Integer sessionID;

    private int capacity;
    private Queue<PlayerDto> playerQueue;
    private Map<PlayerDto,Integer> playerMap;
    private Map<Integer,PlayerDto> placeOwnerMap;

    public Session(Integer sessionID, int size){
        this.sessionID =  sessionID;
        this.capacity = size;
        this.playerQueue = new LinkedList<>();
    }

    // TODO: Implement a way to generate Player given playerID
    public boolean addPlayerQueue(Integer playerID){
        if (capacity > playerQueue.size()  ){
            playerQueue.add(new PlayerDto(playerID));
            return  true;
        }
        return false;
    };

    public Integer getSessionID() {
        return sessionID;
    }

    public Queue<PlayerDto> getPlayerQueue() {
        return playerQueue;
    }

    public void setPlayerQueue(Queue<PlayerDto> playerQueue) {
        this.playerQueue = playerQueue;
    }
}
