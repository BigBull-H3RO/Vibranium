package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Vibranium.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_VIBRANIUM = PARTICLES.register("dripping_vibranium", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_VIBRANIUM = PARTICLES.register("falling_vibranium", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_VIBRANIUM = PARTICLES.register("landing_vibranium", () -> new SimpleParticleType(false));
}
