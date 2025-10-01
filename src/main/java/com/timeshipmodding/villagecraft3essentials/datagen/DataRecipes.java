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
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModPermitItems.*;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIME_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIME_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LIGHT_GRAY_ATM.get())
                .pattern("#!#")
                .pattern("#y#")
                .pattern("#x#")
                .define('#', Blocks.LIGHT_GRAY_CONCRETE).define('!', Blocks.REDSTONE_TORCH).define('y', Blocks.HOPPER).define('x', Items.IRON_INGOT).group("villagecraft3essentials").unlockedBy(getHasName(AMBER.get()), has(AMBER.get())).save(recipeOutput);
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

        // Shapeless Crafting for Permits
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_2.get()).requires(VC_PERMIT_1.get(), 2).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_3.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_2.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_4.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_3.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_5.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_4.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_6.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_5.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_7.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_6.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_8.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_7.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_9.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_8.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_10.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_9.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_11.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_10.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_12.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_11.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_13.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_12.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_14.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_13.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_15.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_14.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_16.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_15.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_17.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_16.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_18.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_17.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_19.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_18.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_20.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_19.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_21.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_20.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_22.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_21.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_23.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_22.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_24.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_23.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_25.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_24.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_26.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_25.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_27.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_26.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_28.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_27.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_29.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_28.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_30.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_29.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_31.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_30.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_32.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_31.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_33.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_32.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_34.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_33.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_35.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_34.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_36.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_35.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_37.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_36.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_38.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_37.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_39.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_38.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_40.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_39.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_41.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_40.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_42.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_41.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_43.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_42.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_44.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_43.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_45.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_44.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_46.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_45.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_47.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_46.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_48.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_47.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_49.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_48.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_50.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_49.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_51.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_50.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_52.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_51.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_53.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_52.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_54.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_53.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_55.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_54.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_56.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_55.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_57.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_56.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_58.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_57.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_59.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_58.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_60.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_59.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_61.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_60.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_62.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_61.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_63.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_62.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VC_PERMIT_64.get()).requires(VC_PERMIT_1.get()).requires(VC_PERMIT_63.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_2.get()).requires(GC_PERMIT_1.get(), 2).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_3.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_2.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_4.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_3.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_5.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_4.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_6.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_5.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_7.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_6.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_8.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_7.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_9.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_8.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_10.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_9.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_11.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_10.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_12.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_11.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_13.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_12.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_14.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_13.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_15.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_14.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_16.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_15.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_17.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_16.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_18.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_17.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_19.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_18.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_20.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_19.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_21.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_20.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_22.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_21.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_23.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_22.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_24.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_23.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_25.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_24.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_26.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_25.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_27.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_26.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_28.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_27.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_29.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_28.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_30.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_29.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_31.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_30.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_32.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_31.get()).group("villagecraft3essentials").unlockedBy(getHasName(GC_PERMIT_1.get()), has(GC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_33.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_32.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_34.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_33.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_35.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_34.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_36.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_35.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_37.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_36.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_38.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_37.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_39.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_38.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_40.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_39.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_41.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_40.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_42.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_41.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_43.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_42.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_44.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_43.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_45.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_44.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_46.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_45.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_47.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_46.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_48.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_47.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_49.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_48.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_50.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_49.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_51.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_50.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_52.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_51.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_53.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_52.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_54.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_53.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_55.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_54.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_56.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_55.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_57.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_56.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_58.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_57.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_59.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_58.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_60.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_59.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_61.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_60.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_62.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_61.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_63.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_62.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GC_PERMIT_64.get()).requires(GC_PERMIT_1.get()).requires(GC_PERMIT_63.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_2.get()).requires(AC_PERMIT_1.get(), 2).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_3.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_2.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_4.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_3.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_5.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_4.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_6.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_5.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_7.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_6.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_8.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_7.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_9.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_8.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_10.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_9.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_11.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_10.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_12.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_11.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_13.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_12.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_14.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_13.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_15.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_14.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_16.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_15.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_17.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_16.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_18.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_17.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_19.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_18.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_20.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_19.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_21.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_20.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_22.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_21.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_23.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_22.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_24.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_23.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_25.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_24.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_26.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_25.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_27.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_26.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_28.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_27.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_29.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_28.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_30.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_29.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_31.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_30.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_32.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_31.get()).group("villagecraft3essentials").unlockedBy(getHasName(AC_PERMIT_1.get()), has(AC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_33.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_32.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_34.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_33.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_35.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_34.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_36.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_35.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_37.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_36.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_38.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_37.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_39.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_38.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_40.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_39.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_41.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_40.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_42.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_41.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_43.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_42.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_44.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_43.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_45.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_44.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_46.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_45.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_47.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_46.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_48.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_47.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_49.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_48.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_50.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_49.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_51.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_50.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_52.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_51.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_53.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_52.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_54.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_53.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_55.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_54.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_56.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_55.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_57.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_56.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_58.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_57.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_59.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_58.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_60.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_59.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_61.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_60.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_62.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_61.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_63.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_62.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AC_PERMIT_64.get()).requires(AC_PERMIT_1.get()).requires(AC_PERMIT_63.get()).group("villagecraft3essentials").unlockedBy(getHasName(VC_PERMIT_1.get()), has(VC_PERMIT_1.get())).save(recipeOutput);

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

        // Netherite Smithing
        netheriteSmithing(recipeOutput, AMBER_AXE.get(), RecipeCategory.TOOLS, AMBER_NETHERITE_AXE.get());
        netheriteSmithing(recipeOutput, AMBER_HOE.get(), RecipeCategory.TOOLS, AMBER_NETHERITE_HOE.get());
        netheriteSmithing(recipeOutput, AMBER_PICKAXE.get(), RecipeCategory.TOOLS, AMBER_NETHERITE_PICKAXE.get());
        netheriteSmithing(recipeOutput, AMBER_SHOVEL.get(), RecipeCategory.TOOLS, AMBER_NETHERITE_SHOVEL.get());
        netheriteSmithing(recipeOutput, AMBER_SWORD.get(), RecipeCategory.TOOLS, AMBER_NETHERITE_SWORD.get());
        netheriteSmithing(recipeOutput, RUBY_AXE.get(), RecipeCategory.TOOLS, RUBY_NETHERITE_AXE.get());
        netheriteSmithing(recipeOutput, RUBY_HOE.get(), RecipeCategory.TOOLS, RUBY_NETHERITE_HOE.get());
        netheriteSmithing(recipeOutput, RUBY_PICKAXE.get(), RecipeCategory.TOOLS, RUBY_NETHERITE_PICKAXE.get());
        netheriteSmithing(recipeOutput, RUBY_SHOVEL.get(), RecipeCategory.TOOLS, RUBY_NETHERITE_SHOVEL.get());
        netheriteSmithing(recipeOutput, RUBY_SWORD.get(), RecipeCategory.TOOLS, RUBY_NETHERITE_SWORD.get());

        // Ruby and Amber Netherite Armor Smithing
        rubyNetheriteSmithing(recipeOutput, RUBY_HELMET.get(), RecipeCategory.TOOLS, NETHERITE_HELMET);
        rubyNetheriteSmithing(recipeOutput, RUBY_CHESTPLATE.get(), RecipeCategory.TOOLS, NETHERITE_CHESTPLATE);
        rubyNetheriteSmithing(recipeOutput, RUBY_LEGGINGS.get(), RecipeCategory.TOOLS, NETHERITE_LEGGINGS);
        rubyNetheriteSmithing(recipeOutput, RUBY_BOOTS.get(), RecipeCategory.TOOLS, NETHERITE_BOOTS);
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