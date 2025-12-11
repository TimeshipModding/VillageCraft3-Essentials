package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.commands.WorldSpawnCommand;
import com.timeshipmodding.villagecraft3essentials.content.commands.ambercaves.*;
import com.timeshipmodding.villagecraft3essentials.content.commands.grippercity.*;
import com.timeshipmodding.villagecraft3essentials.content.commands.villagecraftcity.*;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.networking.packet.atm.AtmRandomConversionRatesPacket;
import com.timeshipmodding.villagecraft3essentials.util.Config;
import com.timeshipmodding.villagecraft3essentials.util.data.saveddata.AtmRandomConversionRatesSavedData;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
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

        if (event.getType() == VillagerProfession.TOOLSMITH) {
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

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
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

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        AtmRandomConversionRatesSavedData data = AtmRandomConversionRatesSavedData.getData(event.getServer());
        int diamond_ruby = randomRubyCurrencyConversion();
        int diamond_amber = randomAmberCurrencyConversion();
        int ruby = randomRubyCurrencyConversion();
        int amber = randomAmberCurrencyConversion();
        int[] diamondToRuby = new int[]{diamond_ruby, ruby};
        int[] diamondToAmber = new int[]{diamond_amber, amber};
        int[] rubyToDiamond = new int[]{ruby, diamond_ruby};
        int[] scaledArray1 = new int[]{(diamond_ruby * diamond_amber), (ruby * diamond_amber)};
        int[] scaledArray2 = new int[]{(diamond_amber * diamond_ruby), (amber * diamond_ruby)};
        int[] rubyToAmber = new int[]{scaledArray1[1], scaledArray2[1]};
        int[] amberToDiamond = new int[]{amber, diamond_amber};
        int[] amberToRuby = new int[]{scaledArray2[1], scaledArray1[1]};
        data.setDiamondToRuby(diamondToRuby);
        data.setDiamondToAmber(diamondToAmber);
        data.setRubyToDiamond(rubyToDiamond);
        data.setRubyToAmber(rubyToAmber);
        data.setAmberToDiamond(amberToDiamond);
        data.setAmberToRuby(amberToRuby);
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToRuby{}", Arrays.toString(diamondToRuby));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToAmber{}", Arrays.toString(diamondToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToDiamond{}", Arrays.toString(rubyToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToAmber{}", Arrays.toString(rubyToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToDiamond{}", Arrays.toString(amberToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToRuby{}", Arrays.toString(amberToRuby));
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

    public static int randomRubyCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(Config.rubyCurrencyConversionRateMin, Config.rubyCurrencyConversionRateMax);
    }

    public static int randomAmberCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(Config.amberCurrencyConversionRateMin, Config.amberCurrencyConversionRateMax);
    }
}