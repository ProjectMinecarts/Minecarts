package org.minecarts.vanilla.mixins;

import java.util.List;

import org.minecarts.api.Difficulty;
import org.minecarts.api.World;
import org.minecarts.api.entity.Entity;
import org.minecarts.api.entity.Player;
import org.minecarts.vanilla.ServerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.ServerWorldProperties;

@Mixin(ServerWorld.class)
public abstract class MixinWorldServer extends MixinWorld implements World {

    @Final
    @Shadow(remap=false)
    public List<net.minecraft.entity.Entity> f;


    @Override
    public String getName() {
        return ((ServerWorldProperties) ((ServerWorld)(Object)this).getLevelProperties()).getLevelName();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Entity> getEntities() {
        return (Iterable<Entity>) (Object) ((ServerWorld)(Object)this).iterateEntities();
    }

    @Override
    public Difficulty getDifficulty() {
        switch (((ServerWorld)(Object)this).getDifficulty()) {
            case field_5801:
                return Difficulty.PEACEFUL;
            case field_5802:
                return Difficulty.EASY;
            case field_5805:
                return Difficulty.NORMAL;
            case field_5807:
                return Difficulty.HARD;
            default:
                return Difficulty.NORMAL;
        }
    }

    @Override
    @Deprecated
    public Player getPlayer(String name) {
        return ServerImpl.instance.getPlayer(name);
    }

}