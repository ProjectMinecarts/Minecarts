package org.minecarts.api.plugin;

import java.util.HashMap;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;

public class JavaPlugin implements IPlugin, CommandExecutor, Listener {
    private PluginDescription des;
    private boolean enabled = false;
    private HashMap<String, Command> cmdmap;

    public JavaPlugin() {
    }

    public void init(PluginDescription des) {
        this.des = des;
        this.enabled = false;
        System.out.println(String.format("Loading %s %s ...", des.getName(), des.getVersion()));
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

            System.out.println((en ? "En" : "Dis") + String.format("abling %s %s ...", des.getName(), des.getVersion()));
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
        return false;
    }

}