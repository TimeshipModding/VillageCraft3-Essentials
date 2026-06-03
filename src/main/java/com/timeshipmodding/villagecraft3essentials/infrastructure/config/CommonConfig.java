package com.timeshipmodding.villagecraft3essentials.infrastructure.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CommonConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MIN;
    public static ModConfigSpec.IntValue RUBY_CURRENCY_CONVERSION_RATE_MAX;
    public static ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MIN;
    public static ModConfigSpec.IntValue AMBER_CURRENCY_CONVERSION_RATE_MAX;
    public static ModConfigSpec.LongValue JAIL_COMMAND_COOLDOWN;
    public static ModConfigSpec.IntValue JAIL_RELEASE_TIME;
    public static ModConfigSpec.LongValue KICK_COMMAND_COOLDOWN;
    public static ModConfigSpec.DoubleValue WALLET_PERCENTAGE_DROPPED_ON_DEATH;
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
        BUILDER.push("Jail Command Config");

        JAIL_COMMAND_COOLDOWN = BUILDER
                .comment("The cooldown in seconds between jailing players using /{town} jail")
                .defineInRange("jailCommandCooldown", 64800, 0, Long.MAX_VALUE);
        JAIL_RELEASE_TIME = BUILDER
                .comment("The time in seconds after a player is jailed to when they are automatically pardoned")
                .defineInRange("jailReleaseTime", 43200, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        BUILDER.push("Kick Command Config");

        KICK_COMMAND_COOLDOWN = BUILDER
                .comment("The cooldown in seconds between kicking players from towns using /{town} kick")
                .defineInRange("kickCommandCooldown", 600, 0, Long.MAX_VALUE);

        BUILDER.pop();
        BUILDER.push("Wallet Config");

        WALLET_PERCENTAGE_DROPPED_ON_DEATH = BUILDER
                .comment("The percentage of total items stored in a player's wallet that is dropped on death")
                .defineInRange("walletPercentageDroppedOnDeath", 0.25, 0, 1);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}