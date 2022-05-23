package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;

import java.util.Set;


public class GameTileStartStrategy implements IGameTileStrategy {
    @Override
    public boolean effectPlayer(Player player,Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner ,GameTile thisTile ) {
        player.increaseMoney(Constants.STARTING_POINT_EARNING);
        return false;
    }
}
