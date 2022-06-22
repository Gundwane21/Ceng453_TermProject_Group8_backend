package com.group8rhea.monopolyserver.dto;

public class PlayerDto {

    private int ID;
    private int money;
    private String username;
    private double x;
    private double y;
    private int onThisTileId;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getOnThisTileId() {
        return onThisTileId;
    }

    public void setOnThisTileId(int onThisTileId) {
        this.onThisTileId = onThisTileId;
    }
}
