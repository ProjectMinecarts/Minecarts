package org.minecarts.api.logging;

import org.minecarts.api.plugin.IPlugin;

public class PluginLogger extends Logger {

    private IPlugin pl;

    public PluginLogger(IPlugin pl) {
        super("[" + pl.getName() + "]: ");
        this.pl = pl;
    }

    public IPlugin getPlugin() {
        return pl;
    }

}