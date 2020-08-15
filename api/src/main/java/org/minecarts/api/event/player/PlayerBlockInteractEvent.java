package org.minecarts.api.event.player;

import org.minecarts.api.World;
import org.minecarts.api.block.BlockPos;
import org.minecarts.api.entity.Hand;
import org.minecarts.api.entity.Player;
import org.minecarts.api.event.Cancelable;

public class PlayerBlockInteractEvent extends PlayerEvent implements Cancelable {

    private boolean cancel;
    private Hand hand;
    private BlockPos pos;

    public PlayerBlockInteractEvent(Player player, World world, Hand hand, BlockPos pos) {
        super(player);
        this.cancel = false;
        this.hand = hand;
        this.pos = pos;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    public Hand getHand() {
        return hand;
    }

    public BlockPos getBlockPos() {
        return pos;
    }

}