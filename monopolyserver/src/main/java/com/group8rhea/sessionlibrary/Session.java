package com.group8rhea.sessionlibrary;

import java.io.Serializable;
import java.util.*;

public class Session implements Serializable {
    private static final long serialVersionUID = 2737714290822048884L;
    private final Integer sessionID;

    private final int capacity;
    private final Queue<PlayerDto> playerQueue;
    private final Map<PlayerDto,Integer> playerMap;
    private final Map<Integer,PlayerDto> placeOwnerMap;
    private List<Integer> randomNumbers;

    public Session(Integer sessionID, int size){
        this.sessionID =  sessionID;
        this.capacity = size;
        this.playerQueue = new LinkedList<>();
        this.placeOwnerMap = new HashMap<>();
        this.playerMap = new HashMap<>();
        this.randomNumbers = Arrays.asList(1,2,3,5,6,7,8,9,10,11,13,14,15);
        Collections.shuffle(randomNumbers);
    }

    public boolean addPlayerQueue(String username){
        if (capacity > playerQueue.size()  ){
            PlayerDto newPlayer = new PlayerDto(username);
            newPlayer.setX(0);
            newPlayer.setY(0);
            playerQueue.add(newPlayer);
            playerMap.put(newPlayer, 0);
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

    public Map<PlayerDto, Integer> getPlayerMap() {
        return playerMap;
    }

    public Map<Integer, PlayerDto> getPlaceOwnerMap() {
        return placeOwnerMap;
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }
}
