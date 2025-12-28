package com.timeshipmodding.villagecraft3essentials.content.command.ambercaves;

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

public class AmberCavesWhitelistCommand {
    public AmberCavesWhitelistCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("whitelist")
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
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
        }
        
        for (ServerPlayer player : targets) {
            if (!hideMessage) {
                MutableComponent message = Component.literal("You have been whitelisted from ");
                message.append(Component.literal("The Amber Caves").withStyle(groupStyling));
                message.append(Component.literal(" and can now teleport to "));
                message.append(Component.literal("The Amber Caves'").withStyle(groupStyling));
                message.append(Component.literal(" spawn!"));
                player.sendSystemMessage(message, false);
            }

            if (ModList.get().isLoaded("luckperms")) {
                LuckpermsMethods.removeGroup(player, ServerConfig.AMBERCAVES_BLACKLISTED_GROUP_NAME.get());
            }

            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());
        }

        MutableComponent message = Component.literal("You have whitelisted ");
        message.append(targetPlayerUsername);
        message.append(Component.literal(" from "));
        message.append(Component.literal("The Amber Caves").withStyle(groupStyling));
        message.append(Component.literal(" and they can now teleport to "));
        message.append(Component.literal("The Amber Caves'").withStyle(groupStyling));
        message.append(Component.literal(" spawn!"));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}