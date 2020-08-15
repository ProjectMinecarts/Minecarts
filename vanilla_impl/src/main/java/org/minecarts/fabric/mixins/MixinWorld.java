package org.minecarts.fabric.mixins;

import org.minecarts.api.Difficulty;
import org.minecarts.api.entity.Entity;
import org.minecarts.api.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld implements org.minecarts.api.World {

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Entity> getEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getPlayer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}