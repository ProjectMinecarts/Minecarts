package org.minecarts.api.command;

import org.minecarts.api.Minecarts;

public class ConsoleCommandSender implements CommandSender {

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public void sendMessage(String message) {
        Minecarts.getServer().getLogger().info(message);
    }

    @Override
    public void sendMessage(String[] message) {
        for (String s : message) sendMessage(s);
    }

}