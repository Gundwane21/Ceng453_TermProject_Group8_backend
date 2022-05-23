package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;

import java.util.Set;

public class GameTileIncomeTaxStrategy implements IGameTileStrategy {
    @Override
    public boolean effectPlayer(Player player,Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner,GameTile thisTile ) {
         return player.decreaseMoney(Constants.TAX_INCOME_RENT);
    }
}
