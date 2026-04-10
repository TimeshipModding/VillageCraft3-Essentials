package com.timeshipmodding.villagecraft3essentials.compat.aquaculture;

import com.teammetallurgy.aquaculture.api.fishing.Hook;
import com.teammetallurgy.aquaculture.client.ClientHandler;
import com.teammetallurgy.aquaculture.item.AquaFishingRodItem;
import com.teammetallurgy.aquaculture.item.HookItem;
import com.teammetallurgy.aquaculture.item.ItemFilletKnife;
import com.timeshipmodding.villagecraft3essentials.content.item.registries.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Objects;
import java.util.function.Supplier;

public class AquaMethods {
    public static Supplier<Item> getAquaFishingRodItemSupplier(Tier tier) {
        return () -> new AquaFishingRodItem(tier, new Item.Properties().durability(450));
    }

    public static Supplier<Item> getFilletKnifeItemSupplier(Tier tier) {
        return () -> new ItemFilletKnife(tier, new Item.Properties());
    }

    public static DeferredItem<Item> registerHook(String hook) {
        Hook RUBY = (new Hook.HookBuilder("ruby")).setColor(ChatFormatting.RED).setDurabilityChance(0.5).build();
        Hook AMBER = (new Hook.HookBuilder("amber")).setColor(ChatFormatting.GOLD).setDurabilityChance(0.5).build();

        if (Objects.equals(hook, "ruby")) {
            DeferredItem<Item> hookItem = ModItems.ITEMS.register(RUBY.getName() + "_hook", () -> new HookItem(RUBY));
            Hook.HOOKS.put(RUBY.getName(), hookItem);
            return hookItem;

        } else if (Objects.equals(hook, "amber")) {
            DeferredItem<Item> hookItem = ModItems.ITEMS.register(AMBER.getName() + "_hook", () -> new HookItem(RUBY));
            Hook.HOOKS.put(AMBER.getName(), hookItem);
            return hookItem;
        }

        return null;
    }

    public static void registerAquaFishingRodCapability(RegisterCapabilitiesEvent event, Item item) {
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                (stack, context) -> new AquaFishingRodItem.FishingRodEquipmentHandler(stack),
                item
        );
    }

    public static void registerAquaFishingRodModelProperties(Item item) {
        ClientHandler.registerFishingRodModelProperties(item);
    }
}