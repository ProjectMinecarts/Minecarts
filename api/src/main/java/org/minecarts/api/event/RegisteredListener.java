package org.minecarts.api.event;

import org.minecarts.api.plugin.Listener;

public class RegisteredListener {

    public final Listener l;
    public final EventPriority p;
    public final boolean ignoreCancelled;

    public RegisteredListener(Listener l, EventHandler h) {
        this.l = l;
        this.p = h.priority();
        this.ignoreCancelled = h.ignoreCancelled();
    }

}