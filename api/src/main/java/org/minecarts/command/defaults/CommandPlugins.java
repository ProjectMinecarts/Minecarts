package org.minecarts.command.defaults;

import org.minecarts.api.plugin.JavaPlugin;
import org.minecarts.api.plugin.PluginManager;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;

public class CommandPlugins implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String strs = "Plugins: ";
        for (JavaPlugin s : PluginManager.plugins.keySet()) {
            strs += s.getName() + ", ";
        }

        sender.sendMessage(strs);
        return true;
    }

}