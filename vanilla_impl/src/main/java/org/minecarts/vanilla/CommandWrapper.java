package org.minecarts.vanilla;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import org.minecarts.api.Minecarts;
import org.minecarts.api.command.CommandSender;
import org.minecarts.api.entity.Player;

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

import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandWrapper implements Predicate<CommandSource>, SuggestionProvider<CommandSource>, Command<CommandSource>  {

    public org.minecarts.api.command.Command minecarts;
    
    public CommandWrapper(org.minecarts.api.command.Command c) {
        this.minecarts = c;
    }

    public LiteralCommandNode<CommandSource> register(String label) {
        return register(ServerImpl.server.aK().a(), label);
    }

    public LiteralCommandNode<CommandSource> register(CommandDispatcher<CommandSource> dispatcher, String label) {
        return dispatcher.register(
                LiteralArgumentBuilder.<CommandSource>literal(label).requires(this).executes(this)
                .then(RequiredArgumentBuilder.<CommandSource, String>argument("args", StringArgumentType.greedyString())
                        .suggests(this).executes(this)));
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> arg0, SuggestionsBuilder arg1)
            throws CommandSyntaxException {
        List<String> results = minecarts.tabComplete();

        for (String s : results) arg1.suggest(s);

        return arg1.buildFuture();
    }

    @Override
    public boolean test(CommandSource t) {
        return true; // TODO
    }

    @Override
    public int run(CommandContext<CommandSource> arg0) throws CommandSyntaxException {
        CommandSource cs = arg0.getSource();
        CommandSender csm;
        if (null == cs.f()) {
            csm = Minecarts.getServer().getConsoleCommandSender();
        } else {
            Entity e = cs.f();
            if (e instanceof EntityPlayerMP) {
                csm = (Player) e;
            } else csm = (org.minecarts.api.entity.Entity) e;
        }
        
        String[] split = arg0.getInput().split(" ");

        if (split.length <= 1) {
            minecarts.getExecutor().onCommand(csm, minecarts, split[0], new String[0]);
            return 0;
        }

        minecarts.getExecutor().onCommand(csm, minecarts, split[0], arg0.getInput().substring(
                arg0.getInput().indexOf(" ")).trim().split(" "));

        return 0;
    }

}