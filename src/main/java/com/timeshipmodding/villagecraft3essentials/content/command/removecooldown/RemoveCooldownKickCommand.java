package com.timeshipmodding.villagecraft3essentials.content.command.removecooldown;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.KickCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.WarPointsSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

import java.util.Collection;
import java.util.Objects;

public class RemoveCooldownKickCommand {
    public RemoveCooldownKickCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("removecooldown").requires(context -> context.hasPermission(2)).then(Commands.literal("kick")
                .then(
                        Commands.literal("villagecraftcity")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeVillageCraftCity(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeVillageCraftCity(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))
                .then(
                        Commands.literal("grippercity")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeGripperCity(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeGripperCity(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))
                .then(
                        Commands.literal("ambercaves")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeAmberCaves(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeAmberCaves(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))));
    }

    private Component targetPlayerUsername;

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        for (ServerPlayer player : targets) {
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.villagecraftCityReset());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeGripperCity(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.gripperCityReset());
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("Gripper City").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("Gripper City").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("Gripper City").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeAmberCaves(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        for (ServerPlayer player : targets) {
            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.amberCavesReset());
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("Amber Caves").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("Amber Caves").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("Amber Caves").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}