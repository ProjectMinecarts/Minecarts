package org.minecarts.api;

import java.util.HashMap;

import org.fusesource.jansi.Ansi.Color;

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

    public static HashMap<Character, ChatColor> map = new HashMap<>();
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

    public boolean isColor() {
        switch (this) {
            case BOLD:
                return false;
            case ITALIC:
                return false;
            case MAGIC:
                return false;
            case RESET:
                return false;
            case STRIKETHROUGH:
                return false;
            case UNDERLINE:
                return false;
            default:
                return true;
        }
    }

    /**
     * Return this ChatColor as a Jansi Color to be used
     * for Logging. Dark/Light colors will return the same Color.
     * Non-color ChatColors will return null
     */
    public Color asJansiColor() {
        switch (this) {
            case AQUA:
                return Color.CYAN;
            case BLACK:
                return Color.BLACK;
            case BLUE:
                return Color.BLUE;
            case DARK_AQUA:
                return Color.CYAN;
            case DARK_BLUE:
                return Color.BLUE;
            case DARK_GRAY:
                return Color.DEFAULT;
            case DARK_GREEN:
                return Color.GREEN;
            case DARK_PURPLE:
                return Color.MAGENTA;
            case DARK_RED:
                return Color.RED;
            case GOLD:
                return Color.YELLOW;
            case GRAY:
                return Color.DEFAULT;
            case GREEN:
                return Color.GREEN;
            case LIGHT_PURPLE:
                return Color.MAGENTA;
            case RED:
                return Color.RED;
            case RESET:
                return Color.DEFAULT;
            case WHITE:
                return Color.WHITE;
            case YELLOW:
                return Color.YELLOW;
            default:
                return null;
        }
    }

    static {
        for (ChatColor color : values()) map.put(color.color, color);
    }

}