package com.timeshipmodding.villagecraft3essentials.util.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> GRIPPERCITY_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> AMBERCAVES_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> GRIPPERCITY_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> AMBERCAVES_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> JAILED_GROUP_NAME;
    public static final ModConfigSpec SPEC;

    static {
        BUILDER.push("Town Luckperms Group Names");
        BUILDER.comment("Names of the town luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_GROUP_NAME = BUILDER
                .define("villagecraftCityGroupName", "villagecraftcity");
        GRIPPERCITY_GROUP_NAME = BUILDER
                .define("gripperCityGroupName", "grippercity");
        AMBERCAVES_GROUP_NAME = BUILDER
                .define("amberCavesGroupName", "ambercaves");

        BUILDER.pop();
        BUILDER.push("Town Blacklisted Luckperms Group Names");
        BUILDER.comment("Names of the town blacklisted luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME = BUILDER
                .define("villagecraftCityGroupName", "villagecraftcityblacklisted");
        GRIPPERCITY_BLACKLISTED_GROUP_NAME = BUILDER
                .define("gripperCityGroupName", "grippercityblacklisted");
        AMBERCAVES_BLACKLISTED_GROUP_NAME = BUILDER
                .define("amberCavesGroupName", "ambercavesblacklisted");

        BUILDER.pop();
        BUILDER.push("Jailed Luckperms Group Name");
        BUILDER.comment("Name of the jailed luckperms group name that is set up on the server");

        JAILED_GROUP_NAME = BUILDER
                .define("jailedGroupName", "jailed");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}