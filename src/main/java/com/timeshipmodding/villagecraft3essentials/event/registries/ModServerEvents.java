package com.timeshipmodding.villagecraft3essentials.event.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.compat.dcintegration.DiscordIntegrationMethods;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
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
        AtmRandomConversionRatesSavedData data = AtmRandomConversionRatesSavedData.getData(event.getServer());
        int diamondJade = randomJadeCurrencyConversion();
        int diamondAmber = randomAmberCurrencyConversion();
        int diamondSilicon = randomSiliconCurrencyConversion();
        int diamondRuby = randomRubyCurrencyConversion();
        int jade = randomJadeCurrencyConversion();
        int amber = randomAmberCurrencyConversion();
        int silicon = randomSiliconCurrencyConversion();
        int ruby = randomRubyCurrencyConversion();
        int scaledJadeAmber = jade * diamondAmber;
        int scaledJadeSilicon = jade * diamondSilicon;
        int scaledJadeRuby = jade * diamondRuby;
        int scaledAmberJade = amber * diamondJade;
        int scaledAmberSilicon = amber * diamondSilicon;
        int scaledAmberRuby = amber * diamondRuby;
        int scaledSiliconJade = silicon * diamondJade;
        int scaledSiliconAmber = silicon * diamondAmber;
        int scaledSiliconRuby = silicon * diamondRuby;
        int scaledRubyJade = ruby * diamondJade;
        int scaledRubyAmber = ruby * diamondAmber;
        int scaledRubySilicon = ruby * diamondSilicon;
        int[] diamondToJade = new int[]{diamondJade / findHighestCommonFactor(diamondJade, jade), jade / findHighestCommonFactor(diamondJade, jade)};
        int[] diamondToAmber = new int[]{diamondAmber / findHighestCommonFactor(diamondAmber, amber), amber / findHighestCommonFactor(diamondAmber, amber)};
        int[] diamondToSilicon = new int[]{diamondSilicon / findHighestCommonFactor(diamondSilicon, silicon), silicon / findHighestCommonFactor(diamondSilicon, silicon)};
        int[] diamondToRuby = new int[]{diamondRuby / findHighestCommonFactor(diamondRuby, ruby), ruby / findHighestCommonFactor(diamondRuby, ruby)};
        int[] jadeToDiamond = new int[]{jade / findHighestCommonFactor(diamondJade, jade), diamondJade / findHighestCommonFactor(diamondJade, jade)};
        int[] jadeToAmber = new int[]{scaledJadeAmber / findHighestCommonFactor(scaledJadeAmber, scaledAmberJade), scaledAmberJade / findHighestCommonFactor(scaledJadeAmber, scaledAmberJade)};
        int[] jadeToSilicon = new int[]{scaledJadeSilicon / findHighestCommonFactor(scaledJadeSilicon, scaledSiliconJade), scaledSiliconJade / findHighestCommonFactor(scaledJadeSilicon, scaledSiliconJade)};
        int[] jadeToRuby = new int[]{scaledJadeRuby / findHighestCommonFactor(scaledJadeRuby, scaledRubyJade), scaledRubyJade / findHighestCommonFactor(scaledJadeRuby, scaledRubyJade)};
        int[] amberToDiamond = new int[]{amber / findHighestCommonFactor(diamondAmber, amber), diamondAmber / findHighestCommonFactor(diamondAmber, amber)};
        int[] amberToJade = new int[]{scaledAmberJade / findHighestCommonFactor(scaledAmberJade, scaledJadeAmber), scaledJadeAmber / findHighestCommonFactor(scaledAmberJade, scaledJadeAmber)};
        int[] amberToSilicon = new int[]{scaledAmberSilicon / findHighestCommonFactor(scaledAmberSilicon, scaledSiliconAmber), scaledSiliconAmber / findHighestCommonFactor(scaledAmberSilicon, scaledSiliconAmber)};
        int[] amberToRuby = new int[]{scaledAmberRuby / findHighestCommonFactor(scaledAmberRuby, scaledRubyAmber), scaledRubyAmber / findHighestCommonFactor(scaledAmberRuby, scaledRubyAmber)};
        int[] siliconToDiamond = new int[]{silicon / findHighestCommonFactor(diamondSilicon, silicon), diamondSilicon / findHighestCommonFactor(diamondSilicon, silicon)};
        int[] siliconToJade = new int[]{scaledSiliconJade / findHighestCommonFactor(scaledSiliconJade, scaledJadeSilicon), scaledJadeSilicon / findHighestCommonFactor(scaledSiliconJade, scaledJadeSilicon)};
        int[] siliconToAmber = new int[]{scaledSiliconAmber / findHighestCommonFactor(scaledSiliconAmber, scaledAmberSilicon), scaledAmberSilicon / findHighestCommonFactor(scaledSiliconAmber, scaledAmberSilicon)};
        int[] siliconToRuby = new int[]{scaledSiliconRuby / findHighestCommonFactor(scaledSiliconRuby, scaledRubySilicon), scaledRubySilicon / findHighestCommonFactor(scaledSiliconRuby, scaledRubySilicon)};
        int[] rubyToDiamond = new int[]{ruby / findHighestCommonFactor(ruby, diamondRuby), diamondRuby / findHighestCommonFactor(ruby, diamondRuby)};
        int[] rubyToJade = new int[]{scaledRubyJade / findHighestCommonFactor(scaledRubyJade, scaledJadeRuby), scaledJadeRuby / findHighestCommonFactor(scaledRubyJade, scaledJadeRuby)};
        int[] rubyToAmber = new int[]{scaledRubyAmber / findHighestCommonFactor(scaledRubyAmber, scaledAmberRuby), scaledAmberRuby / findHighestCommonFactor(scaledRubyAmber, scaledAmberRuby)};
        int[] rubyToSilicon = new int[]{scaledRubySilicon / findHighestCommonFactor(scaledRubySilicon, scaledSiliconRuby), scaledSiliconRuby / findHighestCommonFactor(scaledRubySilicon, scaledSiliconRuby)};
        data.setDiamondToJade(diamondToJade);
        data.setDiamondToAmber(diamondToAmber);
        data.setDiamondToSilicon(diamondToSilicon);
        data.setDiamondToRuby(diamondToRuby);
        data.setJadeToDiamond(jadeToDiamond);
        data.setJadeToAmber(jadeToAmber);
        data.setJadeToSilicon(jadeToSilicon);
        data.setJadeToRuby(jadeToRuby);
        data.setAmberToDiamond(amberToDiamond);
        data.setAmberToJade(amberToJade);
        data.setAmberToSilicon(amberToSilicon);
        data.setAmberToRuby(amberToRuby);
        data.setSiliconToDiamond(siliconToDiamond);
        data.setSiliconToJade(siliconToJade);
        data.setSiliconToAmber(siliconToAmber);
        data.setSiliconToRuby(siliconToRuby);
        data.setRubyToDiamond(rubyToDiamond);
        data.setRubyToJade(rubyToJade);
        data.setRubyToAmber(rubyToAmber);
        data.setRubyToSilicon(rubyToSilicon);
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToJade{}", Arrays.toString(diamondToJade));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToAmber{}", Arrays.toString(diamondToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToSilicon{}", Arrays.toString(diamondToSilicon));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - DiamondToRuby{}", Arrays.toString(diamondToRuby));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - JadeToDiamond{}", Arrays.toString(jadeToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - JadeToAmber{}", Arrays.toString(jadeToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - JadeToSilicon{}", Arrays.toString(jadeToSilicon));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - JadeToRuby{}", Arrays.toString(jadeToRuby));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToDiamond{}", Arrays.toString(amberToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToJade{}", Arrays.toString(amberToJade));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToSilicon{}", Arrays.toString(amberToSilicon));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - AmberToRuby{}", Arrays.toString(amberToRuby));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - SiliconToDiamond{}", Arrays.toString(siliconToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - SiliconToJade{}", Arrays.toString(siliconToJade));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - SiliconToAmber{}", Arrays.toString(siliconToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - SiliconToRuby{}", Arrays.toString(siliconToRuby));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToDiamond{}", Arrays.toString(rubyToDiamond));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToJade{}", Arrays.toString(rubyToJade));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToAmber{}", Arrays.toString(rubyToAmber));
        VillageCraft3Essentials.LOGGER.info("Currency Conversions Randomized - RubyToSilicon{}", Arrays.toString(rubyToSilicon));
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
            TpaCommandManager.cleanupTimedOutRequests(event.getServer());
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
            ServerPlayer player = event.getPlayer();
            MutableComponent formattedMsg = (MutableComponent) player.getDisplayName();
            formattedMsg.append(Component.literal(" >> ").withStyle(ChatFormatting.GRAY));
            formattedMsg.append(event.getMessage());
            event.setCanceled(true);

            player.server.execute(() -> {
                ServerMessageEvent.broadcastMessage(player.level(), formattedMsg);
            });

            if (ModList.get().isLoaded("dcintegration")) {
                DiscordIntegrationMethods.sendMessage(formattedMsg);
            }
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

    private static final ResourceLocation LOGO_FONT = ResourceLocation.fromNamespaceAndPath("villagecraft3essentials", "default");

    private static void updateTabListHeaderFooter(ServerPlayer player) {
        MutableComponent header;

        if (ServerConfig.ENABLE_VILLAGECRAFT3_LOGO_TABLIST.get()) {
            MutableComponent logo = Component.literal("\uE001\uF801\uE002")
                    .withStyle(style -> style
                            .withColor(0x4E5C24)
                            .withFont(LOGO_FONT)
                    );

            header = Component.literal("     ").append(logo).append(Component.literal("\n\n\n\n\n").withStyle(ChatFormatting.RESET));

        } else {
            header = Component.literal(TabListVariables.tablistCharacters(""));
        }

        String footerString = TabListVariables.tablistCharacters("#N&fOnline: &e#PLAYERCOUNT #N&7| TPS: &a#TPS &7 MSPT: &a#MSPT &7 Uptime: &a#UPTIME &7|");
        Component footer = Component.literal(footerString);
        player.connection.send(new ClientboundTabListPacket(header, footer));
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

    public static int randomJadeCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.JADE_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.JADE_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }

    public static int randomAmberCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.AMBER_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.AMBER_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }

    public static int randomSiliconCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.SILICON_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.SILICON_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }

    public static int randomRubyCurrencyConversion() {
        Random random = new Random();
        return random.nextInt(CommonConfig.RUBY_CURRENCY_CONVERSION_RATE_MIN.getAsInt(), CommonConfig.RUBY_CURRENCY_CONVERSION_RATE_MAX.getAsInt());
    }
}