package com.timeshipmodding.villagecraft3essentials.content.menu.registries;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.menu.AtmMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, VillageCraft3Essentials.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<AtmMenu>> ATM_MENU =
            registerMenuType("atm_menu", AtmMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
}