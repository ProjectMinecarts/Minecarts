package org.minecarts.fabric;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import org.minecarts.api.Minecarts;
import org.minecarts.api.command.CommandSender;
import org.minecarts.api.entity.Player;
import org.minecarts.api.event.EventRegistery;
import org.minecarts.api.event.player.PlayerCommandPreprocessEvent;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CommandWrapper implements Predicate<ServerCommandSource>, SuggestionProvider<ServerCommandSource>, Command<ServerCommandSource>  {

    public org.minecarts.api.command.Command minecarts;

    public CommandWrapper(org.minecarts.api.command.Command c) {
        this.minecarts = c;
    }

    public LiteralCommandNode<ServerCommandSource> register(String label) {
        return register(ServerImpl.server.getCommandManager().getDispatcher(), label);
    }

    public LiteralCommandNode<ServerCommandSource> register(CommandDispatcher<ServerCommandSource> dispatcher, String label) {
        return dispatcher.register(
                LiteralArgumentBuilder.<ServerCommandSource>literal(label).requires(this).executes(this)
                .then(RequiredArgumentBuilder.<ServerCommandSource, String>argument("args", StringArgumentType.greedyString())
                        .suggests(this).executes(this)));
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> con, SuggestionsBuilder sug) throws CommandSyntaxException {
        List<String> results = minecarts.tabComplete();

        for (String s : results) sug.suggest(s);

        return sug.buildFuture();
    }

    @Override
    public boolean test(ServerCommandSource t) {
        return true; // TODO
    }

    @Override
    public int run(CommandContext<ServerCommandSource> arg0) throws CommandSyntaxException {
        ServerCommandSource cs = arg0.getSource();
        CommandSender csm;
        boolean player = false;
        boolean run = true;

        if (null == cs.getPlayer()) {
            csm = Minecarts.getServer().getConsoleCommandSender();
        } else {
            Entity e = cs.getEntity();
            if (e instanceof ServerPlayerEntity) {
                csm = (Player) e;
                player = true;
            } else csm = (org.minecarts.api.entity.Entity) e;
        }

        String[] split = arg0.getInput().split(" ");

        if (player) {
            PlayerCommandPreprocessEvent e = (PlayerCommandPreprocessEvent) 
                    EventRegistery.invoke(PlayerCommandPreprocessEvent.class, new PlayerCommandPreprocessEvent((Player)csm, minecarts, arg0.getInput()));
            run = !e.isCanceled();
        }

        if (!run) return 0;

        if (split.length <= 1) {
            minecarts.getExecutor().onCommand(csm, minecarts, split[0], new String[0]);
            return 0;
        }

        minecarts.getExecutor().onCommand(csm, minecarts, split[0], arg0.getInput().substring(
                arg0.getInput().indexOf(" ")).trim().split(" "));

        return 0;
    }

}