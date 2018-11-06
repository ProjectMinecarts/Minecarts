package org.minecarts.api.logging;

import static org.fusesource.jansi.Ansi.ansi;

import org.apache.logging.log4j.LogManager;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.minecarts.api.ChatColor;

public class Logger {

    public final String prefix;
    private final org.apache.logging.log4j.Logger log4j;

    public Logger(String prefix) {
        this.prefix = prefix;
        this.log4j = LogManager.getLogger(prefix);
    }

    public void info(Object o) {
        if (!LoggerSettings.useJansi) {
            log4j.info(prefix + o);
            return;
        }

        log4j.info(getFormatted(prefix + o));
    }

    public void error(Object o) {
        if (!LoggerSettings.useJansi) {
            log4j.error(prefix + o);
            return;
        }

        log4j.error(getFormatted(prefix + ChatColor.RED + "[ERR]: " + o));
    }

    public static Ansi getFormatted(String text) {
        Ansi an = ansi();
        char[] a = text.toCharArray();
        boolean skipChar = false;
        for (int b = 0; b < a.length; b++) {
            if (a[b] == '\u00A7' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(a[b+1]) > -1) {
                a[b+1] = Character.toLowerCase(a[b+1]);
                an.fgBright(ChatColor.map.get(a[b+1]).asJansiColor());
                skipChar = true;
            } else {
                if (!skipChar) an.a(a[b]);
                skipChar = false;
            }
        }
        return an.fg(Color.DEFAULT).bg(Color.DEFAULT); // Make sure every thing is back to DEFAULT for next print
    }

}