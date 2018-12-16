package org.minecarts.api;

public enum Gamemode {

    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR;

    @Deprecated
    public int getLegacyId() {
        return this.ordinal();
    }

    @Deprecated
    public Gamemode getByLegacyId(int id) {
        switch (id) {
            case 0:
                return Gamemode.SURVIVAL;
            case 1:
                return Gamemode.CREATIVE;
            case 2:
                return Gamemode.ADVENTURE;
            case 3:
                return Gamemode.SPECTATOR;
            default:
                return Gamemode.SURVIVAL;
        }
    }

}