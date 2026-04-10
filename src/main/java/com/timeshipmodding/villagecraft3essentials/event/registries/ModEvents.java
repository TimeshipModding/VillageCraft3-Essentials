package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.aquaculture.AquaMethods;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks;
import com.timeshipmodding.villagecraft3essentials.content.command.SpawnCommand;
import com.timeshipmodding.villagecraft3essentials.content.command.ambercaves.*;
import com.timeshipmodding.villagecraft3essentials.content.command.grippercity.*;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.*;
import com.timeshipmodding.villagecraft3essentials.content.command.villagecraftcity.*;
import com.timeshipmodding.villagecraft3essentials.content.command.warscore.*;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.CoreRespawnData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.WarPointsSavedData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.itemhandler.HorseCurrencyArmorItemHandler;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.AtmRandomConversionRatesPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

import java.util.*;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModEvents {
    public static final Map<UUID, TeleportCommandsData> pendingTeleports = new HashMap<>();
    public static final Map<UUID, TpaCommandData> pendingTPAs = new HashMap<>();
    public record TeleportCommandsData(ServerLevel serverLevel, int ticksLeft, Vec3 startPos, int[] destination, Component teleportMessage) {}
    public record TpaCommandData(ServerLevel serverLevel, int ticksLeft, Vec3 startPos, ServerPlayer targetPlayer, ServerPlayer requestingPlayer, Component targetPlayerMessage, Component requestingPlayerMessage, String teleportType) {}
    private static final List<CoreRespawnData> PENDING_CORE_RESPAWNS = new ArrayList<>();
    public static final EntityCapability<HorseCurrencyArmorItemHandler, Void> HORSE_ARMOR_CAPABILITY = EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath(VillageCraft3Essentials.MODID, "horse_armor_handler"), HorseCurrencyArmorItemHandler.class);

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AmberCavesBlacklistCommand(event.getDispatcher());
        new AmberCavesJailCommand(event.getDispatcher());
        new AmberCavesKickCommand(event.getDispatcher());
        new AmberCavesPardonCommand(event.getDispatcher());
        new AmberCavesSetJailCommand(event.getDispatcher());
        new AmberCavesSetSpawnCommand(event.getDispatcher());
        new AmberCavesSpawnCommand(event.getDispatcher());
        new AmberCavesWhitelistCommand(event.getDispatcher());
        new GripperCityBlacklistCommand(event.getDispatcher());
        new GripperCityJailCommand(event.getDispatcher());
        new GripperCityKickCommand(event.getDispatcher());
        new GripperCityPardonCommand(event.getDispatcher());
        new GripperCitySetJailCommand(event.getDispatcher());
        new GripperCitySetSpawnCommand(event.getDispatcher());
        new GripperCitySpawnCommand(event.getDispatcher());
        new GripperCityWhitelistCommand(event.getDispatcher());
        new TpacceptCommand(event.getDispatcher());
        new TpaCommand(event.getDispatcher());
        new TpadenyCommand(event.getDispatcher());
        new TpahereCommand(event.getDispatcher());
        new VillageCraftCityBlacklistCommand(event.getDispatcher());
        new VillageCraftCityJailCommand(event.getDispatcher());
        new VillageCraftCityKickCommand(event.getDispatcher());
        new VillageCraftCityPardonCommand(event.getDispatcher());
        new VillageCraftCitySetJailCommand(event.getDispatcher());
        new VillageCraftCitySetSpawnCommand(event.getDispatcher());
        new VillageCraftCitySpawnCommand(event.getDispatcher());
        new VillageCraftCityWhitelistCommand(event.getDispatcher());
        new ToggleWarScoreGainingCommand(event.getDispatcher());
        new WarScoreAddCommand(event.getDispatcher());
        new WarScoreGetCommand(event.getDispatcher());
        new WarScoreRemoveCommand(event.getDispatcher());
        new WarScoreResetCommand(event.getDispatcher());
        new SpawnCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerJoined(OnDatapackSyncEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            MinecraftServer server = player.getServer();

            if (server != null) {
                int[] diamondToRuby = AtmRandomConversionRatesSavedData.getData(server).getDiamondToRuby();
                int[] diamondToAmber = AtmRandomConversionRatesSavedData.getData(server).getDiamondToAmber();
                int[] rubyToDiamond = AtmRandomConversionRatesSavedData.getData(server).getRubyToDiamond();
                int[] rubyToAmber = AtmRandomConversionRatesSavedData.getData(server).getRubyToAmber();
                int[] amberToDiamond = AtmRandomConversionRatesSavedData.getData(server).getAmberToDiamond();
                int[] amberToRuby = AtmRandomConversionRatesSavedData.getData(server).getAmberToRuby();

                if (diamondToRuby != null && diamondToRuby.length > 0) {
                    PacketDistributor.sendToPlayer(player, new AtmRandomConversionRatesPacket(diamondToRuby, diamondToAmber, rubyToDiamond, rubyToAmber, amberToDiamond, amberToRuby));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        UUID uuid = player.getUUID();

        if (pendingTeleports.containsKey(uuid)) {
            TeleportCommandsData data = pendingTeleports.get(uuid);

            if (distanceToPlayerXZCoordinates(player, data.startPos.x, data.startPos.z) > 0.25 && data.ticksLeft <= 50) {
                player.sendSystemMessage(Component.literal("Teleport cancelled: you moved.").withStyle(ChatFormatting.RED));
                pendingTeleports.remove(uuid);
                return;
            }

            if (data.ticksLeft <= 0) {
                int[] destination = data.destination;
                player.teleportTo(data.serverLevel, destination[0] + 0.5, destination[1], destination[2] + 0.5, destination[3], destination[4]);
                player.sendSystemMessage(data.teleportMessage);
                pendingTeleports.remove(uuid);

            } else {
                if (data.ticksLeft % 20 == 0) {
                    player.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                }

                pendingTeleports.put(uuid, new TeleportCommandsData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.destination, data.teleportMessage));
            }
        }

        if (pendingTPAs.containsKey(uuid)) {
            TpaCommandData data = pendingTPAs.get(uuid);

            if (Objects.equals(data.teleportType, "tpa")) {
                if (distanceToPlayerXZCoordinates(player, data.startPos.x, data.startPos.z) > 0.25 && data.ticksLeft <= 30) {
                    player.sendSystemMessage(Component.literal("TPA cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent targetPlayerMessage = Component.literal("TPA cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        targetPlayerMessage.append(player.getDisplayName().copy());

                    } else {
                        targetPlayerMessage.append(player.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    targetPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    data.targetPlayer.sendSystemMessage(targetPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (data.ticksLeft <= 0) {
                    player.teleportTo(data.serverLevel, data.targetPlayer.getX(), data.targetPlayer.getY(), data.targetPlayer.getZ(), player.getYRot(), player.getXRot());
                    player.sendSystemMessage(data.requestingPlayerMessage);
                    data.targetPlayer.sendSystemMessage(data.targetPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (data.ticksLeft % 20 == 0) {
                        player.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent targetPlayerMessage = player.getDisplayName().copy();
                        targetPlayerMessage.append(Component.literal(" is teleporting to you in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        data.targetPlayer.displayClientMessage(targetPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.targetPlayer(), data.requestingPlayer(), data.targetPlayerMessage(), data.requestingPlayerMessage(), data.teleportType()));
                }

            } else if (Objects.equals(data.teleportType, "tpahere")) {
                if (distanceToPlayerXZCoordinates(player, data.startPos.x, data.startPos.z) > 0.25 && data.ticksLeft <= 30) {
                    data.targetPlayer.sendSystemMessage(Component.literal("TPA here cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent requestingPlayerMessage = Component.literal("TPA here cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        requestingPlayerMessage.append(data.targetPlayer.getDisplayName().copy());

                    } else {
                        requestingPlayerMessage.append(data.targetPlayer.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    requestingPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(requestingPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (data.ticksLeft <= 0) {
                    data.targetPlayer.teleportTo(data.serverLevel, data.requestingPlayer.getX(), data.requestingPlayer.getY(), data.requestingPlayer.getZ(), data.targetPlayer.getYRot(), data.targetPlayer.getXRot());
                    data.targetPlayer.sendSystemMessage(data.targetPlayerMessage);
                    player.sendSystemMessage(data.requestingPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (data.ticksLeft % 20 == 0) {
                        data.targetPlayer.displayClientMessage(Component.literal("Teleporting in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent requestingPlayerMessage = data.targetPlayer.getDisplayName().copy();
                        requestingPlayerMessage.append(Component.literal(" is teleporting to you in " + (data.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        player.displayClientMessage(requestingPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(data.serverLevel, data.ticksLeft - 1, data.startPos, data.targetPlayer, data.requestingPlayer, data.targetPlayerMessage, data.requestingPlayerMessage, data.teleportType));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get() && !event.getLevel().isClientSide() && ModList.get().isLoaded("luckperms") && ToggleWarScoreGainingCommand.gainWarPoints) {
            WarPointsSavedData savedData = WarPointsSavedData.getData(event.getLevel().getServer());

            if (event.getState().getBlock() == ModBlocks.DIAMOND_CORE.get()) {
                if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                    savedData.addGripperCityWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by VillageCraft City.").withStyle(ChatFormatting.RED));
                        }
                    }

                } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                    savedData.addAmberCavesWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by The Amber Caves.").withStyle(ChatFormatting.RED));
                        }
                    }
                }

            } else if (event.getState().getBlock() == ModBlocks.RUBY_CORE.get()) {
                if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                    savedData.addVillagecraftCityWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_BLACKLISTED_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by Gripper City .").withStyle(ChatFormatting.RED));
                        }
                    }

                } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                    savedData.addAmberCavesWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by The Amber Caves.").withStyle(ChatFormatting.RED));
                        }
                    }
                }

            } else if (event.getState().getBlock() == ModBlocks.AMBER_CORE.get()) {
                if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                    savedData.addVillagecraftCityWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by VillageCraft City.").withStyle(ChatFormatting.RED));
                        }
                    }

                } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                    savedData.addGripperCityWarScore((ServerLevel) event.getLevel(), 1);

                    for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                        if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                            player.sendSystemMessage(Component.literal("Core at [" + event.getPos().toShortString() + "] is destroyed by Gripper City.").withStyle(ChatFormatting.RED));
                        }
                    }
                }
            }

            long respawnTime = event.getLevel().getLevelData().getGameTime() + randomCoreRegenerationTime();
            PENDING_CORE_RESPAWNS.add(new CoreRespawnData(event.getPos(), event.getState(), respawnTime));
        }
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get() && event.getLevel() instanceof ServerLevel serverLevel && ToggleWarScoreGainingCommand.gainWarPoints) {
            long currentTime = serverLevel.getGameTime();
            Iterator<CoreRespawnData> iterator = PENDING_CORE_RESPAWNS.iterator();

            while (iterator.hasNext()) {
                CoreRespawnData task = iterator.next();
                if (currentTime >= task.respawnTime()) {
                    serverLevel.setBlockAndUpdate(task.pos(), task.state());

                    if (task.state().getBlock() == ModBlocks.DIAMOND_CORE.get()) {
                        for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                            if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME.get())) {
                                player.sendSystemMessage(Component.literal("Core at " + task.pos() + " has respawned!").withStyle(ChatFormatting.GREEN));
                            }
                        }

                    } else if (task.state().getBlock() == ModBlocks.RUBY_CORE.get()) {
                        for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                            if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                                player.sendSystemMessage(Component.literal("Core at " + task.pos() + " has respawned!").withStyle(ChatFormatting.GREEN));
                            }
                        }

                    } else if (task.state().getBlock() == ModBlocks.AMBER_CORE.get()) {
                        for (ServerPlayer player : ((ServerLevel) event.getLevel()).getServer().getPlayerList().getPlayers()) {
                            if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                                player.sendSystemMessage(Component.literal("Core at " + task.pos() + " has respawned!").withStyle(ChatFormatting.GREEN));
                            }
                        }
                    }

                    iterator.remove();
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                HORSE_ARMOR_CAPABILITY,
                EntityType.HORSE,
                (entity, context) -> new HorseCurrencyArmorItemHandler()
        );

        if (ModList.get().isLoaded("aquaculture")) {
            AquaMethods.registerAquaFishingRodCapability(event, ModItems.RUBY_FISHING_ROD.get());
            AquaMethods.registerAquaFishingRodCapability(event, ModItems.AMBER_FISHING_ROD.get());
        }
    }

    public static double distanceToPlayerXZCoordinates(ServerPlayer player, double x, double z) {
        double d0 = x - player.getX();
        double d2 = z - player.getZ();
        return d0 * d0 + d2 * d2;
    }

    public static int randomCoreRegenerationTime() {
        Random random = new Random();
        double rawRandom = ServerConfig.CORE_RESPAWN_TIME_MIN.getAsInt() + (ServerConfig.CORE_RESPAWN_TIME_MAX.getAsInt() - ServerConfig.CORE_RESPAWN_TIME_MIN.getAsInt()) * random.nextDouble();
        double roundedNumber = Math.round(rawRandom * 100.0) / 100.0;
        return (int) (roundedNumber * 72000);
    }
}