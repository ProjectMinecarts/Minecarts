package org.minecarts.api.entity;

public interface Player extends LivingEntity {

    @Override
    public default EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Get the Player's API configuration file.
     * Useful for storing econ balances & 
     * other usefull infomation
     */
    // TODO: Change return file once implmented.
    public Object getConfiguration();

}