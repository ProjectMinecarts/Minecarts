package org.minecarts.api.plugin;

import java.util.HashMap;

import org.minecarts.api.ChatColor;
import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;
import org.minecarts.api.logging.Logger;
import org.minecarts.api.logging.PluginLogger;

public class JavaPlugin implements IPlugin, CommandExecutor, Listener {

    private PluginDescription des;
    private boolean enabled = false;
    private HashMap<String, Command> cmdmap;
    private Logger logger;

    public JavaPlugin() {
    }

    public void init(PluginDescription des) {
        this.des = des;
        this.enabled = false;
        this.logger = new PluginLogger(this);
        logger.info(String.format("Loading %s %s ...", des.getName(), des.getVersion()));
        this.cmdmap = new HashMap<>();
        this.onLoad();
    }

    @Override
    public String getName() {
        return getDescription().getName();
    }

    @Override
    public Server getServer() {
        return Minecarts.getServer();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public PluginDescription getDescription() {
        return des;
    }

    public void setEnabled(boolean en) {
        if (this.enabled != en) {
            this.enabled = en;

            logger.info((en ? ChatColor.GREEN + "En" : ChatColor.RED + "Dis") + 
                    String.format("abling" + ChatColor.RESET + " %s %s ...", des.getName(), des.getVersion()));
            if (this.enabled) onEnable(); else onDisable();
        }
    }

    @Override
    public Command getCommand(String name) {
        if (cmdmap.containsKey(name))
            return cmdmap.get(name);

        Command c = new Command(this, name);
        cmdmap.put(name, c);
        return c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false; // To be overridden by plugins
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}