package org.minecarts.api.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class PluginManager {
    public static ArrayList<JavaPlugin> plugins = new ArrayList<>();
    private PluginClassLoader classLoader;

    public void start(File folder) {
        if (null == folder.listFiles()) return;

        for (File f : folder.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                try (JarFile jar = new JarFile(f)) {
                    PluginDescription pd = new PluginDescription(jar.getInputStream(jar.getJarEntry("plugin.yml")));

                    classLoader = new PluginClassLoader(new PluginLoader(), getClass().getClassLoader(), pd, folder, f);
                    plugins.add(classLoader.plugin);
                } catch (Exception e) { System.err.println(f.getName() + " is not a JavaPlugin. " + e.getMessage()); }
            }
        }
    }

    public void setAllEnabled(boolean enable) {
        for (JavaPlugin p : plugins) p.setEnabled(enable);
    }

}