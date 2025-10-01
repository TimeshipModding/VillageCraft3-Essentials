package com.timeshipmodding.villagecraft2essentials.content.commands.ambercaves;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft2essentials.util.saveddata.JailSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class AmberCavesJailCommand {
    public AmberCavesJailCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("jail")
                .executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException())))
                        .then(
                                Commands.argument("targets", EntityArgument.players())
                                        .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets")))
                        )));
    }

    private ResourceKey<Level> jailDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getJailDimension() {
        return this.jailDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets) {
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getJailDimension());
        JailSavedData savedData = JailSavedData.getData(server);
        int[] jail = savedData.getAmberCavesJail();

        if(jail[3] != 0 && jail[4] != 0) {
            for (ServerPlayer player : targets) {
                player.teleportTo(serverlevel, jail[0], jail[1], jail[2], jail[3], jail[4]);
            }

            context.getSource().sendSuccess(() -> Component.literal("You have been teleported to The Amber Caves Jail!"), false);
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("No Amber Caves Jail Position has been set."));
            return -1;
        }
    }
}