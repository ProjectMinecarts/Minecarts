package org.minecarts.api.entity;

public interface Player extends Entity {

    @Override
    public default EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Get the Economy balance 
     * provided by an Economy plugin 
     */
    public double getBalance();

}