package org.minecarts.api;

public class Location {

    public World world;
    public double x, y, z;

    private float pitch, yaw;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}