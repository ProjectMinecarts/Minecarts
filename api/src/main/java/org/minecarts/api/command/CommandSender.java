package org.minecarts.api.command;

public interface CommandSender {

    /**
     * Get the name of this sender.
     */
    public String getName();

    /**
     * Sends this sender a message
     */
    public void sendMessage(String message);

    /**
     * Sends this sender messages
     */
    public void sendMessage(String[] message);

}
