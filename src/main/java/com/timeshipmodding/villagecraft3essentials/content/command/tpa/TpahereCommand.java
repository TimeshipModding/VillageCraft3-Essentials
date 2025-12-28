package com.timeshipmodding.villagecraft3essentials.content.command.tpa;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager.TpaCommandManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public class TpahereCommand {
    public TpahereCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tpahere").executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException())))
                .then(Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))));
    }

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> target) {
        ServerPlayer player = context.getSource().getPlayer();

        if (player != null) {
            for (ServerPlayer targetPlayer : target) {
                TpaCommandManager.requestTpa(player, targetPlayer, "tpahere");
                return 1;
            }
        }

        return -1;
    }
}