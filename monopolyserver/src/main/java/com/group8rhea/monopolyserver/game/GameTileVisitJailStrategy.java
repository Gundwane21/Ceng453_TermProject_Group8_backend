package com.group8rhea.monopolyserver.game;

import java.util.Set;

public class GameTileVisitJailStrategy implements IGameTileStrategy {
    @Override
    public boolean effectPlayer(Player player, Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner , GameTile thisTile ){
        return false;
    }
}
