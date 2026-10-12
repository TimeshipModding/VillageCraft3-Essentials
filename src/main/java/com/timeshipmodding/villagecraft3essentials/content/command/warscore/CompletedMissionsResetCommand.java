package com.timeshipmodding.villagecraft3essentials.content.command.warscore;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.MissionSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.neoforged.fml.ModList;

public class CompletedMissionsResetCommand {
    public CompletedMissionsResetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("completedmissions").then(Commands.literal("reset")
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
                        Commands.literal("blackwater").executes(this::executeBlackWater)))));
    }

    private int executeAll(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        data.resetVillagecraftCityCompletedMissions();
        data.resetOasisCompletedMissions();
        data.resetSwedishFurnitureStoreCompletedMissions();
        data.resetLondonCompletedMissions();
        data.resetBlackWaterCompletedMissions();
        MutableComponent message = Component.literal("All towns' completed missions count have been reset to 0.");
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeVillageCraftCity(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        data.resetVillagecraftCityCompletedMissions();
        MutableComponent message = Component.literal("VillageCraft City's").withStyle(groupStyling);
        message.append(Component.literal(" completed missions count has been reset to 0."));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeOasis(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.OASIS_GROUP_NAME.get());
        }

        data.resetOasisCompletedMissions();
        MutableComponent message = Component.literal("Oasis's").withStyle(groupStyling);
        message.append(Component.literal(" completed missions count has been reset to 0."));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeSwedishFurnitureStore(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
        }

        data.resetSwedishFurnitureStoreCompletedMissions();
        MutableComponent message = Component.literal("Swedish Furniture Store's").withStyle(groupStyling);
        message.append(Component.literal(" completed missions count has been reset to 0."));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeLondon(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.LONDON_GROUP_NAME.get());
        }

        data.resetLondonCompletedMissions();
        MutableComponent message = Component.literal("London's").withStyle(groupStyling);
        message.append(Component.literal(" completed missions count has been reset to 0."));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }

    private int executeBlackWater(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.BLACKWATER_GROUP_NAME.get());
        }

        data.resetBlackWaterCompletedMissions();
        MutableComponent message = Component.literal("BlackWater's").withStyle(groupStyling);
        message.append(Component.literal(" completed missions count has been reset to 0."));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}