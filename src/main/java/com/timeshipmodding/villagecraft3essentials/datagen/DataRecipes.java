package com.timeshipmodding.villagecraft3essentials.datagen;

import com.teammetallurgy.aquaculture.init.AquaItems;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.infrastructure.tags.registries.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static net.minecraft.world.item.Items.*;

public class DataRecipes extends RecipeProvider implements IConditionBuilder {
    public DataRecipes(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(packOutput, pRegistries);
    }

    private static final List<ItemLike> RUBY_SMELTABLES = List.of(RUBY_ORE, DEEPSLATE_RUBY_ORE);
    private static final List<ItemLike> AMBER_SMELTABLES = List.of(AMBER_ORE, DEEPSLATE_AMBER_ORE);
    private static final List<Item> DYES = List.of(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE);
    private static final List<Item> ATMS = List.of(BLACK_ATM.asItem(), BLUE_ATM.asItem(), BROWN_ATM.asItem(), CYAN_ATM.asItem(), GRAY_ATM.asItem(), GREEN_ATM.asItem(), LIGHT_BLUE_ATM.asItem(), LIGHT_GRAY_ATM.asItem(), LIME_ATM.asItem(), MAGENTA_ATM.asItem(), ORANGE_ATM.asItem(), PINK_ATM.asItem(), PURPLE_ATM.asItem(), RED_ATM.asItem(), YELLOW_ATM.asItem(), WHITE_ATM.asItem());

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Shaped Crafting
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("!")
                .define('#', RUBY).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_SHOVEL)
                .pattern("#")
                .pattern("!")
                .pattern("!")
                .define('#', RUBY).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_PICKAXE)
                .pattern("###")
                .pattern(" ! ")
                .pattern(" ! ")
                .define('#', RUBY).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_AXE)
                .pattern("##")
                .pattern("#!")
                .pattern(" !")
                .define('#', RUBY).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_HOE)
                .pattern("##")
                .pattern(" !")
                .pattern(" !")
                .define('#', RUBY).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', RUBY).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', RUBY).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', RUBY).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', RUBY).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("!")
                .define('#', AMBER).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_SHOVEL)
                .pattern("#")
                .pattern("!")
                .pattern("!")
                .define('#', AMBER).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_PICKAXE)
                .pattern("###")
                .pattern(" ! ")
                .pattern(" ! ")
                .define('#', AMBER).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_AXE)
                .pattern("##")
                .pattern("#!")
                .pattern(" !")
                .define('#', AMBER).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_HOE)
                .pattern("##")
                .pattern(" !")
                .pattern(" !")
                .define('#', AMBER).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', AMBER).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', AMBER).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', AMBER).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', AMBER).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BLACK_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BLACK_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BLUE_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BLUE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BROWN_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BROWN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CYAN_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.CYAN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GRAY_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.GRAY_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GREEN_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.GREEN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIGHT_BLUE_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIGHT_BLUE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIGHT_GRAY_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIGHT_GRAY_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIME_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIME_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MAGENTA_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.MAGENTA_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ORANGE_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.ORANGE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PINK_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.PINK_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PURPLE_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.PURPLE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RED_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.RED_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WHITE_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.WHITE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, YELLOW_ATM)
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.YELLOW_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy("has_hopper", has(HOPPER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, WORM_ON_A_STICK)
                .pattern("# ")
                .pattern(" X")
                .define('#', FISHING_ROD).define('X', WORM).group("villagecraft3essentials").unlockedBy("has_worm", has(WORM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', Ingredient.of(COPPER_BLOCK, WAXED_COPPER_BLOCK)).define('S', BOLT_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_bolt_armor_trim_smithing_template", has(BOLT_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, BOLT_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, COAST_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', COBBLESTONE).define('S', COAST_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_coast_armor_trim_smithing_template", has(COAST_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, COAST_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', SANDSTONE).define('S', DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_dune_armor_trim_smithing_template", has(DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, DUNE_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EYE_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', END_STONE).define('S', EYE_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_eye_armor_trim_smithing_template", has(EYE_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, EYE_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', BREEZE_ROD).define('S', FLOW_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_flow_armor_trim_smithing_template", has(FLOW_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, FLOW_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HOST_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', TERRACOTTA).define('S', HOST_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_host_armor_trim_smithing_template", has(HOST_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, HOST_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NETHERITE_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', NETHERRACK).define('S', NETHERITE_UPGRADE_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_netherite_upgrade_smithing_template", has(NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(recipeOutput, NETHERITE_UPGRADE_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', TERRACOTTA).define('S', RAISER_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_raiser_armor_trim_smithing_template", has(RAISER_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, RAISER_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RIB_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', NETHERRACK).define('S', RIB_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_rib_armor_trim_smithing_template", has(RIB_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, RIB_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', COBBLESTONE).define('S', SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_sentry_armor_trim_smithing_template", has(SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', TERRACOTTA).define('S', SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_shaper_armor_trim_smithing_template", has(SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', COBBLED_DEEPSLATE).define('S', SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_silence_armor_trim_smithing_template", has(SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', BLACKSTONE).define('S', SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_snout_armor_trim_smithing_template", has(SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', PURPUR_BLOCK).define('S', SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_spire_armor_trim_smithing_template", has(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', PRISMARINE).define('S', TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_tide_armor_trim_smithing_template", has(TIDE_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, TIDE_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, VEX_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', COBBLESTONE).define('S', VEX_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_vex_armor_trim_smithing_template", has(VEX_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, VEX_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WARD_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', COBBLED_DEEPSLATE).define('S', WARD_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_ward_armor_trim_smithing_template", has(WARD_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, WARD_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', TERRACOTTA).define('S', WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_wayfinder_armor_trim_smithing_template", has(WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WILD_ARMOR_TRIM_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', ModItemTags.CURRENCY_GEMS).define('C', MOSSY_COBBLESTONE).define('S', WILD_ARMOR_TRIM_SMITHING_TEMPLATE).group("villagecraft3essentials").unlockedBy("has_wild_armor_trim_smithing_template", has(WILD_ARMOR_TRIM_SMITHING_TEMPLATE)).save(recipeOutput, WILD_ARMOR_TRIM_SMITHING_TEMPLATE + "_using_currency_gems");
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_KNIFE)
                .pattern("#")
                .pattern("!")
                .define('#', RUBY).define('!', STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_KNIFE)
                .pattern("#")
                .pattern("!")
                .define('#', AMBER).define('!', STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_FISHING_ROD)
                .pattern("  #")
                .pattern(" #S")
                .pattern("! S")
                .define('#', RUBY).define('!', STICK).define('S', STRING).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_FISHING_ROD)
                .pattern("  #")
                .pattern(" #S")
                .pattern("! S")
                .define('#', AMBER).define('!', STICK).define('S', STRING).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_FILLET_KNIFE)
                .pattern("  #")
                .pattern(" # ")
                .pattern("!  ")
                .define('#', RUBY).define('!', STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_FILLET_KNIFE)
                .pattern("  #")
                .pattern(" # ")
                .pattern("!  ")
                .define('#', AMBER).define('!', STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_HOOK)
                .pattern(" # ")
                .pattern("#!#")
                .pattern(" # ")
                .define('#', RUBY).define('!', AquaItems.IRON_HOOK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_HOOK)
                .pattern(" # ")
                .pattern("#!#")
                .pattern(" # ")
                .define('#', AMBER).define('!', AquaItems.IRON_HOOK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_BRUSH)
                .pattern("!")
                .pattern("#")
                .pattern("S")
                .define('#', RUBY).define('!', FEATHER).define('S', STICK).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_BRUSH)
                .pattern("!")
                .pattern("#")
                .pattern("S")
                .define('#', AMBER).define('!', FEATHER).define('S', STICK).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.ENCHANTING_TABLE)
                .pattern(" B ")
                .pattern("D#D")
                .pattern("###")
                .define('B', Items.BOOK).define('#', Blocks.OBSIDIAN).define('D', ModItemTags.CURRENCY_GEMS).unlockedBy("has_obsidian", has(Blocks.OBSIDIAN)).save(recipeOutput, ENCHANTING_TABLE + "_using_currency_gems");

        // Shapeless Crafting
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RUBY, 9).requires(RUBY_BLOCK).group("villagecraft3essentials").unlockedBy("has_ruby_block", has(RUBY_BLOCK)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RUBY_BLOCK).requires(RUBY, 9).group("villagecraft3essentials").unlockedBy("has_ruby", has(RUBY)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AMBER, 9).requires(AMBER_BLOCK).group("villagecraft3essentials").unlockedBy("has_amber_block", has(AMBER_BLOCK)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AMBER_BLOCK).requires(AMBER, 9).group("villagecraft3essentials").unlockedBy("has_amber", has(AMBER)).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, POLICE_BATON).requires(MACE).requires(BLACK_DYE).group("villagecraft3essentials").unlockedBy("has_black_dye", has(BLACK_DYE)).save(recipeOutput);

        // Color Block with Dye
        colorBlockWithDye(recipeOutput, DYES, ATMS, "atm");

        // Simple Cooking Recipe
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 200)
                .unlockedBy("has_worm", has(WORM)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 100)
                .unlockedBy("has_worm", has(WORM)).save(recipeOutput, VillageCraft3Essentials.MODID + ":" + getItemName(WORM) + "_from_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 600)
                .unlockedBy("has_worm", has(WORM)).save(recipeOutput, VillageCraft3Essentials.MODID + ":" + getItemName(WORM) + "_from_campfire_cooking");

        // Ore Smelting
        oreSmelting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, RUBY, 1.0F, 200, "ruby");
        oreSmelting(recipeOutput, AMBER_SMELTABLES, RecipeCategory.MISC, AMBER, 1.0F, 200, "amber");

        // Ore Blasting
        oreBlasting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, RUBY, 1.0F, 100, "ruby");
        oreBlasting(recipeOutput, AMBER_SMELTABLES, RecipeCategory.MISC, AMBER, 1.0F, 100, "amber");

        // Ruby and Amber Netherite Smithing
        rubyNetheriteSmithing(recipeOutput, RUBY_AXE.get(), RecipeCategory.TOOLS, NETHERITE_AXE);
        rubyNetheriteSmithing(recipeOutput, RUBY_HOE.get(), RecipeCategory.TOOLS, NETHERITE_HOE);
        rubyNetheriteSmithing(recipeOutput, RUBY_PICKAXE.get(), RecipeCategory.TOOLS, NETHERITE_PICKAXE);
        rubyNetheriteSmithing(recipeOutput, RUBY_SHOVEL.get(), RecipeCategory.TOOLS, NETHERITE_SHOVEL);
        rubyNetheriteSmithing(recipeOutput, RUBY_SWORD.get(), RecipeCategory.COMBAT, NETHERITE_SWORD);
        rubyNetheriteSmithing(recipeOutput, RUBY_KNIFE.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_KNIFE.get());
        rubyNetheriteSmithing(recipeOutput, RUBY_BRUSH.get(), RecipeCategory.TOOLS, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "netherite_brush")));
        rubyNetheriteSmithing(recipeOutput, RUBY_HELMET.get(), RecipeCategory.COMBAT, NETHERITE_HELMET);
        rubyNetheriteSmithing(recipeOutput, RUBY_CHESTPLATE.get(), RecipeCategory.COMBAT, NETHERITE_CHESTPLATE);
        rubyNetheriteSmithing(recipeOutput, RUBY_LEGGINGS.get(), RecipeCategory.COMBAT, NETHERITE_LEGGINGS);
        rubyNetheriteSmithing(recipeOutput, RUBY_BOOTS.get(), RecipeCategory.COMBAT, NETHERITE_BOOTS);
        amberNetheriteSmithing(recipeOutput, AMBER_AXE.get(), RecipeCategory.TOOLS, NETHERITE_AXE);
        amberNetheriteSmithing(recipeOutput, AMBER_HOE.get(), RecipeCategory.TOOLS, NETHERITE_HOE);
        amberNetheriteSmithing(recipeOutput, AMBER_PICKAXE.get(), RecipeCategory.TOOLS, NETHERITE_PICKAXE);
        amberNetheriteSmithing(recipeOutput, AMBER_SHOVEL.get(), RecipeCategory.TOOLS, NETHERITE_SHOVEL);
        amberNetheriteSmithing(recipeOutput, AMBER_SWORD.get(), RecipeCategory.COMBAT, NETHERITE_SWORD);
        amberNetheriteSmithing(recipeOutput, AMBER_KNIFE.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_KNIFE.get());;
        amberNetheriteSmithing(recipeOutput, AMBER_BRUSH.get(), RecipeCategory.TOOLS, BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("betterarcheology", "netherite_brush")));
        amberNetheriteSmithing(recipeOutput, AMBER_HELMET.get(), RecipeCategory.COMBAT, NETHERITE_HELMET);
        amberNetheriteSmithing(recipeOutput, AMBER_CHESTPLATE.get(), RecipeCategory.COMBAT, NETHERITE_CHESTPLATE);
        amberNetheriteSmithing(recipeOutput, AMBER_LEGGINGS.get(), RecipeCategory.COMBAT, NETHERITE_LEGGINGS);
        amberNetheriteSmithing(recipeOutput, AMBER_BOOTS.get(), RecipeCategory.COMBAT, NETHERITE_BOOTS);
    }

    // Generate Methods
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result,
                experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.Factory<T> factory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String recipeName) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, cookingSerializer, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, VillageCraft3Essentials.MODID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void colorBlockWithDye(RecipeOutput recipeOutput, List<Item> dyes, List<Item> dyeableItems, String group) {
        for(int i = 0; i < dyes.size(); ++i) {
            Item item = dyes.get(i);
            Item item1 = dyeableItems.get(i);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item1).requires(item).requires(Ingredient.of(dyeableItems.stream().filter((p_288265_) -> !p_288265_.equals(item1)).map(ItemStack::new))).group(group).unlockedBy("has_needed_dye", has(item)).save(recipeOutput, VillageCraft3Essentials.MODID + ":dye_" + getItemName(item1));
        }
    }

    protected static void rubyNetheriteSmithing(RecipeOutput recipeOutput, Item ingredient, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredient), Ingredient.of(Items.NETHERITE_INGOT), category, result)
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, VillageCraft3Essentials.MODID + ":" + getItemName(result) + "_ruby_smithing");
    }

    protected static void amberNetheriteSmithing(RecipeOutput recipeOutput, Item ingredient, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredient), Ingredient.of(Items.NETHERITE_INGOT), category, result)
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, VillageCraft3Essentials.MODID + ":" + getItemName(result) + "_amber_smithing");
    }
}