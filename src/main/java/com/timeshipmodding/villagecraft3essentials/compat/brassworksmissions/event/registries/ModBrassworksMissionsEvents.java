package com.timeshipmodding.villagecraft3essentials.compat.brassworksmissions.event.registries;

import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.MissionSavedData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.swzo.brassworksmissions.event.MissionEvent;

public class ModBrassworksMissionsEvents {
    public static void registerEvents() {
        NeoForge.EVENT_BUS.register(ModBrassworksMissionsEvents.class);
    }

    @SubscribeEvent
    public static void onMissionComplete(MissionEvent.Completed event) {
        if (ModList.get().isLoaded("luckperms") && ModList.get().isLoaded("brassworksmissions") && ServerConfig.ENABLE_BRASSWORKS_MISSIONS_OVERRIDE.get()) {
            MissionSavedData data = MissionSavedData.getData(event.getPlayer().server);

            if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
                data.addVillagecraftCityCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.OASIS_GROUP_NAME.get())) {
                data.addOasisCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get())) {
                data.addSwedishFurnitureStoreCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.LONDON_GROUP_NAME.get())) {
                data.addLondonCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.BLACKWATER_GROUP_NAME.get())) {
                data.addBlackWaterCompletedMissions(1);
            }
        }
    }

    @SubscribeEvent
    public static void onMissionRewardClaimed(MissionEvent.RewardClaimed event) {
        if (ModList.get().isLoaded("luckperms") && ModList.get().isLoaded("brassworksmissions") && ServerConfig.ENABLE_BRASSWORKS_MISSIONS_OVERRIDE.get()) {
            ItemStack rewardItemStack = new ItemStack(getMissionCurrencyGemReward(event.getPlayer()));
            int rewardCount = event.getMission().getRewardItemStack().getCount();
            rewardItemStack.setCount(rewardCount);

            if (!event.getPlayer().getInventory().add(rewardItemStack)) {
                event.getPlayer().drop(rewardItemStack, false);
            }
        }
    }

    private static Item getMissionCurrencyGemReward(ServerPlayer player) {
        if (LuckpermsMethods.isInGroup(player, ServerConfig.VILLAGECRAFTCITY_GROUP_NAME.get())) {
            return Items.DIAMOND;

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.OASIS_GROUP_NAME.get())) {
            return ModItems.JADE.get();

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.SWEDISHFURNITURESTORE_GROUP_NAME.get())) {
            return ModItems.AMBER.get();

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.LONDON_GROUP_NAME.get())) {
            return ModItems.SILICON.get();

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.BLACKWATER_GROUP_NAME.get())) {
            return ModItems.RUBY.get();
        }

        return Items.EMERALD;
    }
}