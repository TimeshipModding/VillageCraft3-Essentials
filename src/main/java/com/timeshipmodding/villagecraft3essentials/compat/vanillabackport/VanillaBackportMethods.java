package com.timeshipmodding.villagecraft3essentials.compat.vanillabackport;

import com.blackgear.platform.client.GameRendering;
import com.timeshipmodding.villagecraft3essentials.compat.vanillabackport.infrastructure.renderers.SpearRenderer;

public class VanillaBackportMethods {
    public static void itemLikeRendering(GameRendering.ItemLikeRenderingEvent event) {
        event.simple(SpearRenderer.INSTANCE, SpearRenderer.SPEARS);
    }
}