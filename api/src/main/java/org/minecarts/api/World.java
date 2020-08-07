package org.minecarts.api;

import org.minecarts.api.entity.Entity;
import org.minecarts.api.entity.Player;

public interface World {

    /**
     * Get the name of this world
     */
    public String getName();

    /**
     * Get the Entities in this world
     */
    public Iterable<Entity> getEntities();

    /**
     * Get the World's {@link Difficulty} setting
     */
    public Difficulty getDifficulty();

    /**
     * Get a {@link Player} in this World
     */
    public Player getPlayer(String name);

}