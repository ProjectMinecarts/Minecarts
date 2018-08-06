package org.minecarts.api.event.player;

import org.minecarts.api.entity.Player;
import org.minecarts.api.event.Event;

/**
 * Base class for events dealing with Players
 */
public class PlayerEvent extends Event {

    private Player p;

    public PlayerEvent(Player csm) {
        this.p = csm;
    }

    public Player getPlayer() {
        return p;
    }

}