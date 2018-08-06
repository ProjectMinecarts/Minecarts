package org.minecarts.api.event.player;

import org.minecarts.api.command.Command;
import org.minecarts.api.entity.Player;
import org.minecarts.api.event.Cancelable;

public class PlayerCommandPreprocessEvent extends PlayerEvent implements Cancelable {

    private Command cmd;
    private String msg;
    private boolean cancel;

    public PlayerCommandPreprocessEvent(Player csm, Command cmd, String msg) {
        super(csm);
        this.cmd = cmd;
        this.msg = msg;
    }

    public Command getCommand() {
        return cmd;
    }

    public String getMessage() {
        return msg;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = true;
    }

}