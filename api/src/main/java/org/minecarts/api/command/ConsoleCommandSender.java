package org.minecarts.api.command;

public class ConsoleCommandSender implements CommandSender {

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void sendMessage(String[] message) {
        for (String s : message) sendMessage(s);
    }

}