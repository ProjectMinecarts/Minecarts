package org.minecarts.vanilla;

import org.minecarts.api.command.Command;

public class CommandManagerImpl {

    public CommandWrapper register(Command c) {
        CommandWrapper wrapper = new CommandWrapper(c);
        wrapper.register(c.getName());
        for (String label : c.getAliases())
            wrapper.register(label);

        return wrapper;
    }

}