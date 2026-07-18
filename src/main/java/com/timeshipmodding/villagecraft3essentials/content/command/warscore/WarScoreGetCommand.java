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
import net.neoforged.fml.ModList;

public class WarScoreGetCommand {
    public WarScoreGetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("get")
                .then(
                        Commands.literal("score").executes(this::executeScore))
                .then(
                        Commands.literal("points").executes(this::executePoints))));
    }

    private int executeScore(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting villageCraftCityStyling = ChatFormatting.WHITE;
            ChatFormatting gripperCityStyling = ChatFormatting.WHITE;
            ChatFormatting amberCavesStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                villageCraftCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
                gripperCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
                amberCavesStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
            }

            MutableComponent message = Component.literal("VillageCraft City").withStyle(villageCraftCityStyling);
            message.append(Component.literal(" : " + data.getVillagecraftCityWarScore()));
            message.append(Component.literal("\nGripper City").withStyle(gripperCityStyling));
            message.append(Component.literal(" : " + data.getGripperCityWarScore()));
            message.append(Component.literal("\nThe Amber Caves").withStyle(amberCavesStyling));
            message.append(Component.literal(" : " + data.getAmberCavesWarScore()));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }

    private int executePoints(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get()) {
            MinecraftServer server = context.getSource().getServer();
            WarPointsSavedData data = WarPointsSavedData.getData(server);
            ChatFormatting villageCraftCityStyling = ChatFormatting.WHITE;
            ChatFormatting gripperCityStyling = ChatFormatting.WHITE;
            ChatFormatting amberCavesStyling = ChatFormatting.WHITE;

            if (ModList.get().isLoaded("luckperms")) {
                villageCraftCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
                gripperCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
                amberCavesStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
            }

            MutableComponent message = Component.literal("VillageCraft City").withStyle(villageCraftCityStyling);
            message.append(Component.literal(" : " + data.getVillagecraftCityWarPoints()));
            message.append(Component.literal("\nGripper City").withStyle(gripperCityStyling));
            message.append(Component.literal(" : " + data.getGripperCityWarPoints()));
            message.append(Component.literal("\nThe Amber Caves").withStyle(amberCavesStyling));
            message.append(Component.literal(" : " + data.getAmberCavesWarPoints()));
            context.getSource().sendSuccess(() -> message, false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled. To use them, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return -1;
    }
}