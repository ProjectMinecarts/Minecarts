package org.minecarts.vanilla;

import java.io.File;
import java.util.List;

import org.minecarts.api.Minecarts;
import org.minecarts.api.Server;
import org.minecarts.api.World;
import org.minecarts.api.WorldSettings;
import org.minecarts.api.command.CommandSender;
import org.minecarts.api.command.ConsoleCommandSender;
import org.minecarts.api.plugin.PluginManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class ServerImpl implements Server {

    public static MinecraftServer server;
    public static String build = "1";
    public static PluginManager pm = new PluginManager();
    public static ServerImpl instance;

    public static void onStartup() {
        Minecarts.setServer(instance);
        File plfolder = new File("plugins");
        plfolder.mkdirs();
        System.out.println("[Minecarts]: Loading plugins ...");
        pm.start(plfolder); 
    }

    @Override
    public String getName() {
        return "Minecarts";
    }

    @Override
    public String getMinecraftVersion() {
        return "1.13";
    }

    @Override
    public String getMinecartsVersion() {
        return build;
    }

    @Override
    public void setPluginsEnabled(boolean enabled) {
        if (enabled) pm.onEnable(); else pm.onDisable();
    }

    @Override
    public List<World> getWorlds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public World getWorld(String worldName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public World createWorld(String worldName, WorldSettings settings) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean unloadWorld(World world, Boolean saveChunks) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void executeConsoleCommand(String command) {
        // TODO Auto-generated method stub
    }

    @Override
    public void broadcast(String message) {
        for (EntityPlayerMP p : server.ae().v()) 
            p.a(new TextComponentString(message));
    }

    @Override
    public void broadcast(String message, String permission) {
        // TODO: Permissons
        for (EntityPlayerMP p : server.ae().v()) { 
            p.a(new TextComponentString(message));
        }
    }

    @Override
    public CommandSender getConsoleCommandSender() {
        return new ConsoleCommandSender();
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return Launch.classLoader.findClass(name);
    }

}