package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.HSHEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Vibranium.MODID);

    public static final Holder<MobEffect> KINETIC_REDISTRIBUTION = EFFECTS.register("kinetic_redistribution",
            () -> new HSHEffect(MobEffectCategory.BENEFICIAL, 0x181877));
}