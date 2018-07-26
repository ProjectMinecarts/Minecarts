package org.minecarts.vanilla;

import org.minecarts.command.CommandBase;

public class CommandManagerImpl {

    public CommandWrapper register(CommandBase c) {
        CommandWrapper wrapper = new CommandWrapper(c);
        wrapper.register(c.getName());
        for (String label : c.getAliases()) {
            wrapper.register(label);
        }
        return wrapper;
    }

}