package org.minecarts.api.event;

public abstract class Event {

    private boolean async;

    public Event() {
        this.async = false;
    }

    public Event(boolean async) {
        this.async = async;
    }

}