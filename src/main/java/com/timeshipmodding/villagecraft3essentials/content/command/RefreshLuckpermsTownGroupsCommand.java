package com.timeshipmodding.villagecraft3essentials.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.LuckpermsTownGroupPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;

public class RefreshLuckpermsTownGroupsCommand {
    public RefreshLuckpermsTownGroupsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("refreshluckpermstowngroups").requires(context -> context.hasPermission(2)).executes(this::execute));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get() && ServerConfig.ENABLE_NAME_TAG_HIDING.get() && ModList.get().isLoaded("luckperms")) {
            for (ServerPlayer player : context.getSource().getServer().getPlayerList().getPlayers()) {
                boolean inVillageCraftCity = LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get());
                boolean inOasis = LuckpermsMethods.isInGroup(player, ServerConfig.OASIS_GROUP_NAME.get());
                boolean inSwedishFurnitureStore = LuckpermsMethods.isInGroup(player, ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get());
                boolean inLondon = LuckpermsMethods.isInGroup(player, ServerConfig.LONDON_GROUP_NAME.get());
                boolean inBlackWater = LuckpermsMethods.isInGroup(player, ServerConfig.BLACKWATER_GROUP_NAME.get());
                PacketDistributor.sendToAllPlayers(new LuckpermsTownGroupPacket(player.getUUID(), inVillageCraftCity, inOasis, inSwedishFurnitureStore, inLondon, inBlackWater));
            }
            context.getSource().sendSuccess(() -> Component.literal("Luckperms town group refreshed. Name tag display should now be accurate to town groups."), false);
            return 1;
        }

        MutableComponent message = Component.literal("War features are not enabled, name tag hiding is not enabled or luckperms is not installed. To enable war features and name tag hiding, enable them in the VillageCraft 3 Essentials Server Config.").withStyle(ChatFormatting.RED);
        context.getSource().sendFailure(message);
        return 0;
    }
}