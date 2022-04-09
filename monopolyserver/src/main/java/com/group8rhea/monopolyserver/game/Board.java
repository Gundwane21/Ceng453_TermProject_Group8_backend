package com.group8rhea.monopolyserver.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class Board {
    private int width;
    private int height;

    @Getter
    @Setter
    private Map<Player, Integer> playerMap;

    @Getter
    @Setter
    private Map<Integer, String> placeMap;
}
