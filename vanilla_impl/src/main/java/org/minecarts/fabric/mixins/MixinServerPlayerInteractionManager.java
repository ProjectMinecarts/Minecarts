package org.minecarts.fabric.mixins;

import org.minecarts.api.entity.Player;
import org.minecarts.api.event.EventRegistery;
import org.minecarts.api.event.player.PlayerBlockBreakEvent;
import org.minecarts.api.event.player.PlayerBlockInteractEvent;
import org.minecarts.api.event.player.PlayerProcessBlockBreakEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(ServerPlayerInteractionManager.class)
public class MixinServerPlayerInteractionManager {

    @Shadow
    public ServerPlayerEntity player;

    @SuppressWarnings("rawtypes")
    @Inject(at = @At(value = "INVOKE"), method = "interactBlock", cancellable = true)
    public void rightClick(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable ci) {
        org.minecarts.api.entity.Hand mhand = org.minecarts.api.entity.Hand.MAIN_HAND;
        switch (hand) {
            case field_5808:
                mhand = org.minecarts.api.entity.Hand.MAIN_HAND;
                break;
            case field_5810:
                mhand = org.minecarts.api.entity.Hand.OFF_HAND;
                break;
            default:
                break;
        }

        PlayerBlockInteractEvent event = new PlayerBlockInteractEvent((Player)this.player, (org.minecarts.api.World)this.player.getServerWorld(), mhand, (org.minecarts.api.block.BlockPos)hitResult.getBlockPos());
        EventRegistery.invoke(PlayerBlockInteractEvent.class, event);

        if (event.isCanceled()) {
            ci.cancel();
            itStillExists(hitResult.getBlockPos());
            return;
        }
    }

   @Inject (method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V"), 
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
   private void breakBlock(BlockPos blockpos, CallbackInfoReturnable<Boolean> ci, BlockState state, BlockEntity entity, Block bl) {
        PlayerBlockBreakEvent event = new PlayerBlockBreakEvent((Player)this.player, (org.minecarts.api.block.BlockPos)blockpos);
        EventRegistery.invoke(PlayerBlockBreakEvent.class, event);

        if (event.isCanceled()) {
            ci.setReturnValue(false);
            itStillExists(blockpos);
            return;
        }
   }

    @Inject(at = @At(value = "INVOKE"), method = "processBlockBreakingAction", cancellable = true)
    public void leftClick(BlockPos blockpos, PlayerActionC2SPacket.Action action, Direction direction, int integer, CallbackInfo ci) {
        PlayerProcessBlockBreakEvent event = new PlayerProcessBlockBreakEvent((Player)this.player, (org.minecarts.api.block.BlockPos)blockpos);
        EventRegistery.invoke(PlayerProcessBlockBreakEvent.class, event);

        if (event.isCanceled()) {
            ci.cancel();
            itStillExists(blockpos);
            return;
        }
    }

    // Let the client know that the block isn't broken
    public void itStillExists(BlockPos pos) {
        this.player.networkHandler.sendPacket(new BlockUpdateS2CPacket(this.player.getServerWorld(), pos));
        BlockEntity tileentity = player.getServerWorld().getBlockEntity(pos);
        if (tileentity != null)
            this.player.networkHandler.sendPacket(tileentity.toUpdatePacket());
    }

}