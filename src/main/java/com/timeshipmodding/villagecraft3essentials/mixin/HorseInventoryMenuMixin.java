package com.timeshipmodding.villagecraft3essentials.mixin;

import com.timeshipmodding.villagecraft3essentials.interfacing.IHorseInventoryMenuEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.inventory.HorseInventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HorseInventoryMenu.class)
public abstract class HorseInventoryMenuMixin implements IHorseInventoryMenuEntity {
    @Shadow
    private final AbstractHorse horse;

    protected HorseInventoryMenuMixin(AbstractHorse horse) {
        this.horse = horse;
    }

    @Unique
    public AbstractHorse getEntity() {
        return this.horse;
    }
}