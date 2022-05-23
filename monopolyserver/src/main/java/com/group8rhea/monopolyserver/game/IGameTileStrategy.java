package com.group8rhea.monopolyserver.game;

import java.util.Map;
import java.util.Set;

public interface IGameTileStrategy {
    boolean effectPlayer(Player player,Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner, GameTile thisTile );
}
