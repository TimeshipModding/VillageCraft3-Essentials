package com.timeshipmodding.villagecraft3essentials.content.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GripperCityPermitItem extends Item {
    public int pTooltip;

    public GripperCityPermitItem(int pTooltip, Properties pProperties) {
        super(pProperties);
        this.pTooltip = pTooltip;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (pTooltip == 0) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_0"));
        } else if (pTooltip == 1 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_1"));
        } else if (pTooltip == 1) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_1"));
        } else if (pTooltip == 2 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_2"));
        } else if (pTooltip == 2) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_2"));
        } else if (pTooltip == 3 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_3"));
        } else if (pTooltip == 3) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_3"));
        } else if (pTooltip == 4 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_4"));
        } else if (pTooltip == 4) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_4"));
        } else if (pTooltip == 5 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_5"));
        } else if (pTooltip == 5) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_5"));
        } else if (pTooltip == 6 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_6"));
        } else if (pTooltip == 6) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_6"));
        } else if (pTooltip == 7 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_7"));
        } else if (pTooltip == 7) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_7"));
        } else if (pTooltip == 8 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_8"));
        } else if (pTooltip == 8) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_8"));
        } else if (pTooltip == 9 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_9"));
        } else if (pTooltip == 9) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_9"));
        } else if (pTooltip == 10 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_10"));
        } else if (pTooltip == 10) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_10"));
        } else if (pTooltip == 11 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_11"));
        } else if (pTooltip == 11) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_11"));
        } else if (pTooltip == 12 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_12"));
        } else if (pTooltip == 12) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_12"));
        } else if (pTooltip == 13 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_13"));
        } else if (pTooltip == 13) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_13"));
        } else if (pTooltip == 14 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_14"));
        } else if (pTooltip == 14) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_14"));
        } else if (pTooltip == 15 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_15"));
        } else if (pTooltip == 15) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_15"));
        } else if (pTooltip == 16 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_16"));
        } else if (pTooltip == 16) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_16"));
        } else if (pTooltip == 17 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_17"));
        } else if (pTooltip == 17) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_17"));
        } else if (pTooltip == 18 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_18"));
        } else if (pTooltip == 18) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_18"));
        } else if (pTooltip == 19 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_19"));
        } else if (pTooltip == 19) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_19"));
        } else if (pTooltip == 20 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_20"));
        } else if (pTooltip == 20) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_20"));
        } else if (pTooltip == 21 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_21"));
        } else if (pTooltip == 21) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_21"));
        } else if (pTooltip == 22 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_22"));
        } else if (pTooltip == 22) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_22"));
        } else if (pTooltip == 23 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_23"));
        } else if (pTooltip == 23) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_23"));
        } else if (pTooltip == 24 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_24"));
        } else if (pTooltip == 24) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_24"));
        } else if (pTooltip == 25 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_25"));
        } else if (pTooltip == 25) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_25"));
        } else if (pTooltip == 26 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_26"));
        } else if (pTooltip == 26) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_26"));
        } else if (pTooltip == 27 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_27"));
        } else if (pTooltip == 27) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_27"));
        } else if (pTooltip == 28 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_28"));
        } else if (pTooltip == 28) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_28"));
        } else if (pTooltip == 29 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_29"));
        } else if (pTooltip == 29) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_29"));
        } else if (pTooltip == 30 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_30"));
        } else if (pTooltip == 30) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_30"));
        } else if (pTooltip == 31 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_31"));
        } else if (pTooltip == 31) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_31"));
        } else if (pTooltip == 32 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_32"));
        } else if (pTooltip == 32) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_32"));
        } else if (pTooltip == 33 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_33"));
        } else if (pTooltip == 33) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_33"));
        } else if (pTooltip == 34 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_34"));
        } else if (pTooltip == 34) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_34"));
        } else if (pTooltip == 35 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_35"));
        } else if (pTooltip == 35) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_35"));
        } else if (pTooltip == 36 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_36"));
        } else if (pTooltip == 36) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_36"));
        } else if (pTooltip == 37 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_37"));
        } else if (pTooltip == 37) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_37"));
        } else if (pTooltip == 38 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_38"));
        } else if (pTooltip == 38) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_38"));
        } else if (pTooltip == 39 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_39"));
        } else if (pTooltip == 39) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_39"));
        } else if (pTooltip == 40 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_40"));
        } else if (pTooltip == 40) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_40"));
        } else if (pTooltip == 41 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_41"));
        } else if (pTooltip == 41) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_41"));
        } else if (pTooltip == 42 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_42"));
        } else if (pTooltip == 42) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_42"));
        } else if (pTooltip == 43 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_43"));
        } else if (pTooltip == 43) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_43"));
        } else if (pTooltip == 44 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_44"));
        } else if (pTooltip == 44) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_44"));
        } else if (pTooltip == 45 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_45"));
        } else if (pTooltip == 45) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_45"));
        } else if (pTooltip == 46 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_46"));
        } else if (pTooltip == 46) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_46"));
        } else if (pTooltip == 47 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_47"));
        } else if (pTooltip == 47) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_47"));
        } else if (pTooltip == 48 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_48"));
        } else if (pTooltip == 48) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_48"));
        } else if (pTooltip == 49 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_49"));
        } else if (pTooltip == 49) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_49"));
        } else if (pTooltip == 50 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_50"));
        } else if (pTooltip == 50) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_50"));
        } else if (pTooltip == 51 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_51"));
        } else if (pTooltip == 51) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_51"));
        } else if (pTooltip == 52 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_50"));
        } else if (pTooltip == 52) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_52"));
        } else if (pTooltip == 53 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_53"));
        } else if (pTooltip == 53) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_53"));
        } else if (pTooltip == 54 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_54"));
        } else if (pTooltip == 54) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_54"));
        } else if (pTooltip == 55 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_50"));
        } else if (pTooltip == 55) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_50"));
        } else if (pTooltip == 56 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_56"));
        } else if (pTooltip == 56) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_56"));
        } else if (pTooltip == 57 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_57"));
        } else if (pTooltip == 57) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_57"));
        } else if (pTooltip == 58 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_58"));
        } else if (pTooltip == 58) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_58"));
        } else if (pTooltip == 59 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_59"));
        } else if (pTooltip == 59) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_59"));
        } else if (pTooltip == 60 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_60"));
        } else if (pTooltip == 60) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_60"));
        } else if (pTooltip == 61 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_61"));
        } else if (pTooltip == 61) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_61"));
        } else if (pTooltip == 62 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_62"));
        } else if (pTooltip == 62) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_62"));
        } else if (pTooltip == 63 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_63"));
        } else if (pTooltip == 63) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_63"));
        } else if (pTooltip == 64 && Screen.hasShiftDown()) {pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.grippercity_permit_tooltip_value_64"));
        } else if (pTooltip == 64) {
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_shift"));
            pTooltipComponents.add(Component.translatable("tooltip.villagecraft3essentials.permit_tooltip_64"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}