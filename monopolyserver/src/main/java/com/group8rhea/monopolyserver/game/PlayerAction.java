package com.group8rhea.monopolyserver.game;

import lombok.Getter;
import lombok.Setter;

public class PlayerAction {
    public PlayerAction() {
        this.dice1 = 0;
        this.dice2 = 0;
    }

    public PlayerAction(int dice1, int dice2,DoubleRollState doubleRollState) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.doubleRollState = doubleRollState;

    }
    @Getter
    @Setter
    private int dice1;
    @Getter
    @Setter
    private int dice2;

    @Getter
    @Setter
    private int doubleRollCount;

    @Getter
    private DoubleRollState doubleRollState;

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }
}
