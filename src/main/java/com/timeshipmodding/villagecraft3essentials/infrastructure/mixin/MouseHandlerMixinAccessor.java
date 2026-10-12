package com.timeshipmodding.villagecraft3essentials.infrastructure.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MouseHandler.class)
public interface MouseHandlerMixinAccessor {
    @Accessor("xpos")
    void setXPos(double x);

    @Accessor("ypos")
    void setYPos(double y);
}