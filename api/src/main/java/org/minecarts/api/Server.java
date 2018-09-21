package org.minecarts.api;

import java.util.List;
import java.util.UUID;

import org.minecarts.api.command.CommandSender;
import org.minecarts.api.entity.Player;
import org.minecarts.api.plugin.PluginManager;

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

    /**
     * Get the {@link org.minecarts.api.command.CommandSender} for the console
     */
    public CommandSender getConsoleCommandSender();

    /**
     * Get the player based on the player's name
     */
    public Player getPlayer(String name);

    /**
     * Get the player by the player's UUID
     */
    public Player getPlayer(UUID uuid);

    /**
     * Gets all players that have a simlar name to the string
     */
    public List<Player> getPlayersMatchng(String name);

    /**
     * Get obfucated nms class
     */
    public Class<?> findClass(String name) throws ClassNotFoundException;

    /**
     * Returns the obfucated nms class by the non-obfucated name.
     * ex. findClassByMap("EnumChatFormat") -> a.class
     */
    public Class<?> findClassByMap(String name) throws ClassNotFoundException;

    /**
     * Returns the server's plugin manager.
     */
    public PluginManager getPluginManager();

}