package org.minecarts.api.command;

import java.util.ArrayList;
import java.util.List;

import org.minecarts.api.plugin.IPlugin;

public class Command {

    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private CommandExecutor ex;
    private IPlugin pl;
    private String prefix;

    public Command(IPlugin plugin, String name) {
        this(plugin.getName(), name);
        this.pl = plugin;
    }

    public Command(String s, String name) {
        this(s.toLowerCase(), name, "", "/" + name, new ArrayList<String>());
    }

    public Command(String prefix, String name, String description, String usage, List<String> aliases) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
        this.aliases.add(prefix + ":" + name);
        this.prefix = prefix;

        CommandMap.map.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public List<String> getAliases() {
        return aliases;
    }

    /**
     * This method is perfered to {@link getAliases#add} as it also 
     * adds "pluginname:alias" along with the alias, and is cleaner.
     */
    public List<String> addAlias(String alias) {
        aliases.add(alias);
        aliases.add(prefix + ":" + alias);
        return aliases;
    }

    /**
     * Tab complete on commands is not 100% complete yet.
     * <b>The arguments to this method most likey will change!</b>
     */
    public List<String> tabComplete() {
        return null;
    }

    public CommandExecutor getExecutor() {
        return ex;
    }

    public void setExecutor(CommandExecutor ex) {
        this.ex = ex;
    }

    /**
     * Get the plugin that registered this command.
     * if registered not by a plugin this method returns null
     */
    public IPlugin getPlugin() {
        return pl;
    }

    /**
     * returns if this Command was registered by a plugin
     */
    public boolean isPluginCommand() {
        return null != pl;
    }
}