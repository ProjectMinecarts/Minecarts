package org.minecarts.api.command;

import java.util.Collections;
import java.util.List;

public abstract class Command {

    private String name;
    private String description;
    private String usage;
    private List<String> aliases;

    public Command(String name) {
        this(name, "", "/" + name, Collections.emptyList());
    }

    public Command(String name, String description, String usage, List<String> aliases) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
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

}