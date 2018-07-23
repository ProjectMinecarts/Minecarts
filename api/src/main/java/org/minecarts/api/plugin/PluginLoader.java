package org.minecarts.api.plugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PluginLoader {

    private final Map<String, Class<?>> classes = new HashMap<>();
    private final Map<String, PluginClassLoader> loaders = new LinkedHashMap<>();

    Class<?> getClassByName(final String name) {
        Class<?> cached = classes.get(name);

        if (cached != null) return cached;
        else {
            for (String current : loaders.keySet()) {
                PluginClassLoader loader = loaders.get(current);

                try { cached = loader.findClass(name, false); } catch (ClassNotFoundException e) { e.printStackTrace(); }

                if (cached != null) return cached;
            }
        }
        return null;
    }

    void setClass(final String name, final Class<?> clazz) {
        if (!classes.containsKey(name)) classes.put(name, clazz);
    }

}