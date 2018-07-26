package exampleplugin;

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
    }
    
}