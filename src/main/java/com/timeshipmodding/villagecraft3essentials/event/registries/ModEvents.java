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
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModCompatItems;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.CoreRespawnData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.JailAndPardonCommandData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.attachment.registries.ModDataAttachments;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.SpawnSavedData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.WarPointsSavedData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.itemhandler.HorseCurrencyArmorItemHandler;
import com.timeshipmodding.villagecraft3essentials.infrastructure.networking.packet.atm.AtmRandomConversionRatesPacket;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GameType;
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
            TeleportCommandsData teleportCommandsData = pendingTeleports.get(uuid);

            if (distanceToPlayerXZCoordinates(player, teleportCommandsData.startPos.x, teleportCommandsData.startPos.z) > 0.25 && teleportCommandsData.ticksLeft <= 50) {
                player.sendSystemMessage(Component.literal("Teleport cancelled: you moved.").withStyle(ChatFormatting.RED));
                pendingTeleports.remove(uuid);
                return;
            }

            if (teleportCommandsData.ticksLeft <= 0) {
                int[] destination = teleportCommandsData.destination;
                player.teleportTo(teleportCommandsData.serverLevel, destination[0] + 0.5, destination[1], destination[2] + 0.5, destination[3], destination[4]);
                player.sendSystemMessage(teleportCommandsData.teleportMessage);
                pendingTeleports.remove(uuid);

            } else {
                if (teleportCommandsData.ticksLeft % 20 == 0) {
                    player.displayClientMessage(Component.literal("Teleporting in " + (teleportCommandsData.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                }

                pendingTeleports.put(uuid, new TeleportCommandsData(teleportCommandsData.serverLevel, teleportCommandsData.ticksLeft - 1, teleportCommandsData.startPos, teleportCommandsData.destination, teleportCommandsData.teleportMessage));
            }
        }

        if (pendingTPAs.containsKey(uuid)) {
            TpaCommandData teleportCommandsData = pendingTPAs.get(uuid);

            if (Objects.equals(teleportCommandsData.teleportType, "tpa")) {
                if (distanceToPlayerXZCoordinates(player, teleportCommandsData.startPos.x, teleportCommandsData.startPos.z) > 0.25 && teleportCommandsData.ticksLeft <= 30) {
                    player.sendSystemMessage(Component.literal("TPA cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent targetPlayerMessage = Component.literal("TPA cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        targetPlayerMessage.append(player.getDisplayName().copy());

                    } else {
                        targetPlayerMessage.append(player.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    targetPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    teleportCommandsData.targetPlayer.sendSystemMessage(targetPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (teleportCommandsData.ticksLeft <= 0) {
                    player.teleportTo(teleportCommandsData.serverLevel, teleportCommandsData.targetPlayer.getX(), teleportCommandsData.targetPlayer.getY(), teleportCommandsData.targetPlayer.getZ(), player.getYRot(), player.getXRot());
                    player.sendSystemMessage(teleportCommandsData.requestingPlayerMessage);
                    teleportCommandsData.targetPlayer.sendSystemMessage(teleportCommandsData.targetPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (teleportCommandsData.ticksLeft % 20 == 0) {
                        player.displayClientMessage(Component.literal("Teleporting in " + (teleportCommandsData.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent targetPlayerMessage = player.getDisplayName().copy();
                        targetPlayerMessage.append(Component.literal(" is teleporting to you in " + (teleportCommandsData.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        teleportCommandsData.targetPlayer.displayClientMessage(targetPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(teleportCommandsData.serverLevel, teleportCommandsData.ticksLeft - 1, teleportCommandsData.startPos, teleportCommandsData.targetPlayer(), teleportCommandsData.requestingPlayer(), teleportCommandsData.targetPlayerMessage(), teleportCommandsData.requestingPlayerMessage(), teleportCommandsData.teleportType()));
                }

            } else if (Objects.equals(teleportCommandsData.teleportType, "tpahere")) {
                if (distanceToPlayerXZCoordinates(player, teleportCommandsData.startPos.x, teleportCommandsData.startPos.z) > 0.25 && teleportCommandsData.ticksLeft <= 30) {
                    teleportCommandsData.targetPlayer.sendSystemMessage(Component.literal("TPA here cancelled: you moved.").withStyle(ChatFormatting.RED));
                    MutableComponent requestingPlayerMessage = Component.literal("TPA here cancelled: ").withStyle(ChatFormatting.RED);

                    if (ModList.get().isLoaded("luckperms")) {
                        requestingPlayerMessage.append(teleportCommandsData.targetPlayer.getDisplayName().copy());

                    } else {
                        requestingPlayerMessage.append(teleportCommandsData.targetPlayer.getDisplayName().copy().withStyle(ChatFormatting.WHITE));
                    }

                    requestingPlayerMessage.append(Component.literal(" moved.").withStyle(ChatFormatting.RED));
                    player.sendSystemMessage(requestingPlayerMessage);
                    pendingTPAs.remove(uuid);
                    return;
                }

                if (teleportCommandsData.ticksLeft <= 0) {
                    teleportCommandsData.targetPlayer.teleportTo(teleportCommandsData.serverLevel, teleportCommandsData.requestingPlayer.getX(), teleportCommandsData.requestingPlayer.getY(), teleportCommandsData.requestingPlayer.getZ(), teleportCommandsData.targetPlayer.getYRot(), teleportCommandsData.targetPlayer.getXRot());
                    teleportCommandsData.targetPlayer.sendSystemMessage(teleportCommandsData.targetPlayerMessage);
                    player.sendSystemMessage(teleportCommandsData.requestingPlayerMessage);
                    pendingTPAs.remove(uuid);

                } else {
                    if (teleportCommandsData.ticksLeft % 20 == 0) {
                        teleportCommandsData.targetPlayer.displayClientMessage(Component.literal("Teleporting in " + (teleportCommandsData.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD), true);
                        MutableComponent requestingPlayerMessage = teleportCommandsData.targetPlayer.getDisplayName().copy();
                        requestingPlayerMessage.append(Component.literal(" is teleporting to you in " + (teleportCommandsData.ticksLeft / 20) + "...").withStyle(ChatFormatting.GOLD));
                        player.displayClientMessage(requestingPlayerMessage, true);
                    }

                    pendingTPAs.put(uuid, new TpaCommandData(teleportCommandsData.serverLevel, teleportCommandsData.ticksLeft - 1, teleportCommandsData.startPos, teleportCommandsData.targetPlayer, teleportCommandsData.requestingPlayer, teleportCommandsData.targetPlayerMessage, teleportCommandsData.requestingPlayerMessage, teleportCommandsData.teleportType));
                }
            }
        }

        if (player.hasData(ModDataAttachments.JAIL_COMMAND_DATA)) {
            JailAndPardonCommandData jailCommandData = player.getData(ModDataAttachments.JAIL_COMMAND_DATA);

            if (jailCommandData.jailReleaseTime() > 0) {
                player.setData(ModDataAttachments.JAIL_COMMAND_DATA, jailCommandData.tick());

                if (jailCommandData.jailReleaseTime() - 1 == 0) {
                    MinecraftServer server = event.getEntity().getServer();
                    ServerLevel serverlevel = server.getLevel(ServerLevel.OVERWORLD);
                    SpawnSavedData savedData = SpawnSavedData.getData(server);
                    int[] spawn = null;
                    MutableComponent targetPlayerMessage;
                    BlockPos blockpos = serverlevel.getSharedSpawnPos();
                    player.setGameMode(GameType.SURVIVAL);
                    player.setRespawnPosition(ServerLevel.OVERWORLD, blockpos, player.getYRot(), true, false);

                    if (jailCommandData.townComponent().contains(Component.literal("VillageCraft City's"))) {
                        spawn = savedData.getVillagecraftCitySpawn();

                    } else if (jailCommandData.townComponent().contains(Component.literal("Gripper City's"))) {
                        spawn = savedData.getGripperCitySpawn();

                    } else if (jailCommandData.townComponent().contains(Component.literal("Amber Caves'"))) {
                        spawn = savedData.getAmberCavesSpawn();
                    }

                    if (ModList.get().isLoaded("luckperms")) {
                        LuckpermsMethods.removeGroup(player, ServerConfig.JAILED_GROUP_NAME.get());
                    }

                    if (spawn[3] != 0 && spawn[4] != 0) {
                        player.teleportTo(serverlevel, spawn[0] + 0.5, spawn[1], spawn[2] + 0.5, spawn[3], spawn[4]);
                        targetPlayerMessage = Component.literal("You have been pardoned from jail and teleported to ");
                        targetPlayerMessage.append(jailCommandData.townComponent());
                        targetPlayerMessage.append(Component.literal(" spawn!"));
                        player.sendSystemMessage(targetPlayerMessage, false);

                        ServerPlayer jailerPlayer = event.getEntity().getServer().getPlayerList().getPlayerByName(jailCommandData.jailerUsername());
                        MutableComponent jailerPlayerMessage = Component.literal(player.getDisplayName().getString() + " has reached the maximum jail time. They have been automatically pardoned and teleported to ");
                        jailerPlayerMessage.append(jailCommandData.townComponent());
                        jailerPlayerMessage.append(Component.literal(" spawn!"));
                        jailerPlayer.sendSystemMessage(jailerPlayerMessage, false);

                    } else {
                        int playerYaw = (int) player.getYRot();
                        int playerPitch = (int) player.getXRot();
                        player.teleportTo(serverlevel, blockpos.getX(), blockpos.getY(), blockpos.getZ(), playerYaw, playerPitch);
                        targetPlayerMessage = Component.literal("You have been pardoned from jail and teleported to ");
                        targetPlayerMessage.append(Component.literal("World Spawn").withStyle(ChatFormatting.GREEN));
                        targetPlayerMessage.append(Component.literal("!"));
                        player.sendSystemMessage(targetPlayerMessage, false);

                        ServerPlayer jailerPlayer = event.getEntity().getServer().getPlayerList().getPlayerByName(jailCommandData.jailerUsername());
                        MutableComponent jailerPlayerMessage = Component.literal(player.getDisplayName().getString() + " has reached the maximum jail time. They have been automatically pardoned and teleported to ");
                        jailerPlayerMessage.append(Component.literal("World spawn").withStyle(ChatFormatting.GREEN));
                        jailerPlayerMessage.append(Component.literal(" due to "));
                        jailerPlayerMessage.append(jailCommandData.townComponent());
                        jailerPlayerMessage.append(Component.literal(" unset spawn position."));
                        jailerPlayer.sendSystemMessage(jailerPlayerMessage, false);
                    }

                    player.setData(ModDataAttachments.JAIL_COMMAND_DATA, jailCommandData.reset());
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
            AquaMethods.registerAquaFishingRodCapability(event, ModCompatItems.RUBY_FISHING_ROD.get());
            AquaMethods.registerAquaFishingRodCapability(event, ModCompatItems.AMBER_FISHING_ROD.get());
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