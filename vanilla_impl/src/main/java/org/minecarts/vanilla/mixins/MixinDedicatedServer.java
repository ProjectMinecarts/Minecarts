package org.minecarts.vanilla.mixins;

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

        System.out.println("  __  __  _                                 _     MC " + Main.minecraftVersion + ", b" + Main.buildNumber);
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
            System.out.println("[Minecarts]: Enabling plugins ...");
            ServerImpl.pm.onEnable();
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