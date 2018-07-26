package org.minecarts.command.defaults;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.command.CommandBase;
import org.minecarts.command.CommandExecutor;
import org.minecarts.command.CommandSender;

public class VersionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, CommandBase command, String label, String[] args) {
        Server s = Minecarts.getServer();
        sender.sendMessage("This server runs Minecarts version " + s.getMinecraftVersion() + 
                " (API: " + s.getMinecartsVersion() + ")");
        return true;
    }

}
