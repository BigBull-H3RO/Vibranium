//package de.bigbull.vibranium.entity.ai;
//
//import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.phys.Vec3;
//
//import java.util.EnumSet;
//import java.util.List;
//
//public class PushGoal extends Goal {
//    private final VibraGolemEntity entity;
//    private int pushCooldown;
//    private int pushCooldown2;
//
//    public PushGoal(VibraGolemEntity entity) {
//        this.entity = entity;
//        this.pushCooldown = 0;
//        this.pushCooldown2 = 0;
//        this.setFlags(EnumSet.noneOf(Goal.Flag.class));
//    }
//
//    @Override
//    public boolean canUse() {
//        return this.entity.getHealth() < this.entity.getMaxHealth();
//    }
//
//    @Override
//    public void tick() {
//        if (this.entity.hurtTime > 0 && this.entity.getLastDamageSource() != null && !(this.entity.getLastDamageSource().getDirectEntity() instanceof AbstractArrow)) {
//            if (pushCooldown == 0 && this.entity.getHealth() < this.entity.getMaxHealth() * 0.5 && this.entity.getHealth() > this.entity.getMaxHealth() * 0.25) {
//                pushNearbyEntities(1.5, 5);
//                pushCooldown = 400;
//            }
//            if (pushCooldown2 == 0 && this.entity.getHealth() < this.entity.getMaxHealth() * 0.25) {
//                pushNearbyEntities(3.0, 6);
//                pushCooldown2 = 400;
//            }
//        }
//        if (pushCooldown > 0) {
//            pushCooldown--;
//        }
//        if (pushCooldown2 > 0) {
//            pushCooldown2--;
//        }
//    }
//
//    private void pushNearbyEntities(double strength, double radius) {
//        int particleCount = 120;
//        double particleRadius = 4;
//        this.entity.startRage();
//        List<LivingEntity> entities = this.entity.level().getEntitiesOfClass(LivingEntity.class, this.entity.getBoundingBox().inflate(radius));
//
//        for (LivingEntity livingEntity : entities) {
//            if (livingEntity == this.entity || livingEntity instanceof VibraGolemEntity && ((VibraGolemEntity) livingEntity).getOwner() == this.entity.getOwner()) {
//                continue;
//            }
//            Vec3 direction = livingEntity.position().subtract(this.entity.position()).normalize().scale(strength);
//            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(direction.x, direction.y + 0.75, direction.z));
//            livingEntity.hurtMarked = true;
//        }
//
//        if (this.entity.level() instanceof ServerLevel serverLevel) {
//            for (int i = 0; i < particleCount; i++) {
//                double angleOffset = (2 * Math.PI / particleCount) * i;
//                double xParticleOffset = particleRadius * Math.cos(angleOffset);
//                double zParticleOffset = particleRadius * Math.sin(angleOffset);
//                serverLevel.sendParticles(ParticleTypes.CLOUD, this.entity.getX() + xParticleOffset, this.entity.getY() - 0.1, this.entity.getZ() + zParticleOffset, 1, 0.0, 0.05, 0.0, 0.05);
//            }
//        }
//        this.entity.level().playSound(null, this.entity.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1.0F, 1.0F);
//    }
//}