package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.luckperms.LuckpermsMethods;
import com.timeshipmodding.villagecraft3essentials.content.command.tpa.manager.TpaCommandManager;
import com.timeshipmodding.villagecraft3essentials.event.ServerMessageEvent;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.CommonConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.config.ServerConfig;
import com.timeshipmodding.villagecraft3essentials.infrastructure.data.saveddata.AtmRandomConversionRatesSavedData;
import com.timeshipmodding.villagecraft3essentials.infrastructure.util.TabListVariables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class ModServerEvents {
    private static int ticksSinceLastUpdate = 0;

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        if (ModList.get().isLoaded("luckperms")) {
            VillageCraft3Essentials.LOGGER.info("Luckperms is installed. VillageCraft 3 Essentials luckperms features Enabled.");

        } else {
            VillageCraft3Essentials.LOGGER.info("Luckperms is not installed. VillageCraft 3 Essentials luckperms features Disabled.");
        }

        AtmRandomConversionRatesSavedData data = AtmRandomConversionRatesSavedData.getData(event.getServer());
        int diamond_ruby = randomRubyCurrencyConversion();
        int diamond_amber = randomAmberCurrencyConversion();
        int ruby = randomRubyCurrencyConversion();
        int amber = randomAmberCurrencyConversion();
        int[] scaledArray1 = new int[]{(diamond_ruby * diamond_amber), (ruby * diamond_amber)};
        int[] scaledArray2 = new int[]{(diamond_amber * diamond_ruby), (amber * diamond_ruby)};
        int[] diamondToRuby = new int[]{diamond_ruby / findHighestCommonFactor(diamond_ruby, ruby), ruby / findHighestCommonFactor(diamond_ruby, ruby)};
        int[] diamondToAmber = new int[]{diamond_amber / findHighestCommonFactor(diamond_amber, amber), amber / findHighestCommonFactor(diamond_amber, amber)};
        int[] rubyToDiamond = new int[]{ruby / findHighestCommonFactor(ruby, diamond_ruby), diamond_ruby / findHighestCommonFactor(diamond_ruby, ruby)};
        int[] rubyToAmber = new int[]{scaledArray1[1] / findHighestCommonFactor(scaledArray1[1], scaledArray2[1]), scaledArray2[1] / findHighestCommonFactor(scaledArray2[1], scaledArray1[1])};
        int[] amberToDiamond = new int[]{amber / findHighestCommonFactor(diamond_amber, amber), diamond_amber / findHighestCommonFactor(diamond_amber, amber)};
        int[] amberToRuby = new int[]{scaledArray2[1] / findHighestCommonFactor(scaledArray2[1], scaledArray1[1]), scaledArray1[1] / findHighestCommonFactor(scaledArray2[1], scaledArray1[1])};
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
        Scoreboard scoreboard = event.getServer().getScoreboard();

        if (ServerConfig.ENABLE_WAR_SYSTEM_FEATURES.get() && scoreboard.getObjective("war_points_leaderboard") == null) {
            scoreboard.addObjective(
                    "war_points_leaderboard",
                    ObjectiveCriteria.DUMMY,
                    Component.literal("War Points Leaderboard").withStyle(ChatFormatting.BOLD),
                    ObjectiveCriteria.RenderType.INTEGER,
                    true,
                    null
            );
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (event.getServer().getTickCount() % 100 == 0) {
            TpaCommandManager.cleanupTimedOutChallenges(event.getServer());
        }

        if (ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            MinecraftServer server = event.getServer();
            ticksSinceLastUpdate++;

            if (ticksSinceLastUpdate < 10) {
                return;
            }

            ticksSinceLastUpdate = 0;
            server.getPlayerList().getPlayers().forEach(ModServerEvents::refreshPlayerTab);
        }
    }

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        if (ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            Component msg = event.getMessage();
            ServerPlayer player = event.getPlayer();
            MutableComponent formattedMsg = (MutableComponent) player.getDisplayName();
            formattedMsg.append(Component.literal(" >> ").withStyle(ChatFormatting.GRAY));
            formattedMsg.append(msg);
            event.setCanceled(true);

            player.server.execute(() -> {
                ServerMessageEvent.broadcastMessage(player.level(), formattedMsg);
            });
        }
    }

    private static void refreshPlayerTab(ServerPlayer player) {
        updateTabListHeaderFooter(player);

        if (ServerConfig.CHAT_TAB_NAME_FORMATTING.get() || ServerConfig.ENABLE_VILLAGECRAFT3_LOGO_TABLIST.get()) {
            player.refreshTabListName();
            player.refreshDisplayName();

            if (ModList.get().isLoaded("luckperms")) {
                LuckpermsMethods.updatePlayerTeam(player);
            }
        }
    }

    private static void updateTabListHeaderFooter(ServerPlayer player) {
        String header;

        if (ServerConfig.CHAT_TAB_NAME_FORMATTING.get()) {
            header = TabListVariables.tablistCharacters("     \uE001\uF801\uE002#N#N#N#N#N");

        } else {
            header = TabListVariables.tablistCharacters("");
        }

        String footer = TabListVariables.tablistCharacters("#N&fOnline: &e#PLAYERCOUNT #N&7| TPS: &a#TPS &7 MSPT: &a#MSPT &7 Uptime: &a#UPTIME &7|");
        ClientboundTabListPacket packet = new ClientboundTabListPacket(Component.literal(header), Component.literal(footer));
        player.connection.send(packet);
    }

    public static int findHighestCommonFactor(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public static int randomRubyCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.RUBY_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.RUBY_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }

    public static int randomAmberCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.AMBER_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.AMBER_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }
}