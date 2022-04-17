package com.group8rhea.monopolyserver.model;

import com.group8rhea.monopolyserver.game.GameMode;
import lombok.Getter;
import lombok.Setter;

public class SocketSetupMessage {
    @Getter
    @Setter
    GameMode gameMode;
    @Getter
    @Setter
    String username;
}
