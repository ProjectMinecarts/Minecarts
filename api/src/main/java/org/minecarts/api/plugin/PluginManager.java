package org.minecarts.api.plugin;

import java.io.File;
import java.util.HashMap;
import java.util.jar.JarFile;

public class PluginManager {
    public static HashMap<JavaPlugin, PluginDescription> plugins = new HashMap<>();
    private PluginClassLoader classLoader;

    public void start(File plfolder) {
        if (null == plfolder.listFiles()) return;

        for (File f : plfolder.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                try (JarFile jar = new JarFile(f)) {
                    PluginDescription pd = new PluginDescription(jar.getInputStream(jar.getJarEntry("plugin.yml")));

                    classLoader = new PluginClassLoader(new PluginLoader(), getClass().getClassLoader(), pd, plfolder,
                            f);
                    plugins.put(classLoader.plugin, classLoader.description);
                } catch (Exception e) { System.err.println(f.getName() + " is not a JavaPlugin. " + e.getMessage()); }
            }
        }
    }

    public void onDisable() {
        for (JavaPlugin p : plugins.keySet()) p.setEnabled(false);
    }

    public void onEnable() {
        for (JavaPlugin p : plugins.keySet()) p.setEnabled(true);
    }

    public void onLoad() {
        for (JavaPlugin p : plugins.keySet()) p.init(plugins.get(p));
    }
}