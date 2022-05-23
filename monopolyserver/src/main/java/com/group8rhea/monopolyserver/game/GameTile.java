package com.group8rhea.monopolyserver.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;


public class GameTile {

    GameTile(Integer locationId, String name,Integer value, String imagePath, IGameTileStrategy gameTileStrategy,TileType tileType){
        this.locationId = locationId;
        this.name = name;
        this.value = value;
        this.imagePath = imagePath;
        this.gameTileStrategy = gameTileStrategy;
        this.tileType =tileType;
    }

    @Getter
    @Setter
    private Integer locationId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer value;

    @Getter
    @Setter
    private String imagePath;

    @Getter
    @Setter
    private IGameTileStrategy gameTileStrategy;

    @Getter
    @Setter
    private TileType tileType;

    public TileType getTileType() {
        return tileType;
    }

    public Integer getValue() {
        return value;
    }

    public boolean effectPlayer(Player player, Player owner, Set<GameTile> gameTilesOwnedByThisTilesOwner){
        return gameTileStrategy.effectPlayer(player,owner,gameTilesOwnedByThisTilesOwner,this);
    }
}