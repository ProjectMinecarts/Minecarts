package org.minecarts.vanilla.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.EntityPlayer;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntity {
}