package com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.bluemap.BlueMapMethods;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

public class VillageCraftCityRemoveSpawnCommand {
    public VillageCraftCityRemoveSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("removespawn")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        assert player != null;
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getVillagecraftCitySpawn();
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
        }

        if (spawn[3] != 0 && spawn[4] != 0) {
            int[] spawn1 = {0, 0, 0, 0, 0};
            savedData.setVillagecraftCitySpawn(spawn1);

            if (ModList.get().isLoaded("bluemap") && ServerConfig.ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION.get()) {
                BlueMapMethods.removeSpawnMarker("villagecraft-city", player);
            }

            MutableComponent message = Component.literal("Removed ");
            message.append(Component.literal("VillageCraft City's").withStyle(groupStyling));
            message.append(Component.literal(" spawn."));
            context.getSource().sendSuccess(() -> message, true);
            return 1;
        }

        MutableComponent message = Component.literal("VillageCraft City's").withStyle(groupStyling);
        message.append(Component.literal(" spawn has not been set.").withStyle(ChatFormatting.RED));
        context.getSource().sendFailure(message);
        return 0;
    }
}