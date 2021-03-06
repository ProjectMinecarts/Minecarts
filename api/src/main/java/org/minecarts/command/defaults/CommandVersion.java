package org.minecarts.command.defaults;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;

public class CommandVersion implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server s = Minecarts.getServer();
        sender.sendMessage("This server runs Minecarts version " + s.getMinecraftVersion() + 
                " (API: " + s.getMinecartsVersion() + ")");
        return true;
    }

}