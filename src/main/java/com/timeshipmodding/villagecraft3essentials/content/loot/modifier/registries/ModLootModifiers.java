package com.timeshipmodding.villagecraft3essentials.content.loot.modifier.registries;

import com.mojang.serialization.MapCodec;
import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.loot.modifier.*;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, VillageCraft3Essentials.MODID);

    public static final Supplier<MapCodec<AddItemLootModifier>> ADD_ITEM_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIERS.register("add_item_loot_modifier", () -> AddItemLootModifier.CODEC);
    public static final Supplier<MapCodec<SwapItemLootModifier>> SWAP_ITEM_LOOT_MODIFIER =
            GLOBAL_LOOT_MODIFIERS.register("swap_item_loot_modifier", () -> SwapItemLootModifier.CODEC);
}