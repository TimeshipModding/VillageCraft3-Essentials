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
                data.addVillagecraftCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
                data.addGripperCityCompletedMissions(1);

            } else if (LuckpermsMethods.isInGroup(event.getPlayer(), ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
                data.addAmberCavesCompletedMissions(1);
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

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.GRIPPERCITY_GROUP_NAME.get())) {
            return ModItems.RUBY.get();

        } else if (LuckpermsMethods.isInGroup(player, ServerConfig.AMBERCAVES_GROUP_NAME.get())) {
            return ModItems.AMBER.get();
        }

        return Items.EMERALD;
    }
}