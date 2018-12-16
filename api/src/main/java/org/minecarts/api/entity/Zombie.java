package org.minecarts.api.entity;

public interface Zombie extends LivingEntity {

    @Override
    public default EntityType getType() {
        return EntityType.ZOMBIE;
    }

}