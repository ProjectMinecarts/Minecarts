package org.minecarts.api.plugin;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.command.CommandBase;

public class JavaPlugin implements IPlugin {
    private PluginDescription des;
    private boolean enabled;
    
    public JavaPlugin() {
    }

    public void init(PluginDescription des) {
        this.des = des;
        this.enabled = false;
        System.out.println(String.format("Loading %s %s ...", des.getName(), des.getVersion()));
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
    public CommandBase getCommand(String name) {
        return new CommandBase(this, name);
    }
}