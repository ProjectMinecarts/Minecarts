package org.minecarts.api.plugin;

import org.minecarts.api.Server;
import org.minecarts.api.command.Command;

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
    public void onLoad() throws ClassNotFoundException;

    /**
     * Main function for plugins to @Override
     */
    public void onEnable();

    /**
     * Method called when the plugin is disabled (ex. Server shutdown)
     */
    public void onDisable();

    /**
     * Get the discription file of this plugin
     */
    public PluginDescription getDescription();

    /**
     * Get the command by the name.
     */
    public Command getCommand(String name);

}