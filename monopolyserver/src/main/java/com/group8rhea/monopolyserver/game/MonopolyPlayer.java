package com.group8rhea.monopolyserver.game;

import com.group8rhea.monopolyserver.utils.Constants;
import com.group8rhea.monopolyserver.utils.RandomGenerator;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

public abstract class MonopolyPlayer implements Player{
    protected Integer id;

    @Getter
    protected int money = Constants.PLAYER_START_MONEY;

    @Getter
    protected DoubleRollState doubleRollState=DoubleRollState.NORMAL_PLAY;

    @Getter
    protected int doubleRollCount=0;

    protected boolean isJailed=false;

    @Getter
    protected int jailCount=0;

    @Override
    public PlayerAction requestAction() {

        int dice1 = RandomGenerator.getRandomRange(1,6);
        int dice2 = RandomGenerator.getRandomRange(1,6);

        doubleRollCheck(dice1,dice2);

        if (this.doubleRollCount == 1 || this.doubleRollCount == 2 ){
            this.doubleRollState = DoubleRollState.ROLL_AGAIN;
        }
        else if(this.doubleRollCount == 3){
            this.doubleRollState = DoubleRollState.GO_TO_JAIL;
            this.doubleRollCount = 0;
            setJailed();
        }
        else{
            this.doubleRollState=DoubleRollState.NORMAL_PLAY;
            this.doubleRollCount = 0;
        }

        return new PlayerAction(dice1,dice2,doubleRollState);
    }

    /**
     * Set the user to be in jail, set the jailCount for how many turns it will be in jail
     */
    public void setJailed(){
        isJailed = true;
        jailCount = 2;
    }

    /**
     * Check whether the player is in jail.
     * If the user just jailed by triple roll, Dont decrease jailCount
     * @return boolean
     */
    public boolean isJailed(){
        // Just rolled doubles three times
        if( isJailed && doubleRollState == DoubleRollState.GO_TO_JAIL){
            doubleRollState = DoubleRollState.NORMAL_PLAY;
        }
        else if( isJailed && jailCount > 0){
            jailCount--;
        }
        else{
            isJailed = false;
            jailCount =0;
        }
        return isJailed;
    }

    public boolean canPlayAgain(){
        if (doubleRollState == DoubleRollState.ROLL_AGAIN ){
            return true;
        }
        return false;
    }

    private void doubleRollCheck(int dice1,int dice2){
        if ( dice1 == 6 && dice2 == 6){
            this.doubleRollCount++;
        }
        else{
            this.doubleRollCount = 0;
        }
    }

    /**
     * Decrease players money by given amount. Returns boolean denoting whether the player is bankrupt.
     * @param rent int
     * @return bool
     */
    public boolean decreaseMoney(int rent){
        money -= rent;
        return money < 0 ;
    }
    public void increaseMoney(int earning){
        money += earning;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Player)) {

            return false;
        }
        final MonopolyPlayer otherPlayer = (MonopolyPlayer) obj;
        return this.id.equals(otherPlayer.id);
    }
}
