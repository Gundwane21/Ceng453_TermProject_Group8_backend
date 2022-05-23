package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;
import com.group8rhea.monopolyserver.utils.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Board {
    private int width;
    private int height;
    private static int tileCount = 16;

    @Getter
    private final GameTile[] gameTiles = new GameTile[tileCount];

    /**
     * Construct board and bind names and images to the indices
     * @param cityNames
     * @param imagePaths
     */
    public  Board(List<String> cityNames, List<String> imagePaths){
        initializeBoard(cityNames,imagePaths);
        playerMap = new HashMap<>();
        placeMap = new HashMap<>();
        placeOwnerMap =  new HashMap<>();
    }

    void initializeBoard(List<String> cityNames, List<String> imagePaths){
        final RandomGenerator randomGenerator = RandomGenerator.getInstance();

        //start place
        gameTiles[0] = new GameTile(0,cityNames.get(0),0,imagePaths.get(0),new GameTileStartStrategy(), TileType.START_TILE_);
        //visit jail
        gameTiles[Constants.JAIL_TILE_INDEX] = new GameTile(Constants.JAIL_TILE_INDEX,cityNames.get(1), 0,imagePaths.get(1), new GameTileVisitJailStrategy(), TileType.VISIT_JAIL_TILE);
        //goto jail
        gameTiles[Constants.GOTO_JAIL_TILE_INDEX] = new GameTile(Constants.GOTO_JAIL_TILE_INDEX,cityNames.get(2),0,imagePaths.get(2),new GameTileGoJailStrategy(), TileType.GO_JAIL_TILE);

        // income tax
//        List<Integer> usedIndices = Arrays.asList(0,4,12);
        Set<Integer> usedIndices = new HashSet<>();
        usedIndices.add(0);usedIndices.add(4);usedIndices.add(12);

        int randomIndex = RandomGenerator.getRandomWithExclusion(0,15,usedIndices);
        gameTiles[randomIndex] = new GameTile(randomIndex,cityNames.get(3),50 , imagePaths.get(3) ,new GameTileIncomeTaxStrategy(),TileType.INCOME_TAX_TILE);
        usedIndices.add(randomIndex);

        //ferries
        for(int i = 4; i < 4+Constants.FERRY_COUNT ; i++){
            int randomIndexFerry = RandomGenerator.getRandomWithExclusion(0,tileCount-1,usedIndices);
            gameTiles[randomIndexFerry] = new GameTile(randomIndexFerry,cityNames.get(i), Constants.ferryCost, imagePaths.get(i) ,new GameTileFerryStrategy(),TileType.FERRY_TILE);
            usedIndices.add(randomIndexFerry);
        }
        // rest of the places
        for(int i= 8 ; i < tileCount ; i++){
            int randomIndexRest = RandomGenerator.getRandomWithExclusion(0,tileCount-1,usedIndices);
            gameTiles[randomIndexRest] = new GameTile(randomIndexRest,cityNames.get(i),Constants.normalPlaceStart +  (i-8) * Constants.normalPlaceIncreaseCoefficient , imagePaths.get(i) ,new GameTileNormalStrategy(),TileType.REGULER_TILE);
            usedIndices.add(randomIndexRest);
        }
    }

    @Getter
    @Setter
    private Map<Player, Integer> playerMap;
    // { Player1 : 4(th tile) , Player2 : 2(th tile)  }

    @Getter
    @Setter
    private Map<Integer, String> placeMap;
    // { 4(th tile) : "Winterfell"  }

    @Getter
    @Setter
    private Map<Integer,Player> placeOwnerMap;
    // { "Tile 0": Player1 , "Tile 1": Player2 ...}


    public GameTile[] getGameTiles() {
        return gameTiles;
    }

    public Map<Player, Integer> getPlayerMap() {
        return playerMap;
    }

    public Map<Integer, Player> getPlaceOwnerMap() {
        return placeOwnerMap;
    }
}
