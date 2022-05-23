package com.group8rhea.monopolyserver.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.group8rhea.monopolyserver.utils.Constants;

import lombok.Getter;
import lombok.Setter;

public class Monopoly {
    @Getter
    @Setter
    private List<Player> players;
    private Board board;

    private final List<String> placeNames= new ArrayList<>();
    private final List<String> placeImagePaths = new ArrayList<>();

    public Monopoly(List<Player> players) {
        this.players = players;
        initializeGame();
    }

    private void initializeGame(){
        readConfigs("/home/gundwane/Desktop/uni/senior-spring/ceng453-software-construction/assignments/Ceng453_TermProject_Group8_backend/monopolyserver/src/main/java/com/group8rhea/monopolyserver/game/gamePlaces.txt");
        for(String placename : placeNames){
            System.out.println(placename);
        }

        board = new Board(placeNames,placeImagePaths);
        for( GameTile gameTile: board.getGameTiles()){
            System.out.println(gameTile);
        }
    }

    public void startGame() {
        // Put every player is on Zeroth tile
        for(Player player: players){
            board.getPlayerMap().put(player,0);
        }
    }

    public boolean updateGame() {
        for(Player player: players){

            System.out.println(board.getPlayerMap());

            //Check if the player is in jail
            // If so decrement its jail counter
            if (!player.isJailed()){

                //move the player
                int tileIndex = movePlayer(player);

                //check if triple double roll put the player in jail
                if( !player.isJailed()){

                    // process the new location of the player
                    boolean resultOfBankrupt =  processMoveToNewTile(player,tileIndex) ;
                    if(resultOfBankrupt){
                        System.out.println(player.getMoney());
                        return resultOfBankrupt;
                    }

                    // After processing the tile if the player sits in GO TO JAIL
                    if(player.isJailed()){
                        board.getPlayerMap().put(player,Constants.JAIL_TILE_INDEX);
                        continue;
                    }

                    // If the player rolled doubles
                    while(player.canPlayAgain()) {
                        tileIndex = movePlayer(player);

                        if (!player.isJailed()) {
                            boolean resultOfBankrupt1 = processMoveToNewTile(player,tileIndex);
                            if(resultOfBankrupt1){
                                System.out.println(player.getMoney());
                                return resultOfBankrupt1;
                            }
                        }
                        else{
                            board.getPlayerMap().put(player,Constants.JAIL_TILE_INDEX);
                        }
                    }
                }else{
                    board.getPlayerMap().put(player,Constants.JAIL_TILE_INDEX);
                }
            }
        }
        return false;
    }

    private int movePlayer(Player player){
        PlayerAction playerAction = player.requestAction();

        Integer tileIndex  = board.getPlayerMap().get(player);
        tileIndex = ( tileIndex + playerAction.getDice1() + playerAction.getDice2()  )  ;
        // if the user passes the starting point increment money
        if (tileIndex > Constants.TILE_COUNT){
            player.increaseMoney(Constants.STARTING_POINT_EARNING);
        }
        tileIndex = tileIndex % (Constants.TILE_COUNT-1) ;

        System.out.println("movePlayer tileIndex");
        System.out.println(tileIndex);
        return tileIndex;
    }

    private boolean processMoveToNewTile(Player player,int tileIndex){
        GameTile currGameTile = board.getGameTiles()[tileIndex];

        // Get owner of the moved tile
        Player owner = board.getPlaceOwnerMap().get(currGameTile);
        Set<Integer> keySet = board.getPlaceOwnerMap().keySet();

        // Get other owned tiles by this tiles owner
        Set<GameTile> ownerOwnedgameTileSet = new HashSet<>();
        for( Integer key : keySet ){
            Player placeOwner = board.getPlaceOwnerMap().get(key);
            if (placeOwner == owner ){
                ownerOwnedgameTileSet.add(board.getGameTiles()[key]);
            }
        }

        // update locations
        board.getPlayerMap().put(player,tileIndex);

        // Update the player depends on where it landed
        return currGameTile.effectPlayer(player,owner,ownerOwnedgameTileSet  );
    }


    public void processAction(Player player, PlayerAction playerAction) {
    }

    /**
     * Reads city names and images from the file line by line seperated by comma.
     * Example file:
     *kingslanding,img.path
     * wintergell,img.path
     * @param path String
     */
    private void readConfigs(String path){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                if ( values.length !=2 || (line.length() >0 && line.charAt(0) == '#') ) {
                    continue;
                }
                placeNames.add(values[0]);
                placeImagePaths.add(values[1]);
            }
        } catch (IOException e) {
            System.out.println("IOException in try block =>" + e.getMessage());
        }
    }
}
