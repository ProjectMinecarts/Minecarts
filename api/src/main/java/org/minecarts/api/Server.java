package org.minecarts.api;

import java.util.List;

public interface Server {

    /**
     * Gets the name of this server implementation
     */
    public String getName();

    /**
     * Gets the Minecraft version this server is running
     */
    public String getMinecraftVersion();

    /**
     * Gets the Minecarts version
     */
    public String getMinecartsVersion();

    /**
     * Enable/disable all plugins
     */
    public void setPluginsEnabled(boolean enabled);

    /**
     * Get a list of the worlds
     */
    public List<World> getWorlds();

    /**
     * Get the world with the given name
     */
    public World getWorld(String worldName);

    /**
     * Create a world with the given settings
     */
    public World createWorld(String worldName, WorldSettings settings);

    /**
     * Unload the given world
     * 
     * Set the boolean to true in order save the chunks
     * 
     * Returns true if successful; false otherwise
     */
    public Boolean unloadWorld(World world, Boolean saveChunks);

    /**
     * Run a command as the server console
     */
    public void executeConsoleCommand(String command);

    /**
     * Broadcast a message to all players
     */
    public void broadcast(String message);

    /**
     * Broadcast a message to all players with the designated permission
     */
    public void broadcast(String message, String permission);

}