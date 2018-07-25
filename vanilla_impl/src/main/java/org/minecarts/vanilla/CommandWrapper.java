package org.minecarts.vanilla;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.command.CommandSource;

public class CommandWrapper implements com.mojang.brigadier.Command<CommandSource>, 
                                        Predicate<CommandSource>, SuggestionProvider<CommandSource>  {

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> arg0, SuggestionsBuilder arg1)
            throws CommandSyntaxException {
        return null;
    }

    @Override
    public boolean test(CommandSource t) {
        return true; // Test
    }

    @Override
    public int run(CommandContext<CommandSource> arg0) throws CommandSyntaxException {
        return 0;
    }

}
