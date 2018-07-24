package org.minecarts.vanilla;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.common.io.ByteStreams;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class MinecartsTweaker implements ITweaker {
    
    static {
        Launch.classLoader.addClassLoaderExclusion("com.google.gson.");
        Launch.classLoader.addClassLoaderExclusion("org.apache.commons.io");
        Launch.classLoader.addClassLoaderExclusion("org.minecarts.api.");

        if (!Main.isMinecraftServerIncluded()) {
            // download server jar
            File cache = new File("cache");
            cache.mkdir();

            File serverJar = new File(cache, "minecraft_server." + Main.minecraftVersion + ".jar");
            if (!serverJar.exists()) {
                System.out.println("Downloading Minecraft_server.jar Please wait.");
                try {
                    URL url = new URL(Main.minecraftJar);
                    InputStream in = url.openStream();
                    OutputStream out = new FileOutputStream(serverJar);
                    ByteStreams.copy(in, out);
                    in.close();
                    out.close();
                    System.out.println("Downloaded minecraft server");
                } catch (IOException ex) {
                    System.err.println("Failed to download <inecraft: " + ex);
                    System.exit(-1);
                }
            }

            try {
                System.out.println("Adding Minecraft server to classpath");
                Launch.classLoader.addURL(serverJar.toURI().toURL());
            } catch (MalformedURLException ex) {
                System.err.println("Failed to add minecraft server to classpath. " + ex);
                System.exit(-1);
            }
        }
    }

    @Override
    public void acceptOptions(List<String> arg0, File arg1, File arg2, String arg3) {
    }

    @Override
    public String[] getLaunchArguments() {
        return Main.args;
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.server.MinecraftServer";
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader arg0) {
    }

}