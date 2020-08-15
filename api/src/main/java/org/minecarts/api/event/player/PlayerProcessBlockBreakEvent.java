package org.minecarts.api.event.player;

import org.minecarts.api.block.BlockPos;
import org.minecarts.api.block.BlockState;
import org.minecarts.api.entity.Player;
import org.minecarts.api.event.Cancelable;

public class PlayerProcessBlockBreakEvent extends PlayerEvent implements Cancelable {

    private boolean cancel;
    private BlockPos pos;

    public PlayerProcessBlockBreakEvent(Player player, BlockPos pos) {
        super(player);
        this.cancel = false;
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

    public BlockPos getBlockPos() {
        return pos;
    }

}