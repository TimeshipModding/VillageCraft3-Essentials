package com.timeshipmodding.villagecraft3essentials.content.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.util.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.WritableLevelData;

public class WorldSpawnCommand {
    public WorldSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("worldspawn").executes(this::execute));
    }

    private final ResourceKey<Level> spawnDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getTownDimension() {
        return this.spawnDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getTownDimension());
        assert serverlevel != null;
        BlockPos blockpos = serverlevel.getSharedSpawnPos();
        float angle = serverlevel.getSharedSpawnAngle();

        if(player != null) {
            player.teleportTo(serverlevel, blockpos.getX(), blockpos.getY(), blockpos.getZ(), angle, 0);
            context.getSource().sendSuccess(() -> Component.literal("You have been teleported to World Spawn!"), false);
            return 1;
        } else {
            return -1;
        }
    }
}
