package org.minecarts.vanilla.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public abstract class MixinEntityPlayer extends MixinEntity {
}