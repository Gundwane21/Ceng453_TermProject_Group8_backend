package com.group8rhea.monopolyserver.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class Monopoly {
    @Getter
    @Setter
    private List<Player> players;
    private Board board;
    @Getter
    @Setter
    private boolean isGameFinished;
    public Monopoly(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        this.isGameFinished = false;
    }

//    public Monopoly(GameMode gameMode) {
//        if(gameMode.pm == PlayerMode.SINGLEPLAYER) {
//            players.
//        }
//    }

    public void startGame() {
    }
    public void updateGame() {

    }
    public void processAction(Player player, PlayerAction playerAction) {
    }
}
