package exampleplugin;

import org.minecarts.api.event.Event;
import org.minecarts.api.event.EventHandler;
import org.minecarts.api.event.EventRegistery;
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

        EventRegistery.get().registerAll(this);
    }

    @EventHandler
    public void anEventMethodThingy(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command!");
    }

    @EventHandler
    public void anEventMethodThingy(Event e) {
        // Hello
    }

}