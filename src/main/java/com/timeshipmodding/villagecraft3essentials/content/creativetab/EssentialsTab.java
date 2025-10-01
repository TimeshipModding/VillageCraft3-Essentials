package com.timeshipmodding.villagecraft3essentials.content.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.world.item.Items.*;

import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModPermitItems.*;
import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;

import static com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials.MODID;


public class EssentialsTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ESSENTIALS_TAB = CREATIVE_MODE_TABS.register("essentials_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativemodetab.villagecraft3essentials.essentialstab"))
            .icon(() -> new ItemStack(Blocks.BEACON))
            .displayItems((parameters, pOutput) -> {
                pOutput.accept(Blocks.DIAMOND_BLOCK);
                pOutput.accept(RUBY_BLOCK);
                pOutput.accept(AMBER_BLOCK);
                pOutput.accept(Blocks.DIAMOND_ORE);
                pOutput.accept(Blocks.DEEPSLATE_DIAMOND_ORE);
                pOutput.accept(RUBY_ORE);
                pOutput.accept(DEEPSLATE_RUBY_ORE);
                pOutput.accept(AMBER_ORE);
                pOutput.accept(DEEPSLATE_AMBER_ORE);
                pOutput.accept(WHITE_ATM);
                pOutput.accept(LIGHT_GRAY_ATM);
                pOutput.accept(GRAY_ATM);
                pOutput.accept(BLACK_ATM);
                pOutput.accept(BROWN_ATM);
                pOutput.accept(RED_ATM);
                pOutput.accept(ORANGE_ATM);
                pOutput.accept(YELLOW_ATM);
                pOutput.accept(LIME_ATM);
                pOutput.accept(GREEN_ATM);
                pOutput.accept(CYAN_ATM);
                pOutput.accept(LIGHT_BLUE_ATM);
                pOutput.accept(BLUE_ATM);
                pOutput.accept(PURPLE_ATM);
                pOutput.accept(MAGENTA_ATM);
                pOutput.accept(PINK_ATM);
                pOutput.accept(DIAMOND_SHOVEL);
                pOutput.accept(DIAMOND_PICKAXE);
                pOutput.accept(DIAMOND_AXE);
                pOutput.accept(DIAMOND_HOE);
                pOutput.accept(DIAMOND_SWORD);
                pOutput.accept(RUBY_SHOVEL);
                pOutput.accept(RUBY_PICKAXE);
                pOutput.accept(RUBY_AXE);
                pOutput.accept(RUBY_HOE);
                pOutput.accept(RUBY_SWORD);
                pOutput.accept(AMBER_SHOVEL);
                pOutput.accept(AMBER_PICKAXE);
                pOutput.accept(AMBER_AXE);
                pOutput.accept(AMBER_HOE);
                pOutput.accept(AMBER_SWORD);
                pOutput.accept(NETHERITE_SHOVEL);
                pOutput.accept(NETHERITE_PICKAXE);
                pOutput.accept(NETHERITE_AXE);
                pOutput.accept(NETHERITE_HOE);
                pOutput.accept(NETHERITE_SWORD);
                pOutput.accept(RUBY_NETHERITE_SHOVEL);
                pOutput.accept(RUBY_NETHERITE_PICKAXE);
                pOutput.accept(RUBY_NETHERITE_AXE);
                pOutput.accept(RUBY_NETHERITE_HOE);
                pOutput.accept(RUBY_NETHERITE_SWORD);
                pOutput.accept(AMBER_NETHERITE_SHOVEL);
                pOutput.accept(AMBER_NETHERITE_PICKAXE);
                pOutput.accept(AMBER_NETHERITE_AXE);
                pOutput.accept(AMBER_NETHERITE_HOE);
                pOutput.accept(AMBER_NETHERITE_SWORD);
                pOutput.accept(DIAMOND_HELMET);
                pOutput.accept(DIAMOND_CHESTPLATE);
                pOutput.accept(DIAMOND_LEGGINGS);
                pOutput.accept(DIAMOND_BOOTS);
                pOutput.accept(DIAMOND_HORSE_ARMOR);
                pOutput.accept(RUBY_HELMET);
                pOutput.accept(RUBY_CHESTPLATE);
                pOutput.accept(RUBY_LEGGINGS);
                pOutput.accept(RUBY_BOOTS);
                pOutput.accept(RUBY_HORSE_ARMOR);
                pOutput.accept(AMBER_HELMET);
                pOutput.accept(AMBER_CHESTPLATE);
                pOutput.accept(AMBER_LEGGINGS);
                pOutput.accept(AMBER_BOOTS);
                pOutput.accept(AMBER_HORSE_ARMOR);
                pOutput.accept(NETHERITE_HELMET);
                pOutput.accept(NETHERITE_CHESTPLATE);
                pOutput.accept(NETHERITE_LEGGINGS);
                pOutput.accept(NETHERITE_BOOTS);
                pOutput.accept(POLICE_BATON);
                pOutput.accept(WORM_ON_A_STICK);
                pOutput.accept(WORM);
                pOutput.accept(COOKED_WORM);
                pOutput.accept(DIAMOND);
                pOutput.accept(RUBY);
                pOutput.accept(AMBER);
                pOutput.accept(NETHERITE_INGOT);
                pOutput.accept(VC_STARTING_PERMIT);
                pOutput.accept(GC_STARTING_PERMIT);
                pOutput.accept(AC_STARTING_PERMIT);
                pOutput.accept(VC_PERMIT_1);
                pOutput.accept(GC_PERMIT_1);
                pOutput.accept(AC_PERMIT_1);
                pOutput.accept(MOLE_SPAWN_EGG);
            }).build());
}
