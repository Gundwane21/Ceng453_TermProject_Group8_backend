package com.group8rhea.monopolyserver.game;

import java.util.Objects;

public abstract class MonopolyPlayer implements Player{
    protected Integer id;
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
