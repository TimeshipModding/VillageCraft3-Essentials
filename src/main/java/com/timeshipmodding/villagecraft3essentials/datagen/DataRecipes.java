package com.timeshipmodding.villagecraft3essentials.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static net.minecraft.world.item.Items.*;

public class DataRecipes extends RecipeProvider implements IConditionBuilder {
    public DataRecipes(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(packOutput, pRegistries);
    }

    private static final List<ItemLike> RUBY_SMELTABLES = List.of(RUBY_ORE.get(), DEEPSLATE_RUBY_ORE.get());
    private static final List<ItemLike> AMBER_SMELTABLES = List.of(AMBER_ORE.get(), DEEPSLATE_AMBER_ORE.get());

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Shaped Crafting
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_SWORD.get())
                .pattern("#")
                .pattern("#")
                .pattern("!")
                .define('#', RUBY.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_SHOVEL.get())
                .pattern("#")
                .pattern("!")
                .pattern("!")
                .define('#', RUBY.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_PICKAXE.get())
                .pattern("###")
                .pattern(" ! ")
                .pattern(" ! ")
                .define('#', RUBY.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_AXE.get())
                .pattern("##")
                .pattern("#!")
                .pattern(" !")
                .define('#', RUBY.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, RUBY_HOE.get())
                .pattern("##")
                .pattern(" !")
                .pattern(" !")
                .define('#', RUBY.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_HELMET.get())
                .pattern("###")
                .pattern("# #")
                .define('#', RUBY.get()).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_CHESTPLATE.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', RUBY.get()).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_LEGGINGS.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', RUBY.get()).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, RUBY_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', RUBY.get()).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_SWORD.get())
                .pattern("#")
                .pattern("#")
                .pattern("!")
                .define('#', AMBER.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_SHOVEL.get())
                .pattern("#")
                .pattern("!")
                .pattern("!")
                .define('#', AMBER.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_PICKAXE.get())
                .pattern("###")
                .pattern(" ! ")
                .pattern(" ! ")
                .define('#', AMBER.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_AXE.get())
                .pattern("##")
                .pattern("#!")
                .pattern(" !")
                .define('#', AMBER.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AMBER_HOE.get())
                .pattern("##")
                .pattern(" !")
                .pattern(" !")
                .define('#', AMBER.get()).define('!', Items.STICK).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).unlockedBy(getHasName(Items.STICK), has(Items.STICK)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_HELMET.get())
                .pattern("###")
                .pattern("# #")
                .define('#', AMBER.get()).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_CHESTPLATE.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', AMBER.get()).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_LEGGINGS.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', AMBER.get()).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AMBER_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', AMBER.get()).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BLACK_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BLACK_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BLUE_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BLUE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BROWN_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.BROWN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CYAN_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.CYAN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GRAY_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.GRAY_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GREEN_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.GREEN_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIGHT_BLUE_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIGHT_BLUE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIGHT_GRAY_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIGHT_GRAY_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIME_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIME_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MAGENTA_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.MAGENTA_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ORANGE_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.ORANGE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PINK_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.PINK_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PURPLE_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.PURPLE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RED_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.RED_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, WHITE_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.WHITE_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, YELLOW_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.YELLOW_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, WORM_ON_A_STICK.get())
                .pattern("# ")
                .pattern(" X")
                .define('#', FISHING_ROD).define('X', WORM.get()).group("villagecraft3essentials").unlockedBy(getHasName(WORM.get()), has(WORM.get())).save(recipeOutput);

        // Shapeless Crafting
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RUBY.get(), 9).requires(RUBY_BLOCK.get()).group("villagecraft3essentials").unlockedBy(getHasName(RUBY_BLOCK.get()), has(RUBY_BLOCK.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RUBY_BLOCK.get()).requires(RUBY.get(), 9).group("villagecraft3essentials").unlockedBy(getHasName(RUBY.get()), has(RUBY_BLOCK.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AMBER.get(), 9).requires(AMBER_BLOCK.get()).group("villagecraft3essentials").unlockedBy(getHasName(AMBER_BLOCK.get()), has(AMBER_BLOCK.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AMBER_BLOCK.get()).requires(AMBER.get(), 9).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, POLICE_BATON.get()).requires(MACE).requires(BLACK_DYE).group("villagecraft3essentials").unlockedBy(getHasName(MACE), has(MACE)).save(recipeOutput);

        // Simple Cooking Recipe
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 200)
                .unlockedBy(getHasName(WORM.get()), has(WORM)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 100)
                .unlockedBy(getHasName(WORM.get()), has(WORM)).save(recipeOutput, getItemName(WORM) + "_from_smoking");
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(WORM), RecipeCategory.FOOD, COOKED_WORM, 0.35F, 600)
                .unlockedBy(getHasName(WORM.get()), has(WORM)).save(recipeOutput, getItemName(WORM) + "_from_campfire_cooking");

        // Ore Smelting
        oreSmelting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, RUBY.get(), 1.0F, 200, "ruby");
        oreSmelting(recipeOutput, AMBER_SMELTABLES, RecipeCategory.MISC, AMBER.get(), 1.0F, 200, "amber");

        // Ore Blasting
        oreBlasting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, RUBY.get(), 1.0F, 100, "ruby");
        oreBlasting(recipeOutput, AMBER_SMELTABLES, RecipeCategory.MISC, AMBER.get(), 1.0F, 100, "amber");

        // Ruby and Amber Netherite Smithing
        rubyNetheriteSmithing(recipeOutput, RUBY_AXE.get(), RecipeCategory.TOOLS, NETHERITE_AXE);
        rubyNetheriteSmithing(recipeOutput, RUBY_HOE.get(), RecipeCategory.TOOLS, NETHERITE_HOE);
        rubyNetheriteSmithing(recipeOutput, RUBY_PICKAXE.get(), RecipeCategory.TOOLS, NETHERITE_PICKAXE);
        rubyNetheriteSmithing(recipeOutput, RUBY_SHOVEL.get(), RecipeCategory.TOOLS, NETHERITE_SHOVEL);
        rubyNetheriteSmithing(recipeOutput, RUBY_SWORD.get(), RecipeCategory.TOOLS, NETHERITE_SWORD);
        rubyNetheriteSmithing(recipeOutput, RUBY_HELMET.get(), RecipeCategory.TOOLS, NETHERITE_HELMET);
        rubyNetheriteSmithing(recipeOutput, RUBY_CHESTPLATE.get(), RecipeCategory.TOOLS, NETHERITE_CHESTPLATE);
        rubyNetheriteSmithing(recipeOutput, RUBY_LEGGINGS.get(), RecipeCategory.TOOLS, NETHERITE_LEGGINGS);
        rubyNetheriteSmithing(recipeOutput, RUBY_BOOTS.get(), RecipeCategory.TOOLS, NETHERITE_BOOTS);
        amberNetheriteSmithing(recipeOutput, AMBER_AXE.get(), RecipeCategory.TOOLS, NETHERITE_AXE);
        amberNetheriteSmithing(recipeOutput, AMBER_HOE.get(), RecipeCategory.TOOLS, NETHERITE_HOE);
        amberNetheriteSmithing(recipeOutput, AMBER_PICKAXE.get(), RecipeCategory.TOOLS, NETHERITE_PICKAXE);
        amberNetheriteSmithing(recipeOutput, AMBER_SHOVEL.get(), RecipeCategory.TOOLS, NETHERITE_SHOVEL);
        amberNetheriteSmithing(recipeOutput, AMBER_SWORD.get(), RecipeCategory.TOOLS, NETHERITE_SWORD);
        amberNetheriteSmithing(recipeOutput, AMBER_HELMET.get(), RecipeCategory.TOOLS, NETHERITE_HELMET);
        amberNetheriteSmithing(recipeOutput, AMBER_CHESTPLATE.get(), RecipeCategory.TOOLS, NETHERITE_CHESTPLATE);
        amberNetheriteSmithing(recipeOutput, AMBER_LEGGINGS.get(), RecipeCategory.TOOLS, NETHERITE_LEGGINGS);
        amberNetheriteSmithing(recipeOutput, AMBER_BOOTS.get(), RecipeCategory.TOOLS, NETHERITE_BOOTS);
    }

    // Generate Methods
    protected static void rubyNetheriteSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem), Ingredient.of(Items.NETHERITE_INGOT), pCategory, pResultItem
                )
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(pRecipeOutput, getItemName(pResultItem) + "_ruby_smithing");
    }

    protected static void amberNetheriteSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem), Ingredient.of(Items.NETHERITE_INGOT), pCategory, pResultItem
                )
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(pRecipeOutput, getItemName(pResultItem) + "_amber_smithing");
    }
}