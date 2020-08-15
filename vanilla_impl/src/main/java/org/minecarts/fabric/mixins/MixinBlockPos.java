package org.minecarts.fabric.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.util.math.BlockPos;

@Mixin(BlockPos.class)
public class MixinBlockPos implements org.minecarts.api.block.BlockPos {

    @Override @Shadow public int getX() {return 0;}
    @Override @Shadow public int getY() {return 0;}
    @Override @Shadow public int getZ() {return 0;}

}