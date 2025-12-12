package com.timeshipmodding.villagecraft3essentials.content.commands.grippercity;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.util.config.ServerConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class GripperCityWhitelistCommand {
    public GripperCityWhitelistCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("grippercity").then(Commands.literal("whitelist")
                .executes(p_137817_ -> execute(p_137817_, ImmutableList.of(p_137817_.getSource().getPlayerOrException()), !BoolArgumentType.getBool(p_137817_, "hideMessage")))
                        .then(
                                Commands.argument("targets", EntityArgument.players())
                                        .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets"), !BoolArgumentType.getBool(p_137810_, "hideMessage")))
                                        .then(
                                                Commands.argument("hideMessage", BoolArgumentType.bool())
                                                        .executes(p_267909_ -> execute(p_267909_, EntityArgument.getPlayers(p_267909_, "targets"), !BoolArgumentType.getBool(p_267909_, "hideMessage"))
                                        )
                        ))));
    }

    private String targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        for (ServerPlayer player : targets) {
            if (hideMessage) {
                player.sendSystemMessage(Component.literal("You have been whitelisted from Gripper City and can now teleport to Gripper City Spawn!"), false);
            }

            if (ModList.get().isLoaded("luckperms")) {
                LuckpermsMethods.removeGroup(player, ServerConfig.GRIPPERCITY_BLACKLISTED_GROUP_NAME.get());
            }

            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName()).getString();
        }

        context.getSource().sendSuccess(() -> Component.literal("You have whitelisted " + targetPlayerUsername + " from Gripper City and they can now teleport to Gripper City Spawn!"), false);
        return 1;
    }
}