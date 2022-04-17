package com.group8rhea.monopolyserver.network;


import com.group8rhea.monopolyserver.game.*;
import com.group8rhea.monopolyserver.model.SocketSetupMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionManager {
    private static SessionManager sessionManagerInstance;
    private ServerSocket serverSocket;
    private Map<String, Socket> clientConnections;
    private Map<Socket, Monopoly> gameInstances;

    private SessionManager() {
        clientConnections = new HashMap<>();
        gameInstances = new HashMap<>();
    }

    public static SessionManager getSessionManager() {
        if(sessionManagerInstance == null) {
            SessionManager.sessionManagerInstance = new SessionManager();
        }
        return SessionManager.sessionManagerInstance;
    }

    private void handleRequests() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        while (true) {
            Socket clientSocket = null;
            SocketSetupMessage ssm = null;
            try {
                clientSocket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ssm = (SocketSetupMessage) objectInputStream.readObject();
                List<Player> players = new ArrayList<>();
                if(PlayerMode.SINGLEPLAYER == ssm.getGameMode().getPm()) {
                    MonopolyPlayer monopolyPlayerBot = new MonopolyPlayerBot();
                    MonopolyPlayer monopolyPlayerClient = new ClientPlayer(clientSocket);
                    players.add(monopolyPlayerBot);
                    players.add(monopolyPlayerClient);
                } else if(PlayerMode.MULTIPLAYER == ssm.getGameMode().getPm()) {
                    //TO BE IMPLEMENTED...
                }
                Board board = new Board();
                Monopoly monopoly = new Monopoly(players, board);
                clientConnections.put(ssm.getUsername(), clientSocket);
                gameInstances.put(clientSocket, monopoly);
                es.submit(new GameThread(clientSocket, monopoly));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void startSessionManager(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handleRequests();
    }
}
