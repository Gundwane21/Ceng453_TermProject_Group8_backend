package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;

import java.util.Map;
import java.util.Set;

import static java.lang.Math.min;

public class GameTileFerryStrategy implements IGameTileStrategy {
    @Override
    public boolean effectPlayer(Player player,Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner, GameTile thisTile ) {
        int numOfFerryOwned = 0;
        boolean isBankrupt = false;
        // ask player to buy
        if (owner == null) {
            //player.askBuy()?
        }
        // pay money to owner
        else if( owner != player) {
            for( GameTile gameTile: gameTilesOwnedByThisTilesOwner){
                if(gameTile.getTileType() == TileType.FERRY_TILE){
                    numOfFerryOwned++;
                }
            }
            int rent = Constants.FERRY_RENT * min(numOfFerryOwned,Constants.FERRY_COUNT);
             isBankrupt = player.decreaseMoney(rent);
             owner.increaseMoney(rent);
        }
        return isBankrupt;
    }

}
