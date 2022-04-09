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
    public Monopoly(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public void startGame() {
    }
    public void updateGame() {

    }
    public void processAction(Player player, PlayerAction playerAction) {
    }
}
