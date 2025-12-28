package com.timeshipmodding.villagecraft3essentials.infrastructure.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> GRIPPERCITY_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> AMBERCAVES_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> GRIPPERCITY_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> AMBERCAVES_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> GRIPPERCITY_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> AMBERCAVES_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> MAYOR_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> CHIEF_OF_POLICE_NAME;
    public static ModConfigSpec.ConfigValue<String> TREASURER_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> JAILED_GROUP_NAME;
    public static ModConfigSpec.BooleanValue CHAT_TAB_NAME_FORMATTING;
    public static ModConfigSpec.BooleanValue ENABLE_VILLAGECRAFT3_LOGO_TABLIST;
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
                .define("villagecraftCityBlacklistedGroupName", "villagecraftcityblacklisted");
        GRIPPERCITY_BLACKLISTED_GROUP_NAME = BUILDER
                .define("gripperCityBlacklistedGroupName", "grippercityblacklisted");
        AMBERCAVES_BLACKLISTED_GROUP_NAME = BUILDER
                .define("amberCavesBlacklistedGroupName", "ambercavesblacklisted");

        BUILDER.pop();
        BUILDER.push("Town Government Luckperms Group Names");
        BUILDER.comment("Names of the town government luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_GOVERNMENT_GROUP_NAME = BUILDER
                .define("villagecraftCityGovernmentGroupName", "villagecraftcitygovernment");
        GRIPPERCITY_GOVERNMENT_GROUP_NAME = BUILDER
                .define("gripperCityGovernmentGroupName", "grippercitygovernment");
        AMBERCAVES_GOVERNMENT_GROUP_NAME = BUILDER
                .define("amberCavesGovernmentGroupName", "ambercavesgovernment");

        BUILDER.pop();
        BUILDER.push("Town Government Role Luckperms Group Names");
        BUILDER.comment("Names of the town government role luckperms group names that are set up on the server");

        MAYOR_GROUP_NAME = BUILDER
                .define("mayorGroupName", "mayor");
        CHIEF_OF_POLICE_NAME = BUILDER
                .define("chiefOfPoliceGroupName", "chiefofpolice");
        TREASURER_GROUP_NAME = BUILDER
                .define("treasurerGroupName", "treasurer");

        BUILDER.pop();
        BUILDER.push("Jailed Luckperms Group Name");
        BUILDER.comment("Name of the jailed luckperms group name that is set up on the server");

        JAILED_GROUP_NAME = BUILDER
                .define("jailedGroupName", "jailed");

        BUILDER.pop();
        BUILDER.push("Player Name, Tablist and Chat Formatting");
        BUILDER.comment("Formats all player's names, tablist and chat messages depending on luckperms configurations");

        CHAT_TAB_NAME_FORMATTING = BUILDER
                .define("enableChatTabNameFormatting", false);

        BUILDER.pop();
        BUILDER.push("Enable VillageCraft 3 Logo in Tablist");
        BUILDER.comment("Enables the villagecraft 3 logo to render at the top of the tablist. Don't enable unless your server is the actual smp");

        ENABLE_VILLAGECRAFT3_LOGO_TABLIST = BUILDER
                .define("enableVillageCraft3LogoTablist", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}