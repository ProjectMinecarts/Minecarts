package org.minecarts.api.plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;

final class PluginClassLoader extends URLClassLoader {

    private final PluginLoader loader;
    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    protected final PluginDescription description;
    private final JarFile jar;
    protected final JavaPlugin plugin;

    static {
        ClassLoader.registerAsParallelCapable();
    }

    PluginClassLoader(final PluginLoader loader, final ClassLoader parent, final PluginDescription des, final File dataFolder, final File file) throws IOException,  InstantiationException, IllegalAccessException {
        super(new URL[] {file.toURI().toURL()}, parent);

        this.loader = loader;
        this.description = des;
        this.jar = new JarFile(file);

        Class<? extends JavaPlugin> pluginClass;
        try {
            pluginClass = Class.forName(des.getMain(), true, this).asSubclass(JavaPlugin.class);
        } catch (ClassCastException | ClassNotFoundException e) {
            throw new IOException("Unable to get class `" + des.getMain() + "' " + e.getMessage());
        }

        if (pluginClass.isAnnotationPresent(PluginInfo.class)) {
            this.description.importFromAnnotation(pluginClass.getAnnotation(PluginInfo.class));
        }

        plugin = pluginClass.newInstance();
        plugin.init(des);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return findClass(name, true);
    }

    Class<?> findClass(String name, boolean checkGlobal) throws ClassNotFoundException {
        if (name.startsWith("org.minecarts.")) throw new ClassNotFoundException(name);

        Class<?> result = classes.get(name);

        if (result == null) {
            if (checkGlobal) result = loader.getClassByName(name);

            if (result == null) {
                result = super.findClass(name);

                if (result != null) loader.setClass(name, result);
            }

            classes.put(name, result);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally { jar.close(); }
    }

    Set<String> getClasses() {
        return classes.keySet();
    }

}