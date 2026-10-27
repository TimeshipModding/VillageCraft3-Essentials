package com.timeshipmodding.villagecraft3essentials.compat.vanillabackport;

import com.blackgear.platform.client.GameRendering;
import com.blackgear.vanillabackport.common.level.items.NautilusArmorItem;
import com.blackgear.vanillabackport.common.level.items.spear.SpearItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.function.Supplier;

public class VanillaBackportMethods {
    public static Supplier<Item> getSpearItemSupplier(Tier currencyTier, Item.Properties properties) {
        return () -> new SpearItem(currencyTier,
                1.05F, 1.075F, 0.5F, 3.0F,
                7.5F, 6.5F, 5.1F, 10.0F, 4.6F,
                properties);
    }

    public static Supplier<Item> getNautilusArmorItemSupplier(Holder<ArmorMaterial> currencyArmorMaterial, Item.Properties properties) {
        return () -> new NautilusArmorItem(currencyArmorMaterial, properties.stacksTo(1));
    }

    public static void itemLikeRendering(GameRendering.ItemLikeRenderingEvent event) {
        event.simple(SpearRenderer.INSTANCE, SpearRenderer.SPEARS);
    }
}
