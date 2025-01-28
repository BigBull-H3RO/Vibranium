package de.bigbull.vibranium.init.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HSHEffect extends MobEffect {
    private final Map<Player, Float> damageTracker = new HashMap<>();
    private static final float DAMAGE_THRESHOLD = 10.0F;
    private static final float BASE_PUSH_DAMAGE = 4.0F;
    private static final double BASE_PUSH_RADIUS = 2.5F;
    private static final double BASE_PUSH_STRENGTH = 1.5;

    public HSHEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onMobHurt(ServerLevel level, LivingEntity entity, int amplifier, DamageSource source, float damage) {
        if (entity instanceof Player player) {
            if (player.isDeadOrDying()) {
                damageTracker.remove(player);
                return;
            }

            if (!(source.getEntity() instanceof LivingEntity)) {
                return;
            }

            float baseDamage = (float) player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
            float adjustedDamage = Math.max(damage, baseDamage / 2);

            accumulateDamage(player, adjustedDamage);

            if (damageTracker.getOrDefault(player, 0.0F) >= DAMAGE_THRESHOLD) {
                double pushStrength = BASE_PUSH_STRENGTH + amplifier * 0.5;
                double pushRadius = BASE_PUSH_RADIUS + amplifier * 2.5;
                float pushDamage = (float) (BASE_PUSH_DAMAGE + amplifier * 2.0);

                applyPushEffect(player, pushStrength, pushRadius, pushDamage);
                damageTracker.put(player, 0.0F);
            }
        }
    }

    private void accumulateDamage(Player player, float damage) {
        float accumulatedDamage = damageTracker.getOrDefault(player, 0.0F);
        accumulatedDamage += damage;
        damageTracker.put(player, accumulatedDamage);
    }

    private void applyPushEffect(Player player, double strength, double radius, float damage) {
        int particleCount = 120;

        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius));

        for (LivingEntity entity : entities) {
            if (entity == player) continue;

            Vec3 direction = entity.position().subtract(player.position()).normalize().scale(strength);
            entity.setDeltaMovement(entity.getDeltaMovement().add(direction.x, 0.5, direction.z));
            entity.hurtMarked = true;

            DamageSource pushDamageSource = player.damageSources().playerAttack(player);

            if (entity.level() instanceof ServerLevel serverLevel) {
                entity.hurtServer(serverLevel, pushDamageSource, damage);
            }
        }

        if (player.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < particleCount; i++) {
                double angleOffset = (2 * Math.PI / particleCount) * i;
                double xParticleOffset = radius * Math.cos(angleOffset);
                double zParticleOffset = radius * Math.sin(angleOffset);
                serverLevel.sendParticles(ParticleTypes.CLOUD, player.getX() + xParticleOffset, player.getY() - 0.1, player.getZ() + zParticleOffset, 1, 0.0, 0.05, 0.0, 0.05);
            }
        }

        player.level().playSound(null, player.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}