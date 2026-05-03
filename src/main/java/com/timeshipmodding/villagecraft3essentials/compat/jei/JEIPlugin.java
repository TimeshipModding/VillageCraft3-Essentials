package com.timeshipmodding.villagecraft3essentials.compat.jei;

import com.timeshipmodding.villagecraft3essentials.compat.jei.transfer.WalletRecipeTransferHandler;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import com.timeshipmodding.villagecraft3essentials.content.screen.WalletScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
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
            registration.addIngredientInfo(new ItemStack(ModItems.RUBY_KNIFE.get()), VanillaTypes.ITEM_STACK, KNIFE_INFO);
            registration.addIngredientInfo(new ItemStack(ModItems.AMBER_KNIFE.get()), VanillaTypes.ITEM_STACK, KNIFE_INFO);
        }
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "jei_plugin");
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(WalletScreen.class, 137, 29, 10, 13, RecipeTypes.CRAFTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        IRecipeTransferHandlerHelper transferHelper = registration.getTransferHelper();
        WalletRecipeTransferHandler recipeTransferHandler = new WalletRecipeTransferHandler(transferHelper);
        registration.addRecipeTransferHandler(recipeTransferHandler, RecipeTypes.CRAFTING);
    }
}