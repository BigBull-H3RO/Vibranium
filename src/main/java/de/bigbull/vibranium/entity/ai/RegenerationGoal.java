package de.bigbull.vibranium.entity.ai;

import de.bigbull.vibranium.entity.VibraGolemEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class RegenerationGoal extends Goal {
    private final VibraGolemEntity entity;
    private int regenCounter;

    public RegenerationGoal(VibraGolemEntity entity) {
        this.entity = entity;
        this.regenCounter = 0;
        this.setFlags(EnumSet.noneOf(Goal.Flag.class));
    }

    @Override
    public boolean canUse() {
        return this.entity.getHealth() < this.entity.getMaxHealth();
    }

    @Override
    public void tick() {
        if (this.entity.getHealth() < this.entity.getMaxHealth()) {
            regenCounter++;
            if (regenCounter > 80) {
                regeneration(2.0f, ParticleTypes.HEART);
                regenCounter = 0;
            }
        }
    }

    private void regeneration(float healAmount, SimpleParticleType particle) {
        this.entity.heal(healAmount);
        if (this.entity.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 7; i++) {
                double d0 = this.entity.getRandom().nextGaussian() * 0.02;
                double d1 = this.entity.getRandom().nextGaussian() * 0.02;
                double d2 = this.entity.getRandom().nextGaussian() * 0.02;
                serverLevel.sendParticles(particle, this.entity.getRandomX(1.0), this.entity.getRandomY() + 0.5, this.entity.getRandomZ(1.0), 1, d0, d1, d2, 0.05);
            }
        }
    }
}