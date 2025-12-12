package com.timeshipmodding.villagecraft3essentials.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MIN;
    public static ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MAX;
    public static ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MIN;
    public static ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MAX;
    public static final ModConfigSpec SPEC;

    static {
        BUILDER.push("Random Currency Conversion Bounds");

        RUBY_CURRENCY_CONVERSION_RATE_MIN = BUILDER
                .comment("The minimum number for ruby random currency conversion rates in the atm block")
                .defineInRange("rubyCurrencyConversionRateMin", 1, 1, 64);
        RUBY_CURRENCY_CONVERSION_RATE_MAX = BUILDER
                .comment("The maximum number for ruby random currency conversion rates in the atm block")
                .defineInRange("rubyCurrencyConversionRateMax", 10, 1, 64);
        AMBER_CURRENCY_CONVERSION_RATE_MIN = BUILDER
                .comment("The minimum number for amber random currency conversion rates in the atm block")
                .defineInRange("amberCurrencyConversionRateMin", 1, 1, 64);
        AMBER_CURRENCY_CONVERSION_RATE_MAX = BUILDER
                .comment("The maximum number for amber random currency conversion rates in the atm block")
                .defineInRange("amberCurrencyConversionRateMax", 10, 1, 64);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}