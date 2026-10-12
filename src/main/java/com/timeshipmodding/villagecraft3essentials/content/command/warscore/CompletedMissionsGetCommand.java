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

public class CompletedMissionsGetCommand {
    public CompletedMissionsGetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warscore").requires(context -> context.hasPermission(2)).then(Commands.literal("completedmissions")
                .then(
                        Commands.literal("get").executes(this::execute))));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        MinecraftServer server = context.getSource().getServer();
        MissionSavedData data = MissionSavedData.getData(server);
        ChatFormatting villageCraftCityStyling = ChatFormatting.WHITE;
        ChatFormatting oasisStyling = ChatFormatting.WHITE;
        ChatFormatting swedishFurnitureStoreStyling = ChatFormatting.WHITE;
        ChatFormatting londonStyling = ChatFormatting.WHITE;
        ChatFormatting blackWaterStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            villageCraftCityStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
            oasisStyling = LuckpermsMethods.getGroupStyling(ServerConfig.OASIS_GROUP_NAME.get());
            swedishFurnitureStoreStyling = LuckpermsMethods.getGroupStyling(ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
            londonStyling = LuckpermsMethods.getGroupStyling(ServerConfig.LONDON_GROUP_NAME.get());
            blackWaterStyling = LuckpermsMethods.getGroupStyling(ServerConfig.BLACKWATER_GROUP_NAME.get());
        }

        MutableComponent message = Component.literal("VillageCraft City").withStyle(villageCraftCityStyling);
        message.append(Component.literal(" : " + data.getVillagecraftCityCompletedMissions()));
        message.append(Component.literal("\nOasis").withStyle(oasisStyling));
        message.append(Component.literal(" : " + data.getOasisCompletedMissions()));
        message.append(Component.literal("\nSwedish Furniture Store").withStyle(swedishFurnitureStoreStyling));
        message.append(Component.literal(" : " + data.getSwedishFurnitureStoreCompletedMissions()));
        message.append(Component.literal("\nLondon").withStyle(londonStyling));
        message.append(Component.literal(" : " + data.getLondonCompletedMissions()));
        message.append(Component.literal("\nBlackWater").withStyle(blackWaterStyling));
        message.append(Component.literal(" : " + data.getBlackWaterCompletedMissions()));
        context.getSource().sendSuccess(() -> message, false);
        return 1;
    }
}