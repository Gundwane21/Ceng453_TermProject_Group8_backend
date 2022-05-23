package com.group8rhea.monopolyserver.game;

public interface Player {
    //DEBUG PURPOSES DELETE ONCE DEPLOYED
    public int getMoney();

    public void updatePlayer();

    public PlayerAction requestAction();

    public boolean decreaseMoney(int rent);
    public void increaseMoney(int earning);

    public boolean isJailed();
    public boolean canPlayAgain();
    void setJailed();
}
