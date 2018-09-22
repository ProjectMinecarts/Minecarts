package org.minecarts.api.event;

import java.lang.reflect.Method;

import org.minecarts.api.plugin.Listener;

public class RegisteredListener {

    public final Listener l;
    public final EventPriority p;
    public final boolean ignoreCancelled;
    public final Method method;

    public RegisteredListener(Listener l, EventHandler h, Method m) {
        this.l = l;
        this.p = h.priority();
        this.ignoreCancelled = h.ignoreCancelled();
        this.method = m;
    }

}