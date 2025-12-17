package com.timeshipmodding.villagecraft3essentials.content.command.ambercaves;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;

public class AmberCavesSpawnCommand {
    public AmberCavesSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("spawn").executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getAmberCavesSpawn();
        String groupColour;

        if (ModList.get().isLoaded("luckperms")) {
            groupColour = LuckpermsMethods.getGroupColour(ServerConfig.GRIPPERCITY_GROUP_NAME.get());
        } else {
            groupColour = "";
        }

        if (spawn[3] != 0 && spawn[4] != 0 && player != null && serverlevel != null) {
            player.teleportTo(serverlevel, spawn[0] + 0.5, spawn[1], spawn[2] + 0.5, spawn[3], spawn[4]);
            context.getSource().sendSuccess(() -> Component.literal("You have been teleported to " + groupColour + "The Amber Caves§r!"), false);
            return 1;

        } else {
            context.getSource().sendFailure(Component.literal("§cNo " + groupColour + "Amber Caves'§c Spawn position has been set."));
            return -1;
        }
    }
}