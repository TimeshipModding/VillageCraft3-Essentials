package com.timeshipmodding.villagecraft3essentials.datagen;

import com.timeshipmodding.villagecraft3essentials.VillageCraft3Essentials;
import com.timeshipmodding.villagecraft3essentials.content.trim.registries.ModTrimMaterials;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;
import java.util.Objects;

import static com.timeshipmodding.villagecraft3essentials.content.block.registries.ModBlocks.*;
import static com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems.*;

public class DataItemModels extends ItemModelProvider {
    public DataItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VillageCraft3Essentials.MODID, existingFileHelper);
    }

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
        trimMaterials.put(ModTrimMaterials.RUBY, 0.4F);
        trimMaterials.put(ModTrimMaterials.AMBER, 0.6F);
    }

    @Override
    protected void registerModels() {
        // Items
        basicItem(CRACKED_DIAMOND.get());
        basicItem(CRACKED_RUBY.get());
        basicItem(RUBY.get());
        basicItem(RUBY_HOOK.get());
        basicItem(CRACKED_AMBER.get());
        basicItem(AMBER.get());
        basicItem(AMBER_HOOK.get());
        basicItem(COOKED_WORM.get());
        basicItem(WORM.get());

        // Tool Items
        handheldItem(RUBY_SWORD.get());
        handheldItem(RUBY_SHOVEL.get());
        handheldItem(RUBY_PICKAXE.get());
        handheldItem(RUBY_AXE.get());
        handheldItem(RUBY_HOE.get());
        handheldItem(RUBY_KNIFE.get());
        handheldItem(RUBY_FILLET_KNIFE.get());
        handheldItem(AMBER_SWORD.get());
        handheldItem(AMBER_SHOVEL.get());
        handheldItem(AMBER_PICKAXE.get());
        handheldItem(AMBER_AXE.get());
        handheldItem(AMBER_HOE.get());
        handheldItem(AMBER_KNIFE.get());
        handheldItem(AMBER_FILLET_KNIFE.get());
        handheldItem(POLICE_BATON.get());
        handheldRodItem(WORM_ON_A_STICK.get());
        handheldRodItem(WORM_ON_A_STICK.get());

        // Armor Items
        trimmedArmorItem(RUBY_HELMET);
        trimmedArmorItem(RUBY_CHESTPLATE);
        trimmedArmorItem(RUBY_LEGGINGS);
        trimmedArmorItem(RUBY_BOOTS);
        basicItem(RUBY_HORSE_ARMOR.get());
        trimmedArmorItem(AMBER_HELMET);
        trimmedArmorItem(AMBER_CHESTPLATE);
        trimmedArmorItem(AMBER_LEGGINGS);
        trimmedArmorItem(AMBER_BOOTS);
        basicItem(AMBER_HORSE_ARMOR.get());

        // Simple Block Item
        simpleBlockItem(BLACK_ATM.get());
        simpleBlockItem(BLUE_ATM.get());
        simpleBlockItem(BROWN_ATM.get());
        simpleBlockItem(CYAN_ATM.get());
        simpleBlockItem(GRAY_ATM.get());
        simpleBlockItem(GREEN_ATM.get());
        simpleBlockItem(LIGHT_BLUE_ATM.get());
        simpleBlockItem(LIGHT_GRAY_ATM.get());
        simpleBlockItem(LIME_ATM.get());
        simpleBlockItem(MAGENTA_ATM.get());
        simpleBlockItem(ORANGE_ATM.get());
        simpleBlockItem(PINK_ATM.get());
        simpleBlockItem(PURPLE_ATM.get());
        simpleBlockItem(RED_ATM.get());
        simpleBlockItem(WHITE_ATM.get());
        simpleBlockItem(YELLOW_ATM.get());
        simpleBlockItem(DIAMOND_CORE.get());
        simpleBlockItem(RUBY_CORE.get());
        simpleBlockItem(AMBER_CORE.get());

        // withExistingParent
        withExistingParent(MOLE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    // Generate Methods
    public ItemModelBuilder handheldItem(Item item) {
        return handheldItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder handheldItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()));
    }

    public ItemModelBuilder handheldRodItem(Item item) {
        return handheldRodItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    public ItemModelBuilder handheldRodItem(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld_rod"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/" + item.getPath()));
    }

    private void trimmedArmorItem(DeferredItem<Item> itemDeferredItem) {
        final String MOD_ID = VillageCraft3Essentials.MODID;

        if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemDeferredItem.getId().getPath(), mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }
}