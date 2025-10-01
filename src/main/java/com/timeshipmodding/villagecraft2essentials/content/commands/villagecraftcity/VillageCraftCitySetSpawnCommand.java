package com.timeshipmodding.villagecraft2essentials.content.commands.villagecraftcity;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft2essentials.util.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class VillageCraftCitySetSpawnCommand {
    public VillageCraftCitySetSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("villagecraftcity").then(Commands.literal("setspawn")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        ServerLevel serverLevel = context.getSource().getLevel();
        MinecraftServer server = context.getSource().getServer();
        BlockPos playerPos = player.blockPosition();
        String positionString = playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ();
        int playerYaw = (int) player.getYRot();
        int playerPitch = (int) player.getXRot();
        int[] spawn = {playerPos.getX(), playerPos.getY(), playerPos.getZ(), playerYaw, playerPitch};
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        savedData.setVillagecraftCitySpawn(spawn);

        if (serverLevel.dimension() != Level.OVERWORLD) {
            context.getSource().sendFailure(Component.literal("Can only set Villagecraft City's spawn in the overworld."));
            return 0;
        } else {
            context.getSource().sendSuccess(() -> Component.literal("Set VillageCraft City's Spawn to " + positionString), true);
            return 1;
        }
    }
}
