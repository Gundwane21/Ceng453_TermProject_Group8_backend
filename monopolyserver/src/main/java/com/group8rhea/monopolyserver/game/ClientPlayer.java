package com.group8rhea.monopolyserver.game;

import java.net.Socket;

public class ClientPlayer extends MonopolyPlayer{
    private final Socket clientSocket;

    public ClientPlayer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void updatePlayer() {

    }

    @Override
    public PlayerAction requestAction() {
        return null;
    }
}
