package org.minecarts.command;

public interface CommandExecutor {

    public boolean onCommand(CommandSender sender, CommandBase command, String label, String[] args);

}