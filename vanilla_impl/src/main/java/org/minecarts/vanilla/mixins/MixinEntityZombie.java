package org.minecarts.vanilla.mixins;

import org.minecarts.api.entity.Zombie;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.mob.ZombieEntity;

@Mixin(ZombieEntity.class)
public abstract class MixinEntityZombie implements Zombie {
}