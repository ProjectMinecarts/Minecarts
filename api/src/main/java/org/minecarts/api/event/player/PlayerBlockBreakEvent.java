package org.minecarts.api.event.player;

import org.minecarts.api.block.BlockPos;
import org.minecarts.api.block.BlockState;
import org.minecarts.api.entity.Player;
import org.minecarts.api.event.Cancelable;

public class PlayerBlockBreakEvent extends PlayerEvent implements Cancelable {

    private boolean cancel;
    private BlockPos state;

    public PlayerBlockBreakEvent(Player player, BlockPos blockpos) {
        super(player);
        this.cancel = false;
        this.state = blockpos;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    public BlockPos getBlockPos() {
        return state;
    }

}