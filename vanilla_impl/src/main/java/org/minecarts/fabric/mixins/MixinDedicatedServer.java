package org.minecarts.fabric.mixins;

import org.minecarts.api.ChatColor;
import org.minecarts.api.logging.Logger;
import org.minecarts.fabric.ServerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;

@Mixin(DedicatedServer.class)
public class MixinDedicatedServer {

    private Logger logger = new Logger("[" + ChatColor.WHITE + "Minecarts" + ChatColor.RESET + "]: " + ChatColor.RESET);

    @Inject(at = @At(value = "JUMP", ordinal = 8), method = "setupServer()Z")
    void onServerStart(CallbackInfoReturnable<Boolean> callbackInfo) {
        logger.info("Loading ProjectMinecarts...");
        ServerImpl s = new ServerImpl();
        ServerImpl.server = (MinecraftServer) (Object) this;
        ServerImpl.instance = s;
        ServerImpl.onStartup();
    }

}