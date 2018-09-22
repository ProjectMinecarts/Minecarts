package exampleplugin;

import org.minecarts.api.Minecarts;
import org.minecarts.api.event.EventHandler;
import org.minecarts.api.event.EventPriority;
import org.minecarts.api.event.player.PlayerCommandPreprocessEvent;
import org.minecarts.api.plugin.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        System.out.println("This method is called before the Minecraft server starts!");
    }

    @Override
    public void onEnable() {
        System.out.println("An example plugin for Minecarts!");
        System.out.println("This feals alot like coding a Bukkit plugin");

        System.out.println("Registering /examplecommand :");
        getCommand("examplecommand").setExecutor(new ExampleCommand());
        getCommand("wild").setExecutor(new CommandWild());

        Minecarts.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void anEventMethodThingy(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 1");
        System.out.println("sent message 1");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void anEventMethodThingy2(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 2");
        System.out.println("sent message 2");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void anEventMethodThingy3(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 3");
        System.out.println("sent message 3");
    }

}