package com.timeshipmodding.villagecraft3essentials.infrastructure.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> OASIS_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> SWEDISHFURNITURESTORE_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> LONDON_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> BLACKWATER_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> OASIS_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> SWEDISHFURNITURESTORE_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> LONDON_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> BLACKWATER_BLACKLISTED_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> VILLAGECRAFTCITY_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> OASIS_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> SWEDISHFURNITURESTORE_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> LONDON_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> BLACKWATER_GOVERNMENT_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> MAYOR_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> SHERIFF_NAME;
    public static ModConfigSpec.ConfigValue<String> TREASURER_GROUP_NAME;
    public static ModConfigSpec.ConfigValue<String> JAILED_GROUP_NAME;
    public static ModConfigSpec.BooleanValue CHAT_TAB_NAME_FORMATTING;
    public static ModConfigSpec.BooleanValue ENABLE_VILLAGECRAFT3_LOGO_TABLIST;
    public static ModConfigSpec.BooleanValue ENABLE_BRASSWORKS_MISSIONS_OVERRIDE;
    public static ModConfigSpec.BooleanValue ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION;
    public static ModConfigSpec.BooleanValue ENABLE_TOWN_DEBUG_BLUEMAP_MARKERS;
    public static ModConfigSpec.BooleanValue ENABLE_WAR_SYSTEM_FEATURES;
    public static ModConfigSpec.IntValue CORE_RESPAWN_TIME_MIN;
    public static ModConfigSpec.IntValue CORE_RESPAWN_TIME_MAX;
    public static ModConfigSpec.IntValue VILLAGECRAFTCITY_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue OASIS_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue SWEDISHFURNITURESTORE_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue LONDON_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue BLACKWATER_POINT_MULTIPLIER;
    public static ModConfigSpec.IntValue VILLAGECRAFTCITY_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue OASIS_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue SWEDISHFURNITURESTORE_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue LONDON_COMPLETED_MISSIONS;
    public static ModConfigSpec.IntValue BLACKWATER_COMPLETED_MISSIONS;
    public static ModConfigSpec.ConfigValue<String> DISCORD_SERVER_ID;
    public static ModConfigSpec.ConfigValue<String> DISCORD_JAIL_ROLE_ID;
    public static final ModConfigSpec SPEC;

    static {
        BUILDER.push("Town Luckperms Group Names");
        BUILDER.comment("Names of the town luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_GROUP_NAME = BUILDER
                .define("villagecraftCityGroupName", "vcc.civ");
        OASIS_GROUP_NAME = BUILDER
                .define("oasisGroupName", "slubberdegullions");
        SWEDISHFURNITURESTORE_GROUP_NAME = BUILDER
                .define("swedishFurnitureStoreGroupName", "minimum-wage-employees");
        LONDON_GROUP_NAME = BUILDER
                .define("londonGroupName", "engineers");
        BLACKWATER_GROUP_NAME = BUILDER
                .define("blackWaterGroupName", "wog");

        BUILDER.pop();
        BUILDER.push("Town Blacklisted Luckperms Group Names");
        BUILDER.comment("Names of the town blacklisted luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_BLACKLISTED_GROUP_NAME = BUILDER
                .define("villagecraftCityBlacklistedGroupName", "villagecraftcity-blacklisted");
        OASIS_BLACKLISTED_GROUP_NAME = BUILDER
                .define("oasisBlacklistedGroupName", "oasis-blacklisted");
        SWEDISHFURNITURESTORE_BLACKLISTED_GROUP_NAME = BUILDER
                .define("swedishFurnitureStoreBlacklistedGroupName", "swedishfurniturestore-blacklisted");
        LONDON_BLACKLISTED_GROUP_NAME = BUILDER
                .define("londonBlacklistedGroupName", "london-blacklisted");
        BLACKWATER_BLACKLISTED_GROUP_NAME = BUILDER
                .define("blackWaterBlacklistedGroupName", "blackwater-blacklisted");

        BUILDER.pop();
        BUILDER.push("Town Government Luckperms Group Names");
        BUILDER.comment("Names of the town government luckperms group names that are set up on the server");

        VILLAGECRAFTCITY_GOVERNMENT_GROUP_NAME = BUILDER
                .define("villagecraftCityGovernmentGroupName", "vcc.gov");
        OASIS_GOVERNMENT_GROUP_NAME = BUILDER
                .define("oasisGovernmentGroupName", "the-gubernatorials");
        SWEDISHFURNITURESTORE_GOVERNMENT_GROUP_NAME = BUILDER
                .define("swedishFurnitureStoreGovernmentGroupName", "it-support-team");
        LONDON_GOVERNMENT_GROUP_NAME = BUILDER
                .define("londonGovernmentGroupName", "chief-technicians");
        BLACKWATER_GOVERNMENT_GROUP_NAME = BUILDER
                .define("blackWaterGovernmentGroupName", "wog-enterprises");

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
        BUILDER.push("Enable Create: Brassworks Missions Reward Override");
        BUILDER.comment("Enables override and replacement of brassworks mission rewards with currency gems depending on luckperms groups");

        ENABLE_BRASSWORKS_MISSIONS_OVERRIDE = BUILDER
                .define("enableBrassworksMissionsRewardOverride", false);

        BUILDER.pop();
        BUILDER.push("Bluemap Compat Config");

        ENABLE_SETSPAWN_BLUEMAP_MARKER_CREATION = BUILDER
                .comment("Enables /{town} setspawn commands to create a bluemap poi marker")
                .define("enableSetspawnBluemapMarkerCreation", false);

        ENABLE_TOWN_DEBUG_BLUEMAP_MARKERS = BUILDER
                .comment("Enables debug entry for which town the player is standing in based on bluemap town border markers")
                .define("enableTownDebugBluemapMarkers", false);

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
                .comment("The amount VillageCraft City's war score is multiplied by to get VillageCraft City's war points")
                .defineInRange("villagecraftCityPointMultiplier", 1, 1, Integer.MAX_VALUE);
        OASIS_POINT_MULTIPLIER = BUILDER
                .comment("The amount Oasis's war score is multiplied by to get Oasis's war points")
                .defineInRange("oasisPointMultiplier", 1, 1, Integer.MAX_VALUE);
        SWEDISHFURNITURESTORE_POINT_MULTIPLIER = BUILDER
                .comment("The amount Swedish Furniture Store's war score is multiplied by to get Swedish Furniture Store's war points")
                .defineInRange("swedishFurnitureStorePointMultiplier", 1, 1, Integer.MAX_VALUE);
        LONDON_POINT_MULTIPLIER = BUILDER
                .comment("The amount London's war score is multiplied by to get London's war points")
                .defineInRange("londonPointMultiplier", 1, 1, Integer.MAX_VALUE);
        BLACKWATER_POINT_MULTIPLIER = BUILDER
                .comment("The amount BlackWater's war score is multiplied by to get BlackWater's war points")
                .defineInRange("blackWaterPointMultiplier", 1, 1, Integer.MAX_VALUE);
        VILLAGECRAFTCITY_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of VillageCraft City has completed on the main server. This value is added to VillageCraft City's war points")
                .defineInRange("villagecraftCityCompletedMissions", 0, 1, Integer.MAX_VALUE);
        OASIS_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of Oasis has completed on the main server. This value is added to Oasis's war points")
                .defineInRange("oasisCompletedMissions", 0, 1, Integer.MAX_VALUE);
        SWEDISHFURNITURESTORE_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of Swedish Furniture Store has completed on the main server. This value is added to Swedish Furniture Store's war points")
                .defineInRange("swedishFurnitureStoreCompletedMissions", 0, 1, Integer.MAX_VALUE);
        LONDON_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of London have completed on the main server. This value is added to London's war points")
                .defineInRange("londonCompletedMissions", 0, 1, Integer.MAX_VALUE);
        BLACKWATER_COMPLETED_MISSIONS = BUILDER
                .comment("Number of missions members of BlackWater have completed on the main server. This value is added to BlackWater's war points")
                .defineInRange("blackWaterCompletedMissions", 0, 1, Integer.MAX_VALUE);

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