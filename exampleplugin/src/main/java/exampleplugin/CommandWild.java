package exampleplugin;

import java.util.Random;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandExecutor;
import org.minecarts.api.command.CommandSender;
import org.minecarts.api.entity.Player;

public class CommandWild implements CommandExecutor {

    private Random r = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Teleporting randomly...");

        Player p = (Player) sender;

        p.teleport(r.nextInt(10000), r.nextInt(10) + 100, r.nextInt(10000));
        p.sendMessage("test of Minecarts Player & Entity api.");

        return true;
    }

}