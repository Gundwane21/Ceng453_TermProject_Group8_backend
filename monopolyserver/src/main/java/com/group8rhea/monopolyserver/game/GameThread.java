package com.group8rhea.monopolyserver.game;

import java.net.Socket;
import java.util.Map;

//TODO: Clear socket resource on exit
public class GameThread implements Runnable{
    private Socket clientSocket;
    private Monopoly monopoly;

    public GameThread(Socket clientSocket, Monopoly monopoly) {
        this.clientSocket = clientSocket;
        this.monopoly = monopoly;
    }

    @Override
    public void run() {

    }
}
