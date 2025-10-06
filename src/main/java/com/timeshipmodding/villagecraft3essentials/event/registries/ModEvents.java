package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.commands.WorldSpawnCommand;
import com.timeshipmodding.villagecraft3essentials.content.commands.ambercaves.*;
import com.timeshipmodding.villagecraft3essentials.content.commands.grippercity.*;
import com.timeshipmodding.villagecraft3essentials.content.commands.villagecraftcity.*;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.villager.registries.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.server.command.ConfigCommand;

import java.util.List;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.BANKER.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 1),
                    new ItemStack(ModItems.RUBY.get(), 5), 128, 10, 0.0f
            ));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.RUBY.get(), 5),
                    new ItemStack(Items.DIAMOND, 1), 128, 10, 0.0f
            ));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 2),
                    new ItemStack(ModItems.AMBER.get(), 1), 128, 15, 0.0f
            ));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.AMBER.get(), 1),
                    new ItemStack(Items.DIAMOND, 2), 128, 15, 0.0f
            ));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.AMBER.get(), 1),
                    new ItemStack(ModItems.RUBY.get(), 10), 128, 30, 0.0f
            ));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.RUBY.get(), 10),
                    new ItemStack(ModItems.AMBER.get(), 1), 128, 30, 0.0f
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Blocks.EMERALD_BLOCK, 8),
                    new ItemStack(Items.DIAMOND, 1), 128, 45, 0.0f
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Blocks.EMERALD_BLOCK, 8),
                    new ItemStack(ModItems.RUBY.get(), 5), 128, 45, 0.0f
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Blocks.EMERALD_BLOCK, 16),
                    new ItemStack(ModItems.AMBER.get(), 1), 128, 45, 0.0f
            ));
        }

        if(event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 14),
                    new ItemStack(ModItems.RUBY_LEGGINGS.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 14),
                    new ItemStack(ModItems.AMBER_LEGGINGS.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.RUBY_BOOTS.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.AMBER_BOOTS.get(), 1), 3, 15, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.RUBY_HELMET.get(), 1), 3, 30, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.AMBER_HELMET.get(), 1), 3, 30, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.RUBY_CHESTPLATE.get(), 1), 3, 30, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 16),
                    new ItemStack(ModItems.AMBER_CHESTPLATE.get(), 1), 3, 30, 0.2F
            ));
        }

        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 4),
                    new ItemStack(ModItems.RUBY_HOE.get(), 1), 3, 10, 0.2F
            ));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 4),
                    new ItemStack(ModItems.AMBER_HOE.get(), 1), 3, 10, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.RUBY.get(), 1),
                    new ItemStack(Items.EMERALD, 1), 12, 30, 0F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.AMBER.get(), 1),
                    new ItemStack(Items.EMERALD, 1), 12, 30, 0F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModItems.RUBY_AXE.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModItems.AMBER_AXE.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.RUBY_SHOVEL.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.AMBER_SHOVEL.get(), 1), 3, 15, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 13),
                    new ItemStack(ModItems.RUBY_PICKAXE.get(), 1), 3, 30, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 13),
                    new ItemStack(ModItems.AMBER_PICKAXE.get(), 1), 3, 30, 0.2F
            ));
        }

        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.RUBY.get(), 1),
                    new ItemStack(Items.EMERALD, 1), 12, 30, 0F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(ModItems.AMBER, 1),
                    new ItemStack(Items.EMERALD, 1), 12, 30, 0F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModItems.RUBY_AXE.get(), 1), 3, 15, 0.2F
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModItems.AMBER_AXE.get(), 1), 3, 15, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.RUBY_SWORD.get(), 1), 3, 30, 0.2F
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 8),
                    new ItemStack(ModItems.AMBER_SWORD.get(), 1), 3, 30, 0.2F
            ));
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new WorldSpawnCommand(event.getDispatcher());
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
        new VillageCraftCityBlacklistCommand(event.getDispatcher());
        new VillageCraftCityJailCommand(event.getDispatcher());
        new VillageCraftCityKickCommand(event.getDispatcher());
        new VillageCraftCityPardonCommand(event.getDispatcher());
        new VillageCraftCitySetJailCommand(event.getDispatcher());
        new VillageCraftCitySetSpawnCommand(event.getDispatcher());
        new VillageCraftCitySpawnCommand(event.getDispatcher());
        new VillageCraftCityWhitelistCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}