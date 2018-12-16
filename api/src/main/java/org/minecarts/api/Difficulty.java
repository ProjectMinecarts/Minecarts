package org.minecarts.api;

public enum Difficulty {

    PEACEFUL(0),

    EASY(1),

    NORMAL(2),

    HARD(3);

    private final int value;

    private Difficulty(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Difficulty getByValue(int value) {
        return Difficulty.values()[value];
    }

}