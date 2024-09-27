package de.bigbull.vibranium.init.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HSHEffect extends MobEffect {
    private static final float DAMAGE_THRESHOLD = 10.0F;
    private final Map<Player, Float> damageTracker = new HashMap<>();
    private static final float PUSH_DAMAGE = 5.0F;

    public HSHEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onMobHurt(LivingEntity entity, int amplifier, DamageSource source, float damage) {
        if (entity instanceof Player player) {
            accumulateDamage(player, damage);

            if (damageTracker.getOrDefault(player, 0.0F) >= DAMAGE_THRESHOLD) {
                applyPushEffect(player, 1.5 + amplifier * 0.5, 5.0 + amplifier * 1.0);

                damageTracker.put(player, 0.0F);
            }
        }
    }

    private void accumulateDamage(Player player, float damage) {
        float accumulatedDamage = damageTracker.getOrDefault(player, 0.0F);

        accumulatedDamage += damage;

        damageTracker.put(player, accumulatedDamage);
    }

    private void applyPushEffect(Player player, double strength, double radius) {
        int particleCount = 120;
        double particleRadius = 3.0;

        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius));

        for (LivingEntity entity : entities) {
            if (entity == player) continue;

            Vec3 direction = entity.position().subtract(player.position()).normalize().scale(strength);
            entity.setDeltaMovement(entity.getDeltaMovement().add(direction.x, 0.5, direction.z));
            entity.hurtMarked = true;

            DamageSource pushDamageSource = player.damageSources().playerAttack(player);
            entity.hurt(pushDamageSource, PUSH_DAMAGE);
        }

        // Partikel und Sounds
        if (player.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < particleCount; i++) {
                double angleOffset = (2 * Math.PI / particleCount) * i;
                double xParticleOffset = particleRadius * Math.cos(angleOffset);
                double zParticleOffset = particleRadius * Math.sin(angleOffset);
                serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX() + xParticleOffset, player.getY() - 0.1, player.getZ() + zParticleOffset, 1, 0.0, 0.05, 0.0, 0.05);
            }
        }
        player.level().playSound(null, player.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}