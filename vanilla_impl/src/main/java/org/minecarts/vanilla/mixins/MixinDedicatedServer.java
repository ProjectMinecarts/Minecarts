package org.minecarts.vanilla.mixins;

import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandMap;
import org.minecarts.command.defaults.CommandPlugins;
import org.minecarts.command.defaults.CommandVersion;
import org.minecarts.vanilla.CommandManagerImpl;
import org.minecarts.vanilla.Main;
import org.minecarts.vanilla.ServerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

@Mixin(DedicatedServer.class)
public class MixinDedicatedServer {

    @Inject(method = "d", at = @At("HEAD"), remap = false) // remap false because d is not mapped to init
    void onServerStart(CallbackInfoReturnable<Boolean> callbackInfo) {
        ServerImpl s = new ServerImpl();
        ServerImpl.server = (MinecraftServer) (Object) this;
        ServerImpl.instance = s;
        ServerImpl.onStartup();

        System.out.println("  __  __  _                                 _     MC " + Main.minecraftVersion + ", b" + Main.build);
        System.out.println(" |  \\/  |(_)                               | |                             ");
        System.out.println(" | \\  / | _  _ __    ___   ___  __ _  _ __ | |_  ___     ___   _ __  __ _  ");
        System.out.println(" | |\\/| || || '_ \\  / _ \\ / __|/ _` || '__|| __|/ __|   / _ \\ | '__|/ _` | ");
        System.out.println(" | |  | || || | | ||  __/| (__| (_| || |   | |_ \\__ \\ _| (_) || |  | (_| | ");
        System.out.println(" |_|  |_||_||_| |_| \\___| \\___|\\__,_||_|    \\__||___/(_)\\___/ |_|   \\__, | ");
        System.out.println("                                                                     __/ | ");
        System.out.println("Minecarts.org Server Mod                                            |___/  ");
    }

    @Inject(method = "d", at = @At("RETURN"), remap = false)
    void onServerFullyLoaded(CallbackInfoReturnable<Boolean> callbackInfo) {
        if (callbackInfo.getReturnValue()) {

            CommandManagerImpl i = new CommandManagerImpl();

            Command ver = new Command("minecarts", "version");
            ver.addAlias("ver");
            ver.setExecutor(new CommandVersion());
            i.register(ver);

            Command plugins = new Command("minecarts", "plugins");
            plugins.addAlias("pl");
            plugins.setExecutor(new CommandPlugins());
            i.register(plugins);

            ServerImpl.pm.onLoad();
            System.out.println("[Minecarts]: Enabling plugins ...");
            ServerImpl.pm.onEnable();

            for (String s : CommandMap.map.keySet()) {
                i.register(CommandMap.map.get(s));
            }
            System.out.println("[Minecarts]: Server fully loaded.");
        }
    }

    public void aY() {
        // Disable the GUI
    }

    public String getServerModName() {
        return "Minecarts";
    }
}