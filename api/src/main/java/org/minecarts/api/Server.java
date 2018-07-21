package org.minecarts.api;

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

}