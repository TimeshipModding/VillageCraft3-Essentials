package com.timeshipmodding.villagecraft3essentials.content.item.roleplayitem;

import io.wispforest.accessories.api.AccessoryItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

public class AccessoryRoleplayItem extends AccessoryItem {
    private final String town;
    private ChatFormatting itemNameColour;

    public AccessoryRoleplayItem(String town, Properties properties) {
        super(properties);
        this.town = town;
    }

    @Override
    public Component getName(ItemStack stack) {
        switch (town) {
            case "villagecraftcity":
                itemNameColour = ChatFormatting.RED;
                break;
            case "oasis":
                itemNameColour = ChatFormatting.DARK_GREEN;
                break;
            case "swedishfurniturestore":
                itemNameColour = ChatFormatting.BLUE;
                break;
        }

        return Component.translatable(this.getDescriptionId()).setStyle(Style.EMPTY.withColor(itemNameColour));
    }
}