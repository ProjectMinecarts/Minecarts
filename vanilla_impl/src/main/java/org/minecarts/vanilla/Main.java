package org.minecarts.vanilla;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import net.minecraft.launchwrapper.Launch;

public class Main {
    public static String minecraftJar = 
            "https://launcher.mojang.com/mc/game/1.13/server/d0caafb8438ebd206f99930cfaecfa6c9a13dca0/server.jar";
    public static String[] args;
    public static Set<File> tweakers = new HashSet<File>();

    public final static String minecraftVersion;
    public final static String build;

    public static void main(String[] args) {
        Main.args = args;

        System.out.println("Searching for additional tweakers...");

        List<String> tweakClasses = Arrays.asList("org.minecarts.vanilla.launch.MinecartsTweaker", 
                "org.spongepowered.asm.launch.MixinTweaker");

        File plugins = new File("plugins");
        if (plugins.exists() && plugins.isDirectory()) {
            for (File file : plugins.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    try (JarFile jarFile = new JarFile(file)) {
                        Manifest ex = jarFile.getManifest();
                        if (ex != null) {
                            Attributes var3 = ex.getMainAttributes();
                            String tweakClass = var3.getValue("TweakClass");
                            if (tweakClass != null) {
                                tweakers.add(file);
                                addURL(file.toURI().toURL());
                                if (!tweakClasses.contains(tweakClass))
                                    tweakClasses.add(tweakClass);
                            }
                        }
                    } catch (Throwable th) {
                        System.err.println("Failed to load tweaker file " + file + " T:" + th);
                    }
                }
            }
        }

        List<String> options = new ArrayList<>();
        options.addAll(Arrays.asList("-version", "mnecarts.org",
                      "-gameDir", ".", "-assetsDir", "."));
        

        if (isMinecraftServerIncluded()) { // dev environment
            options.add("--mixin");
            options.add("mixins.json");
        }

        for (String tweakClass : tweakClasses) {
            options.add("--tweakClass");
            options.add(tweakClass);
        }

        Launch.main(options.toArray(new String[options.size()]));
    }

    private static void addURL(URL u) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) Launch.class.getClassLoader();
        Class<?> sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(sysloader, u);
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Could not add URL to system classloader");
        }
    }

    public static boolean isMinecraftServerIncluded() {
        try {
            Class.forName("com.mojang.util.QueueLogAppender");
            return true;
        } catch (ClassNotFoundException ignored) { return false; }
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(Main.class.getClassLoader().getResourceAsStream("build.properties"));
            minecraftVersion = properties.getProperty("mcversion", "1.13");
            build = properties.getProperty("build", "ERR_NO_BUILD_NUM");
        } catch (IOException ex) { throw new RuntimeException(ex); }
    }
}