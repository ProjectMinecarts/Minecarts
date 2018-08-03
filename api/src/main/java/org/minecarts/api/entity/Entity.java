package org.minecarts.api.entity;

import java.util.UUID;

import org.minecarts.api.Location;
import org.minecarts.api.World;
import org.minecarts.api.command.CommandSender;

public interface Entity extends CommandSender {

    /**
     * get the Location of this Entity.
     */
    public Location getLocation();

    /**
     * Teleport this entity to a new Location
     */
    public void teleport(Location l);

    /**
     * Teleport this entity to a new Location
     */
    public void teleport(int x, int y, int z);

    /**
     * Get the World the Entity is in
     */
    public World getWorld();

    /**
     * Get the Type of this entity.
     * 
     * @see EntityType
     */
    public default EntityType getType() {
        return EntityType.ENTITY;
    }

    /**
     * Get the UUID of this Entity
     */
    public UUID getUUID();

    /**
     * Kills this Entity
     */
    public void kill();

}