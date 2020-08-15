package org.minecarts.api.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PluginDescription {

    private String main;
    private String name;
    private String version;
    private String description;

    /**
     * @param f - Plugin description file
     * @throws IOException - Cant read from InputStream
     */
    @Deprecated
    public PluginDescription(final InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        // TODO: Read YAML properly.
        while ((line=br.readLine()) != null) {
            if (line.startsWith("main:")) main = line.split(":")[1].trim();
            if (line.startsWith("name:")) name = line.split(":")[1].trim();
            if (line.startsWith("version:")) version = line.split(":")[1].trim();
            if (line.startsWith("description:")) description = line.split(":")[1].trim();
        }
    }

    public PluginDescription(PluginInfo i) {
        importFromAnnotation(i);
    }

    public PluginDescription importFromAnnotation(PluginInfo i) {
        if (null != i.name()) this.name = i.name();
        if (null != i.version()) this.version = i.version();
        if (null != i.description()) this.description = i.description();

        return this;
    }

    public String getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
    
    public String getDescription() {
        return description;
    }

}