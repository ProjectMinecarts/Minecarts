package org.minecarts.api.entity;

import org.minecarts.api.Location;
import org.minecarts.api.command.CommandSender;

public interface Entity extends CommandSender {

    public Location getLocation();

}