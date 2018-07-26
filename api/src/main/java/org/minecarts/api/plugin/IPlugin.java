package org.minecarts.api.plugin;

import org.minecarts.api.Server;
import org.minecarts.command.Command;

public interface IPlugin {
    // TODO: configuration files

    /**
     * Get the plugin name.
     * 
     * @see getDescription().getName();
     */
    public String getName();

    /**
     * Get the current Minecarts server instance
     * 
     * @see Minecarts#getServer()
     */
    public Server getServer();

    /**
     * Get if the plugin is enabled
     */
    public boolean isEnabled();

    /**
     * Method called on plugin init, during load into ClassLoader
     */
    public void onLoad();

    /**
     * Main function for plugins to @Override
     */
    public void onEnable();

    public void onDisable();

    public PluginDescription getDescription();
    
    public Command getCommand(String name);

}