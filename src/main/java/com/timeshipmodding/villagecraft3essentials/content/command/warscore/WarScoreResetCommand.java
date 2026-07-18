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
                        Commands.literal("all").executes(this::executeResetAll))
                .then(
                        Commands.literal("villagecraftcity").executes(this::executeResetVillageCraftCity))
                .then(
                        Commands.literal("grippercity").executes(this::executeResetGripperCity))
                .then(
                        Commands.literal("ambercaves").executes(this::executeResetAmberCaves))));
    }

    private int executeResetAll(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            data.resetVillagecraftCityWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetGripperCityWarScore(server.getLevel(ServerLevel.OVERWORLD));
            data.resetAmberCavesWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("All towns' war points and score have been reset to 0.");
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeResetVillageCraftCity(CommandContext<CommandSourceStack> context) {
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

    private int executeResetGripperCity(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
            }

            data.resetGripperCityWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("Gripper City's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeResetAmberCaves(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
            }

            data.resetAmberCavesWarScore(server.getLevel(ServerLevel.OVERWORLD));
            MutableComponent message = Component.literal("Amber Caves's").withStyle(groupStyling);
            message.append(Component.literal(" war points and score have been reset to 0."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }
}