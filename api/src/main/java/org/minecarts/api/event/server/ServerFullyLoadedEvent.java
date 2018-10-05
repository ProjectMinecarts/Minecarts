package org.minecarts.api.event.server;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;

/**
 * Async multi-threaded Server Fully loaded event.
 */
public class ServerFullyLoadedEvent extends ServerEvent {

    public ServerFullyLoadedEvent() {
        this.setAsync(true);
    }

    /**
     * Convinice method for {@link Minecarts#getServer()}
     */
    public Server getServer() {
        return Minecarts.getServer();
    }

}