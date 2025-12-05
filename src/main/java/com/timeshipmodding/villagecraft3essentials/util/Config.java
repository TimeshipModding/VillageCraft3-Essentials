package com.timeshipmodding.villagecraft3essentials.util;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = VillageCraft3Essentials.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MIN = BUILDER
            .comment("The minimum number for ruby random currency conversion rates in the atm block")
            .defineInRange("rubyCurrencyConversionRateMin", 1, 1, 64);
    private static final ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MAX = BUILDER
            .comment("The maximum number for ruby random currency conversion rates in the atm block")
            .defineInRange("rubyCurrencyConversionRateMax", 10, 1, 64);
    private static final ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MIN = BUILDER
            .comment("The minimum number for amber random currency conversion rates in the atm block")
            .defineInRange("amberCurrencyConversionRateMin", 1, 1, 64);
    private static final ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MAX = BUILDER
            .comment("The maximum number for amber random currency conversion rates in the atm block")
            .defineInRange("amberCurrencyConversionRateMax", 10, 1, 64);

    public static final ModConfigSpec SPEC = BUILDER.build();
    public static int rubyCurrencyConversionRateMin;
    public static int rubyCurrencyConversionRateMax;
    public static int amberCurrencyConversionRateMin;
    public static int amberCurrencyConversionRateMax;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        rubyCurrencyConversionRateMin = RUBY_CURRENCY_CONVERSION_RATE_MIN.get();
        rubyCurrencyConversionRateMax = RUBY_CURRENCY_CONVERSION_RATE_MAX.get();
        amberCurrencyConversionRateMin = AMBER_CURRENCY_CONVERSION_RATE_MIN.get();
        amberCurrencyConversionRateMax = AMBER_CURRENCY_CONVERSION_RATE_MAX.get();
    }
}