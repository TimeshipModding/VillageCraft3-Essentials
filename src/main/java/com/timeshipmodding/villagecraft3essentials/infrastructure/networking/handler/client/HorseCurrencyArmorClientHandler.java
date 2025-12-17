package com.timeshipmodding.villagecraft3essentials.infrastructure.networking.handler.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HorseCurrencyArmorClientHandler {
    private static boolean serverSaysCanEquip = false;

    public static void setCanEquipState(boolean state) {
        serverSaysCanEquip = state;
    }

    public static boolean canEquip() {
        return serverSaysCanEquip;
    }
}