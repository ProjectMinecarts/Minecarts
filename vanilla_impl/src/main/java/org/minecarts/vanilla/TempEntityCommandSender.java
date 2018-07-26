package org.minecarts.vanilla;

import org.minecarts.api.command.CommandSender;

import net.minecraft.entity.Entity;
import net.minecraft.util.text.TextComponentString;

/**
 * Entities have not been added to the API yet so they have no CommandSender.
 * This is a temporary class for entity commandsenders
 */
public class TempEntityCommandSender implements CommandSender {

    public Entity e;
    
    public TempEntityCommandSender(Entity e) {
        this.e = e;
    }
    
    @Override
    public String getName() {
        return "NMS_Entity";
    }

    @Override
    public void sendMessage(String message) {
        e.a(new TextComponentString(message));
    }

    @Override
    public void sendMessage(String[] message) {
        for (String s : message) sendMessage(s);
    }

}