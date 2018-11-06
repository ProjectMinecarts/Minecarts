package exampleplugin;

import org.minecarts.api.event.EventHandler;
import org.minecarts.api.event.EventPriority;
import org.minecarts.api.event.player.PlayerCommandPreprocessEvent;
import org.minecarts.api.event.server.ServerFullyLoadedEvent;
import org.minecarts.api.plugin.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        getLogger().info("This method is called before the Minecraft server starts!");
    }

    @Override
    public void onEnable() {
        getLogger().info("An example plugin for Minecarts!");
        getLogger().info("This feals alot like coding a Bukkit plugin");

        System.out.println("Registering /examplecommand :");
        getCommand("examplecommand").setExecutor(new ExampleCommand());
        getCommand("wild").setExecutor(new CommandWild());

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void aServerFullyLoadedExample(ServerFullyLoadedEvent e) {
        getLogger().info(e.getServer().getName() + " server fully loaded!");
    }

    @EventHandler
    public void anEventMethodThingy(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 1");
        getLogger().info("sent message 1");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void anEventMethodThingy2(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 2");
        getLogger().info("sent message 2");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void anEventMethodThingy3(PlayerCommandPreprocessEvent e) {
        e.getPlayer().sendMessage("player has run command! 3");
        getLogger().info("sent message 3");
    }

}