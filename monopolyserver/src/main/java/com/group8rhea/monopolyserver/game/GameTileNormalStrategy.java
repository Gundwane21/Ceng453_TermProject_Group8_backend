package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;

import java.util.Set;

public class GameTileNormalStrategy implements IGameTileStrategy {
    @Override
    public boolean effectPlayer(Player player,Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner, GameTile thisTile ) {
        boolean isBankrupt = false;
        // ask player to buy
        if (owner == null) {
//            player.askBuy()?
        }
        // pay money to owner
        else if( owner != player) {

            int rent = thisTile.getValue() / 10 ;
             isBankrupt = player.decreaseMoney(rent);
             owner.increaseMoney(rent);
        }
        return isBankrupt;
    }
}
