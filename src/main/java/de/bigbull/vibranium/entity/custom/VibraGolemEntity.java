//Alpha Version it's not finished yet

package de.bigbull.vibranium.entity.custom;

import de.bigbull.vibranium.entity.ai.DefensiveGoal;
import de.bigbull.vibranium.entity.ai.PushGoal;
import de.bigbull.vibranium.entity.ai.RegenerationGoal;
import de.bigbull.vibranium.entity.ai.VibraGolemAttackGoal;
import de.bigbull.vibranium.entity.client.Crackniess.VibraCrackiness;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class VibraGolemEntity extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(VibraGolemEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(VibraGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DEFENSIVE_MODE = SynchedEntityData.defineId(VibraGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<BlockPos> DEFENSIVE_POSITION = SynchedEntityData.defineId(VibraGolemEntity.class, EntityDataSerializers.BLOCK_POS);
    private UUID ownerUUID;
    private DamageSource lastDamageSource;
    private boolean isRaging = false;
    private int particlecounter = 10;

    public VibraGolemEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    public int attackAnimationTimeout = 0;

    public static AttributeSupplier.Builder setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.FOLLOW_RANGE, 30.0)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.ATTACK_SPEED, 0.4)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.4)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new VibraGolemAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !VibraGolemEntity.this.isDefensiveMode();
            }
        });
        this.goalSelector.addGoal(4, new DefensiveGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F));
        this.goalSelector.addGoal(7, new PushGoal(this));
        this.goalSelector.addGoal(5, new RegenerationGoal(this));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationState();
        }
    }

    private void setupAnimationState() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float particalTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(particalTick * 6f, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setSitting(tag.getBoolean("Sitting"));
        this.setDefensiveMode(tag.getBoolean("DefensiveMode"), BlockPos.of(tag.getLong("DefensivePosition")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Sitting", this.isSitting());
        tag.putBoolean("DefensiveMode", this.isDefensiveMode());
        tag.putLong("DefensivePosition", this.getDefensivePosition().asLong());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
        builder.define(SITTING, false);
        builder.define(DEFENSIVE_MODE, false);
        builder.define(DEFENSIVE_POSITION, BlockPos.ZERO);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.level().broadcastEntityEvent(this, (byte) 4);

        float damage = this.getAttackDamage();
        float actualDamage = (int) damage > 0 ? damage / 2.0F + this.random.nextInt((int) damage) : damage;
        DamageSource damageSource = this.damageSources().mobAttack(this);

        boolean success = entity.hurt(damageSource, actualDamage);  // Schaden zufügen
        if (success) {
            double knockbackResistance = entity instanceof LivingEntity livingEntity ? livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
            double knockback = Math.max(0.0, 1.0 - knockbackResistance);
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4F * knockback, 0.0));
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);  // Sound abspielen
        }

        return success;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float pAmount) {
        this.lastDamageSource = damageSource;
        VibraCrackiness.Level vibraCrackiness$level = this.getCrackiness();
        boolean flag = super.hurt(damageSource, pAmount);
        if (flag && this.getCrackiness() != vibraCrackiness$level) {
            this.playSound(SoundEvents.WOLF_ARMOR_DAMAGE, 1.0F, 1.0F);
        }
        if (damageSource.getDirectEntity() instanceof AbstractArrow arrow) {
            arrow.setDeltaMovement(arrow.getDeltaMovement().scale(-0.1));
            arrow.setBaseDamage(0.0);
            this.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 1.0F);
            return super.hurt(damageSource, pAmount * 0.8f);
        }
        if (damageSource.is(DamageTypes.PLAYER_EXPLOSION) || damageSource.is(DamageTypes.EXPLOSION)) {
            return super.hurt(damageSource, pAmount * 0.2f);
        }
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            if (!this.level().isClientSide) {
                this.setSitting(false);
            }
            return flag;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isDefensiveMode()) {
            particlecounter++;
            if (particlecounter > 10 && this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.END_ROD, this.getX(), this.getY() + 4.0D, this.getZ(), 0.0D, 0.0D, 0.0D);
                particlecounter = 0;
            }
        } else {
            particlecounter = 10;
        }
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 2.5000003E-7F && this.random.nextInt(5) == 0;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else if (id == 5) {
            this.isRaging = true;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        Item TamingItem = ItemInit.VIBRANIUM_CORE.get();
        Item HealingItem = ItemInit.VIBRANIUM_INGOT.get();

        if (this.isTame()) {
            if (this.isOwnedBy(player) && !this.level().isClientSide() && hand == InteractionHand.MAIN_HAND) {
                if (item == HealingItem && this.getHealth() < this.getMaxHealth()) {
                    this.usePlayerItem(player, hand, itemstack);
                    itemstack.consume(1, player);
                    this.heal(25.0F);
                } else if (player.isShiftKeyDown()) {
                    this.setDefensiveMode(!this.isDefensiveMode(), this.blockPosition());
                    return InteractionResult.SUCCESS;
                } else {
                    InteractionResult interactionresult = super.mobInteract(player, hand);
                    if (!interactionresult.consumesAction()) {
                        this.setSitting(!this.isSitting());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        return InteractionResult.SUCCESS_NO_ITEM_USED;
                    } else {
                        return interactionresult;
                    }
                }
            }
            return super.mobInteract(player, hand);
        } else if (item == TamingItem) {
            if (!this.level().isClientSide) {
                itemstack.consume(1, player);
                this.usePlayerItem(player, hand, itemstack);
                this.tryToTame(player);
                return InteractionResult.SUCCESS;
            } else {
                return super.mobInteract(player, hand);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean pboolean) {
        super.dropCustomDeathLoot(level, damageSource, pboolean);
        ItemStack itemStack = ItemInit.VIBRANIUM_INGOT.toStack();
        if (!itemStack.isEmpty()) {
            this.spawnAtLocation(itemStack);
        }
    }

    private void tryToTame(Player player) {
        if (this.random.nextInt(3) == 0  && !net.neoforged.neoforge.event.EventHooks.onAnimalTame(this, player)) {
            this.tame(player);
            this.navigation.stop();
            this.level().broadcastEntityEvent(this, (byte)7);
        } else {
            this.level().broadcastEntityEvent(this, (byte)6);
        }
    }

    @Override
    protected void usePlayerItem(Player player, InteractionHand hand, ItemStack itemStack) {
        if (itemStack.getItem() == ItemInit.VIBRANIUM_INGOT.get()) {
            this.playSound(SoundEvents.NETHERITE_BLOCK_HIT, 1.0F, 1.0F);
        }
        if (itemStack.getItem() == ItemInit.VIBRANIUM_CORE.get()) {
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
        }
        super.usePlayerItem(player, hand, itemStack);
    }

    @Override
    public boolean wantsToAttack(LivingEntity entity, LivingEntity entity1) {
        if (entity instanceof Creeper || entity instanceof Ghast || entity instanceof ArmorStand) {
            return false;
        } else if (entity instanceof VibraGolemEntity vibraGolem) {
            return !vibraGolem.isTame() || vibraGolem.getOwner() != entity1;
        } else {
            if (entity instanceof Player player && entity1 instanceof Player player1 && !player1.canHarmPlayer(player)) {
                return false;
            }
            if (entity instanceof AbstractHorse abstracthorse && abstracthorse.isTamed()) {
                return false;
            }
            return !(entity instanceof TamableAnimal tamableanimal) || !tamableanimal.isTame();
        }
    }

    public boolean isDefensiveMode() {
        return this.entityData.get(DEFENSIVE_MODE);
    }

    public void setDefensiveMode(boolean defensiveMode, BlockPos position) {
        this.entityData.set(DEFENSIVE_MODE, defensiveMode);
        this.entityData.set(DEFENSIVE_POSITION, position);
    }

    public BlockPos getDefensivePosition() {
        return this.entityData.get(DEFENSIVE_POSITION);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
        this.setOrderedToSit(sitting);
    }
    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }
    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public DamageSource getLastDamageSource() {
        return this.lastDamageSource;
    }

    public VibraCrackiness.Level getCrackiness() {
        return VibraCrackiness.VIBRA_GOLEM.byFraction(this.getHealth() / this.getMaxHealth());
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.HOSTILE_SWIM;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }
    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 0.15F, 1.0F);
    }
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
    @Override
    public void tryToTeleportToOwner() {
    }

    public void startRage() {
        this.isRaging = true;
        this.level().broadcastEntityEvent(this, (byte)5);
    }
}