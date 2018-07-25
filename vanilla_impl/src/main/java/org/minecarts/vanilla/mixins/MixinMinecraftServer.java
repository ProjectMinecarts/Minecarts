package org.minecarts.vanilla.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    public String getServerModName() {
        return "Minecarts";
    }

}