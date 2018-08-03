package org.minecarts.vanilla.mixins;

import java.util.UUID;

import org.minecarts.api.Location;
import org.minecarts.api.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

@Mixin(Entity.class)
public abstract class MixinEntity implements org.minecarts.api.entity.Entity {

    @Shadow(remap=false)
    public abstract void a(ITextComponent text);
    
    @Shadow(remap=false)
    public abstract UUID bt();
    
    @Shadow(remap=false)
    public abstract net.minecraft.world.World bJ();

    @Shadow(remap=false)
    public abstract void a(double d, double d2, double d3); // TODO: is right method?

    @Override
    public String getName() {
        return null; // TODO
    }

    @Override
    public void sendMessage(String message) {
        a(new TextComponentString(message));
    }

    @Override
    public void sendMessage(String[] message) {
        for (String s : message)
            sendMessage(s);
    }

    @Override
    public Location getLocation() {
        // TODO Auto-generated method stub
        Entity e;
   
        return null;
    }

    @Override
    public void teleport(Location l) {
        a(l.x, l.y, l.z);
    }

    @Override
    public void teleport(int x, int y, int z) {
        a(x, y, z);
    }

    @Override
    public World getWorld() {
        // nms -> bJ()
        // TODO: add World & WorldServer mixin
        return null;
    }

    @Override
    public UUID getUUID() {
        return bt();
    }

    @Override
    public void kill() {
    }

}