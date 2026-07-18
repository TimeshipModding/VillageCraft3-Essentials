package com.timeshipmodding.villagecraft3essentials.content.command.warscore;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class WarScoreRemoveCommand {
    public WarScoreRemoveCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("remove")
                .then(
                        Commands.literal("villagecraftcity")
                        .then(Commands.argument("score", IntegerArgumentType.integer(1, 1000000))
                        .executes(context -> executeVillageCraftCity(context, IntegerArgumentType.getInteger(context, "score")))))
                .then(
                        Commands.literal("grippercity")
                        .then(Commands.argument("score", IntegerArgumentType.integer(1, 1000000))
                        .executes(context -> executeGripperCity(context, IntegerArgumentType.getInteger(context, "score")))))
                .then(
                        Commands.literal("ambercaves")
                        .then(Commands.argument("score", IntegerArgumentType.integer(1, 1000000))
                        .executes(context -> executeAmberCaves(context, IntegerArgumentType.getInteger(context, "score")))))));
    }

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context, int score) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            data.removeVillagecraftCityWarScore(server.getLevel(ServerLevel.OVERWORLD), score);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
            }

            MutableComponent message = Component.literal("Removed " + score + " to ");
            message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
            message.append(Component.literal(" score."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeGripperCity(CommandContext<CommandSourceStack> context, int score) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            data.removeGripperCityWarScore(server.getLevel(ServerLevel.OVERWORLD), score);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
            }

            MutableComponent message = Component.literal("Removed " + score + " to ");
            message.append(Component.literal("Gripper City's").withStyle(groupStyling));
            message.append(Component.literal(" score."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executeAmberCaves(CommandContext<CommandSourceStack> context, int score) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            data.removeAmberCavesWarScore(server.getLevel(ServerLevel.OVERWORLD), score);
            ChatFormatting groupStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
            }

            MutableComponent message = Component.literal("Removed " + score + " to ");
            message.append(Component.literal("The Amber Caves's").withStyle(groupStyling));
            message.append(Component.literal(" score."));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }
}