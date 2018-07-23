package org.minecarts.api.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PluginDescription {
    private String main;
    private String name;
    private String version;

    /**
     * @param f - Plugin description file
     * @throws IOException - Cant read from InputStream
     */
    public PluginDescription(final InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;

        // TODO: Read YAML properly.
        while ((read=br.readLine()) != null) {
            if (read.startsWith("main:")) main = read.split(":")[1].trim();
            if (read.startsWith("name:")) name = read.split(":")[1].trim();
            if (read.startsWith("version:")) version = read.split(":")[1].trim();
        }
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
}