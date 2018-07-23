package org.minecarts.api;

public class Minecarts {

    private static Server server;

    /**
     * Get the current Server instance.
     */
    public static Server getServer() {
        return server;
    }

    /**
     * Attempts to set the {@link Server} singleton.
     *
     * @param server Server instance
     */
    public static void setServer(Server server) {
        if (Minecarts.server != null) throw new UnsupportedOperationException("Minecarts.server != null");

        Minecarts.server = server;
        System.out.println("This server is running " + server.getName() + " version " + 
                server.getMinecartsVersion() + " (Minecraft version " + server.getMinecraftVersion() +")");
    }

}