package com.timeshipmodding.villagecraft3essentials.datagen.tags;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;
import static com.timeshipmodding.villagecraft3essentials.util.tags.registries.ModItemTags.*;

public class DataItemTags extends ItemTagsProvider {
    public DataItemTags(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, VillageCraft3Essentials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Minecraft Tags
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(RUBY_HELMET.get())
                .add(RUBY_CHESTPLATE.get())
                .add(RUBY_LEGGINGS.get())
                .add(RUBY_BOOTS.get())
                .add(AMBER_HELMET.get())
                .add(AMBER_CHESTPLATE.get())
                .add(AMBER_LEGGINGS.get())
                .add(AMBER_BOOTS.get());
        tag(ItemTags.TRIM_MATERIALS)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(RUBY.get())
                .add(AMBER.get());
        tag(ItemTags.SHOVELS)
                .add(RUBY_SHOVEL.get())
                .add(RUBY_NETHERITE_SHOVEL.get())
                .add(AMBER_SHOVEL.get())
                .add(AMBER_NETHERITE_SHOVEL.get());
        tag(ItemTags.PICKAXES)
                .add(RUBY_PICKAXE.get())
                .add(RUBY_NETHERITE_PICKAXE.get())
                .add(AMBER_PICKAXE.get())
                .add(AMBER_NETHERITE_PICKAXE.get());
        tag(ItemTags.AXES)
                .add(RUBY_AXE.get())
                .add(RUBY_NETHERITE_AXE.get())
                .add(AMBER_AXE.get())
                .add(AMBER_NETHERITE_AXE.get());
        tag(ItemTags.HOES)
                .add(RUBY_HOE.get())
                .add(RUBY_NETHERITE_HOE.get())
                .add(AMBER_HOE.get())
                .add(AMBER_NETHERITE_HOE.get());
        tag(ItemTags.SWORDS)
                .add(RUBY_SWORD.get())
                .add(RUBY_NETHERITE_SWORD.get())
                .add(AMBER_SWORD.get())
                .add(AMBER_NETHERITE_SWORD.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(RUBY_BOOTS.get())
                .add(AMBER_BOOTS.get());
        tag(ItemTags.LEG_ARMOR)
                .add(RUBY_LEGGINGS.get())
                .add(AMBER_LEGGINGS.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(RUBY_CHESTPLATE.get())
                .add(AMBER_CHESTPLATE.get());
        tag(ItemTags.HEAD_ARMOR)
                .add(RUBY_HELMET.get())
                .add(AMBER_HELMET.get());
        tag(ItemTags.BREAKS_DECORATED_POTS)
                .add(POLICE_BATON.get());
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(POLICE_BATON.get());
        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(POLICE_BATON.get());
        tag(ItemTags.MACE_ENCHANTABLE)
                .add(POLICE_BATON.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(POLICE_BATON.get());

        // VillageCraft 3 Essentials Tags
        tag(MOLE_FOOD)
                .add(WORM.get())
                .add(COOKED_WORM.get());
    }
}
