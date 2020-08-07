package org.minecarts.fabric.mixins;

import org.minecarts.api.ChatColor;
import org.minecarts.api.command.Command;
import org.minecarts.api.command.CommandMap;
import org.minecarts.api.event.EventRegistery;
import org.minecarts.api.event.server.ServerFullyLoadedEvent;
import org.minecarts.api.logging.Logger;
import org.minecarts.command.defaults.CommandPlugins;
import org.minecarts.command.defaults.CommandVersion;
import org.minecarts.fabric.CommandWrapper;
import org.minecarts.fabric.ServerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    private Logger logger = new Logger("[" + ChatColor.WHITE + "Minecarts" + ChatColor.RESET + "]: " + ChatColor.RESET);

    @Inject(at = @At("TAIL"), method = "loadWorld")
    public void afterWorldLoad(CallbackInfo ci) {
        Command ver = new Command("minecarts", "version");
        ver.addAlias("ver");
        ver.setExecutor(new CommandVersion());

        Command plugins = new Command("minecarts", "plugins");
        plugins.addAlias("pl");
        plugins.setExecutor(new CommandPlugins());

        logger.info("Enabling plugins ...");
        ServerImpl.pm.setAllEnabled(true);

        for (String s : CommandMap.map.keySet()) register(CommandMap.map.get(s));

        logger.info(ChatColor.GREEN + "Server fully loaded.");
        EventRegistery.invoke(ServerFullyLoadedEvent.class, new ServerFullyLoadedEvent());
    }

    public CommandWrapper register(Command c) {
        CommandWrapper wrapper = new CommandWrapper(c);
        wrapper.register(c.getName());
        for (String label : c.getAliases())
            wrapper.register(label);

        return wrapper;
    }

}