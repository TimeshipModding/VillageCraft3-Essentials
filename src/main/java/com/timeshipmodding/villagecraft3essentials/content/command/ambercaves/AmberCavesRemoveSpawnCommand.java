package com.timeshipmodding.villagecraft3essentials.content.command.ambercaves;

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

public class AmberCavesRemoveSpawnCommand {
    public AmberCavesRemoveSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("removespawn")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        assert player != null;
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getAmberCavesSpawn();
        ChatFormatting groupStyling = ChatFormatting.WHITE;

        if (ModList.get().isLoaded("luckperms")) {
            groupStyling = LuckpermsMethods.getGroupStyling(ServerConfig.AMBERCAVES_GROUP_NAME.get());
        }

        if (spawn[3] != 0 && spawn[4] != 0) {
            int[] spawn1 = {0, 0, 0, 0, 0};
            savedData.setAmberCavesSpawn(spawn1);

            if (ModList.get().isLoaded("bluemap") && ServerConfig.ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION.get()) {
                BlueMapMethods.removeSpawnMarker("amber-caves", player);
            }

            MutableComponent message = Component.literal("Removed ");
            message.append(Component.literal("The Amber Caves'").withStyle(groupStyling));
            message.append(Component.literal(" spawn."));
            context.getSource().sendSuccess(() -> message, true);
            return 1;
        }

        MutableComponent message = Component.literal("The Amber Caves'").withStyle(groupStyling);
        message.append(Component.literal(" spawn has not been set.").withStyle(ChatFormatting.RED));
        context.getSource().sendFailure(message);
        return 0;
    }
}