package com.group8rhea.monopolyserver.game;

import lombok.Getter;
import lombok.Setter;

public class PlayerAction {
    public PlayerAction() {
        this.dice1 = 0;
        this.dice2 = 0;
    }

    public PlayerAction(int dice1, int dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Getter
    @Setter
    private int dice1;
    @Getter
    @Setter
    private int dice2;
}
