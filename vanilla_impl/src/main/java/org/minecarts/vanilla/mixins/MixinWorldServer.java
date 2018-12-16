package org.minecarts.vanilla.mixins;

import java.util.Collections;
import java.util.List;

import org.minecarts.api.Difficulty;
import org.minecarts.api.World;
import org.minecarts.api.entity.Entity;
import org.minecarts.api.entity.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer extends MixinWorld implements World {

    @Shadow(remap=false)
    public abstract WorldInfo g();

    @Final
    @Shadow(remap=false)
    public List<net.minecraft.entity.Entity> f;

    @Shadow(remap=false)
    public abstract EnumDifficulty aj();

    @Shadow(remap=false)
    public abstract EntityPlayer a(String s);

    @Override
    public String getName() {
        return g().j();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> getEntities() {
        return (List<Entity>) (Object) Collections.unmodifiableList(f);
    }

    @Override
    public Difficulty getDifficulty() {
        switch (aj()) {
            case a:
                return Difficulty.PEACEFUL;
            case b:
                return Difficulty.EASY;
            case c:
                return Difficulty.NORMAL;
            case d:
                return Difficulty.HARD;
            default:
                return Difficulty.NORMAL;
        }
    }

    @Override
    public Player getPlayer(String name) {
        return (Player) a(name);
    }

}