package com.timeshipmodding.villagecraft3essentials.content.blockitem.roleplayblockitem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class RoleplayBlockItem extends BlockItem {
    private final String town;
    private ChatFormatting itemNameColour;

    public RoleplayBlockItem(String town, Block block, Properties properties) {
        super(block, properties);
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