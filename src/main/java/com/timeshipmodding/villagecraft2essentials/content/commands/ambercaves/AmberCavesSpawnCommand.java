package com.timeshipmodding.villagecraft2essentials.content.commands.ambercaves;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft2essentials.util.saveddata.SpawnSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class AmberCavesSpawnCommand {
    public AmberCavesSpawnCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ambercaves").then(Commands.literal("spawn").executes(this::execute)));
    }

    private ResourceKey<Level> townDimension = ServerLevel.OVERWORLD;

    public ResourceKey<Level> getTownDimension() {
        return this.townDimension;
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        MinecraftServer server = context.getSource().getServer();
        ServerLevel serverlevel = server.getLevel(this.getTownDimension());
        SpawnSavedData savedData = SpawnSavedData.getData(server);
        int[] spawn = savedData.getAmberCavesSpawn();

        if(spawn[3] != 0 && spawn[4] != 0) {
            player.teleportTo(serverlevel, spawn[0], spawn[1], spawn[2], spawn[3], spawn[4]);
            context.getSource().sendSuccess(() -> Component.literal("You have been teleported to The Amber Caves!"), false);
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("No Amber Caves Spawn ition has been set."));
            return -1;
        }
    }
}