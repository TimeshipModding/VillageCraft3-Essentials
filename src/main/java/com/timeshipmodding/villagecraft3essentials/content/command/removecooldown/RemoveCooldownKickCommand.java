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
                        Commands.literal("oasis")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeOasis(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeOasis(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))
                .then(
                        Commands.literal("swedishfurniturestore")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeSwedishFurnitureStore(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeSwedishFurnitureStore(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))
                .then(
                        Commands.literal("london")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeLondon(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeLondon(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))
                .then(
                        Commands.literal("blackwater")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .executes(context -> executeBlackWater(context, EntityArgument.getPlayers(context, "targets"), false))
                                        .then(Commands.argument("hideMessage", BoolArgumentType.bool())
                                                .executes(context -> executeBlackWater(context, EntityArgument.getPlayers(context, "targets"), !BoolArgumentType.getBool(context, "hideMessage"))))))));
    }

    private Component targetPlayerUsername;

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.resetVillageCraftCityKickCooldown());

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

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("VillageCraft City").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeOasis(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.OASIS_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.resetOasisKickCooldown());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("Oasis").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("Oasis").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("Oasis").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeSwedishFurnitureStore(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.resetSwedishFurnitureStoreCooldown());
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("Swedish Furniture Store").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("Swedish Furniture Store").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("Swedish Furniture Store").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeLondon(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.LONDON_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.resetLondonKickCooldown());
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("London").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("London").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("London").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeBlackWater(CommandContext<CommandSourceStack> context, Collection<? extends ServerPlayer> targets, boolean hideMessage) {
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.BLACKWATER_GROUP_NAME.get());
        }

        for (ServerPlayer player : targets) {
            if (player.hasData(ModDataAttachments.KICK_COMMAND_DATA)) {
                KickCommandData kickCommandData = player.getData(ModDataAttachments.KICK_COMMAND_DATA);
                player.setData(ModDataAttachments.KICK_COMMAND_DATA, kickCommandData.resetBlackWaterKickCooldown());
                targetPlayerUsername = Objects.requireNonNull(player.getDisplayName());

                if (hideMessage) {
                    MutableComponent message = Component.literal("Your ");
                    message.append(Component.literal("BlackWater").withStyle(groupStyling));
                    message.append(" kick command cooldown has been reset!");
                    player.sendSystemMessage(message, false);
                    return 1;
                }

            } else {
                MutableComponent message = targetPlayerUsername.copy();
                message.append(" has no ");
                message.append(Component.literal("BlackWater").withStyle(groupStyling));
                message.append(" kick command cooldown.");
                context.getSource().sendFailure(message);
                return -1;
            }
        }

        MutableComponent message = Component.literal("You have reset ");
        message.append(targetPlayerUsername);
        message.append("'s ");
        message.append(Component.literal("BlackWater").withStyle(groupStyling));
        message.append("kick command cooldown!");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}