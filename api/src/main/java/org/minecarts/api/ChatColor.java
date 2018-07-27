package org.minecarts.api;

public enum ChatColor {

    BLACK,

    DARK_BLUE,

    DARK_GREEN,

    DARK_AQUA,

    DARK_RED,

    DARK_PURPLE,

    GOLD,

    GRAY,

    DARK_GRAY,

    BLUE,

    GREEN('a'),

    AQUA('b'),

    RED('c'),

    LIGHT_PURPLE('d'),

    YELLOW('e'),

    WHITE('f'),

    MAGIC('k'),

    BOLD('l'),

    STRIKETHROUGH('m'),

    UNDERLINE('n'),

    ITALIC('o'),

    RESET('r');

    public static char COLOR_CHAR = '\u00A7';

    private char color;

    private ChatColor(char c) {
        this.color = c;
    }

    private ChatColor() {
        this.color = Integer.toString(this.ordinal()).charAt(0);
    }

    @Override
    public String toString() {
        return COLOR_CHAR + "" + color;
    }
}
