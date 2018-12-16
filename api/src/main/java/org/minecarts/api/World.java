package org.minecarts.api;

import java.util.List;

import org.minecarts.api.entity.Entity;

public interface World {

    /**
     * Get the name of this world
     */
    public String getName();

    /**
     * Get the Entities in this world
     */
    public List<Entity> getEntities();

}