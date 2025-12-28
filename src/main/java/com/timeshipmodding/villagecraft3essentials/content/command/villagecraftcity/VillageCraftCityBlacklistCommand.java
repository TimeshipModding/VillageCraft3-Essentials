package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class VillageCraftCityBlacklistCommand {
    public VillageCraftCityBlacklistCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("blacklist")
                .then(
                        Commands.argument("targets", EntityArgument.players())
                                .executes(p_137810_ -> execute(p_137810_, EntityArgument.getPlayers(p_137810_, "targets"), false))
                                .then(
                                        Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(p_267909_ -> execute(p_267909_, EntityArgument.getPlayers(p_267909_, "targets"), !BoolArgumentType.getBool(p_267909_, "hideMessage")))
                                ))));
    }

    private Component targetPlayerUsername;

    private int execute(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            if (!hideMessage) {
                MutableComponent message = Component.literal("You have been blacklisted from ");
                message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
                message.append(Component.literal(" and can no longer teleport to "));
                message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
                message.append(Component.literal(" spawn!"));
                player.sendSystemMessage(message, false);
            }

            if (ModList.get().isLoaded("luckperms")) {
                LuckpermsMethods.addGroup(player, ServerConfig.VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME.get());
            }

            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());
        }

        MutableComponent message = Component.literal("You have blacklisted ");
        message.append(targetPlayerUsername);
        message.append(Component.literal(" from "));
        message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
        message.append(Component.literal(" and they can no longer teleport to "));
        message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
        message.append(Component.literal(" spawn!"));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}