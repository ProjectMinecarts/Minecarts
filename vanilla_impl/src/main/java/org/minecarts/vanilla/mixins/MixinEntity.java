package org.minecarts.vanilla.mixins;

import java.util.UUID;

import org.minecarts.api.Location;
import org.minecarts.api.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

@Mixin(Entity.class)
public abstract class MixinEntity implements org.minecarts.api.entity.Entity {

    @Shadow
    public void sendSystemMessage(Text text, UUID uuid) {
    }

    @Override
    public String getName() {
        return ((Entity)(Object)this).getName().asString();
    }

    @Override
    public void sendMessage(String message) {
        sendSystemMessage(new LiteralText(message), UUID.randomUUID());
    }

    @Override
    public void sendMessage(String[] message) {
        for (String s : message)
            sendMessage(s);
    }

    @Override
    public Location getLocation() {
        Vec3d pos = ((Entity)(Object)this).getPos();
        return new Location(pos.x, pos.y, pos.z);
    }

    @Override
    public void teleport(Location l) {
        ((Entity)(Object)this).teleport(l.x, l.y, l.z);
    }

    @Override
    public void teleport(int x, int y, int z) {
        ((Entity)(Object)this).teleport(x, y, z);
    }

    @Override
    public World getWorld() {
        return (World) ((Entity)(Object)this).getEntityWorld();
    }

    @Override
    public UUID getUUID() {
        return ((Entity)(Object)this).getUuid();
    }

    @Override
    public void kill() {
    }

}