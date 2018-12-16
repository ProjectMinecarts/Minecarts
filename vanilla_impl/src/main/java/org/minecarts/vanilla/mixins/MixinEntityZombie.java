package org.minecarts.vanilla.mixins;

import org.minecarts.api.entity.Zombie;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.monster.EntityZombie;

@Mixin(EntityZombie.class)
public abstract class MixinEntityZombie implements Zombie {
}