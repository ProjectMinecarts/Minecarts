package org.minecarts.vanilla.mixins;

import org.minecarts.api.entity.EntityType;
import org.minecarts.api.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.EntityPlayerMP;

@Mixin(EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP extends MixinEntityPlayer implements Player {

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Get the Economy balance 
     * provided by an Economy plugin 
     */
    public double getBalance() {
        return 0; // TODO
    }

}