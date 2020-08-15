package org.minecarts.api.entity;

public interface Player extends LivingEntity {

    @Override
    public default EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Get the Player's configuration file.
     */
    // TODO: Change return file once implemented.
    public Object getConfiguration();

}