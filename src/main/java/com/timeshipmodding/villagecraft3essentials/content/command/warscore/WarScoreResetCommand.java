package com.timeshipmodding.villagecraft3essentials.content.command.warscore;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.WarPointsSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.fml.ModList;

import java.awt.*;

public class WarScoreResetCommand {
    public WarScoreResetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("reset")
                .then(
                        Commands.literal("all").executes(this::executeAll))
                .then(
                        Commands.literal("villagecraftcity").executes(this::executeVillageCraftCity))
                .then(
                        Commands.literal("oasis").executes(this::executeOasis))
                .then(
                        Commands.literal("swedishfurniturestore").executes(this::executeSwedishFurnitureStore))
                .then(
                        Commands.literal("london").executes(this::executeLondon))
                .then(
                        Commands.literal("blackwater").executes(this::executeBlackWater))));
    }

    private int executeAll(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            data.resetVillagecraftCityWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetOasisWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetSwedishFurnitureStoreWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetLondonWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetBlackWaterWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("All towns' war points and score have been reset to 0.");
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
            }

            data.resetVillagecraftCityWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("VillageCraft City's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeOasis(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
            }

            data.resetOasisWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("Oasis's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeSwedishFurnitureStore(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
            }

            data.resetSwedishFurnitureStoreWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("Swedish Furniture Store's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeLondon(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.LONDON_GROUP_NAME.get());
            }

            data.resetLondonWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("London's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeBlackWater(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.BLACKWATER_GROUP_NAME.get());
            }

            data.resetBlackWaterWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("BlackWater's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }
}