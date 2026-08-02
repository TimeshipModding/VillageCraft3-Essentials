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
    public static ModConfigSpec.ConfigValue<String> SHERIFF_NAME;
    public static ModConfigSpec.ConfigValue<String> TREASURER_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> JAILED_GROUP_NAME;
    public static ModConfigSpec.BooleanValue CHAT_TAB_NAME_FORMATTING;
    public static ModConfigSpec.BooleanValue ENABLE_VILLAGECRAFT3_LOGO_TABLIST;
    public static ModConfigSpec.BooleanValue ENABLE_WAR_SYSTEM_FEATURES;
    public static ModConfigSpec.IntValue CORE_RESPAWN_TIME_MIN;
    public static ModConfigSpec.IntValue CORE_RESPAWN_TIME_MAX;
    public static ModConfigSpec.IntValue VILLAGECRAFTCITY_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue GRIPPERCITY_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue AMBERCAVES_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue VILLAGECRAFTCITY_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue GRIPPERCITY_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue AMBERCAVES_COMPLETED_MISSIONS;
    public static ModConfigSpec.ConfigValue<String> DISCORD_SERVER_ID;
    public static ModConfigSpec.ConfigValue<String> DISCORD_JAIL_ROLE_ID;
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
        SHERIFF_NAME = BUILDER
                .define("sheriffGroupName", "sheriff");
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
        BUILDER.push("War System Config");

        ENABLE_WAR_SYSTEM_FEATURES = BUILDER
                .comment("Enables the features required for VillageCraft 3's War System")
                .define("enableWarSystemFeatures", false);
        CORE_RESPAWN_TIME_MIN = BUILDER
                .comment("The minimum time in hours for a core to respawn")
                .defineInRange("coreRespawnTimeMin", 2, 0, Integer.MAX_VALUE);
        CORE_RESPAWN_TIME_MAX = BUILDER
                .comment("The maximum time in hours for a core to respawn")
                .defineInRange("coreRespawnTimeMax", 4, 0, Integer.MAX_VALUE);
        VILLAGECRAFTCITY_POINT_MULTIPLIER = BUILDER
                .comment("The amount VillageCraft City's score is multiplied by to get VillageCraft City's points")
                .defineInRange("villagecraftCityPointMultiplier", 1, 1, Integer.MAX_VALUE);
        GRIPPERCITY_POINT_MULTIPLIER = BUILDER
                .comment("The amount VillageCraft City's score is multiplied by to get Gripper City's points")
                .defineInRange("gripperCityPointMultiplier", 1, 1, Integer.MAX_VALUE);
        AMBERCAVES_POINT_MULTIPLIER = BUILDER
                .comment("The amount VillageCraft City's score is multiplied by to get Amber Caves' points")
                .defineInRange("amberCavesPointMultiplier", 1, 1, Integer.MAX_VALUE);
        VILLAGECRAFTCITY_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of VillageCraft City has completed on the regular server. This value is added to VillageCraft City's points")
                .defineInRange("villagecraftCityCompletedMissions", 0, 1, Integer.MAX_VALUE);
        GRIPPERCITY_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of Gripper City has completed on the regular server. This value is added to Gripper City's points")
                .defineInRange("gripperCityCompletedMissions", 0, 1, Integer.MAX_VALUE);
        AMBERCAVES_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of Amber Caves have completed on the regular server. This value is added to Amber Caves' points")
                .defineInRange("amberCavesCompletedMissions", 0, 1, Integer.MAX_VALUE);

        BUILDER.pop();
        BUILDER.push("Discord Integration Config");
        BUILDER.comment("IDs needed for VillageCraft 3 Essentials' Discord Integration features");

        DISCORD_SERVER_ID = BUILDER
                .define("discordServerID", "");
        DISCORD_JAIL_ROLE_ID = BUILDER
                .define("discordJailRoleID", "");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}