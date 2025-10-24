package com.timeshipmodding.villagecraft3essentials.util;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue CURRENCY_CONVERSION_RATE_MIN = BUILDER
            .comment("The minimum number for random currency conversion rates in the atm blocks")
            .defineInRange("currencyConversionRateMin", 1, 1, 64);
    private static final ModConfigSpec.IntValue CURRENCY_CONVERSION_RATE_MAX = BUILDER
            .comment("The maximum number for random currency conversion rates in the atm blocks")
            .defineInRange("currencyConversionRateMax", 10, 1, 64);

    public static final ModConfigSpec SPEC = BUILDER.build();
    public static int currencyConversionRateMin;
    public static int currencyConversionRateMax;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        currencyConversionRateMin = CURRENCY_CONVERSION_RATE_MIN.get();
        currencyConversionRateMax = CURRENCY_CONVERSION_RATE_MAX.get();
    }
}
