package org.minecarts.vanilla.mixins;

import org.minecarts.api.entity.EntityType;
import org.minecarts.api.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinEntityPlayerMP extends MixinEntityPlayer implements Player {

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    public Object getConfiguration() {
        return null; // TODO: Configuration files
    }

}