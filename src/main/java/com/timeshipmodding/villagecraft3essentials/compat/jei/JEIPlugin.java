package com.timeshipmodding.villagecraft3essentials.compat.jei;

import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModCompatItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import vectorwing.farmersdelight.FarmersDelight;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class JEIPlugin implements IModPlugin {
    Component KNIFE_INFO = Component.literal("A lightweight melee weapon, with fast swings and gentle damage.\n\nThey can harvest Straw from grasses, and guarantee secondary drops from animals.");

    @Override
    public void registerRecipes (IRecipeRegistration registration) {
        if (ModList.get().isLoaded("farmersdelight")) {
            registration.addIngredientInfo(new ItemStack(ModCompatItems.RUBY_KNIFE.get()), VanillaTypes.ITEM_STACK, KNIFE_INFO);
            registration.addIngredientInfo(new ItemStack(ModCompatItems.AMBER_KNIFE.get()), VanillaTypes.ITEM_STACK, KNIFE_INFO);
        }
    }


    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "jei_plugin");
    }
}