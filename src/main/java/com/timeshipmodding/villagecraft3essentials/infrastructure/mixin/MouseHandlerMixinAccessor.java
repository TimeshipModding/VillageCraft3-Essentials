package com.timeshipmodding.villagecraft3essentials.infrastructure.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MouseHandler.class)
public interface MouseHandlerMixinAccessor {
    @Accessor("xpos")
    void setXpos(double x);

    @Accessor("ypos")
    void setYpos(double y);
}