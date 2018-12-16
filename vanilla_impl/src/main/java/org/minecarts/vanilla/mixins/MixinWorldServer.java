package org.minecarts.vanilla.mixins;

import java.util.Collections;
import java.util.List;

import org.minecarts.api.World;
import org.minecarts.api.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer extends MixinWorld implements World {

    @Shadow(remap=false)
    public abstract WorldInfo g();

    @Final
    @Shadow(remap=false)
    public List<net.minecraft.entity.Entity> f;

    @Override
    public String getName() {
        return g().j();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> getEntities() {
        return (List<Entity>) (Object) Collections.unmodifiableList(f);
    }

}
