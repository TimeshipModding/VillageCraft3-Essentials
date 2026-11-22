package com.timeshipmodding.villagecraft3essentials.compat.creategoggles.content.item;

import blue.endless.jankson.annotation.Nullable;
import com.robocraft999.creategoggles.item.goggle.IGoggleHelmet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.Locale;

public class CurrencyGoggleHelmetItem extends ArmorItem implements IGoggleHelmet {
    protected final ResourceLocation textureLoc;

    public CurrencyGoggleHelmetItem(Holder<ArmorMaterial> material, Type type, Properties properties, ResourceLocation textureLoc) {
        super(material, type, properties);
        this.textureLoc = textureLoc;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.parse(String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_%d.png", textureLoc.getNamespace(), textureLoc.getPath(), slot == EquipmentSlot.LEGS ? 2 : 1));
    }
}