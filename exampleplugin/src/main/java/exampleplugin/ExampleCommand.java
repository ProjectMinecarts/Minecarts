package exampleplugin;

import org.minecarts.api.ChatColor;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;

public class ExampleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Because we only have one command set to this CommandExucator, We don't need 
        // to check the command name, but if more than one command we can use
        //
        // if (command.getName().equalsIgnoreCase("examplecommand")) {
        //      code for command
        // }

        sender.sendMessage(ChatColor.GREEN + "This is an example command! Minecarts.org");
        sender.sendMessage("Label: " + label);
        sender.sendMessage("Arguments:");
        sender.sendMessage(args);
        return true;
    }

}
