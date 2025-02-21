package de.bigbull.vibranium.init.custom.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class VibraniumMaceItem extends DiggerItem {
    private final ToolMaterial material;
    private float lastCalculatedDamage = 0.0F;

    public static final Component TOOLTIP = Component.translatable("item.vibranium_mace.tooltip").withStyle(ChatFormatting.GRAY);

    public VibraniumMaceItem(ToolMaterial material, TagKey<Block> tag, float attackDamage, float attackSpeed, Properties properties) {
        super(material, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, properties);
        this.material = material;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, Player player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if(traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                }
            }
        }

        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
        }

        if(traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                }
            }
        }
        return positions;
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity entity1) {
        if (canSmashAttack(entity1)) {
            ServerLevel serverlevel = (ServerLevel)entity1.level();
            entity1.setDeltaMovement(entity1.getDeltaMovement().with(Direction.Axis.Y, 0.01F));
            if (entity1 instanceof ServerPlayer serverplayer) {
                serverplayer.currentImpulseImpactPos = this.calculateImpactPosition(serverplayer);
                serverplayer.setIgnoreFallDamageFromCurrentImpulse(true);
                serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
            }

            if (entity.onGround()) {
                if (entity1 instanceof ServerPlayer serverplayer1) {
                    serverplayer1.setSpawnExtraParticlesOnFall(true);
                }

                SoundEvent soundevent = entity1.fallDistance > 5.0F ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND;
                serverlevel.playSound(null, entity1.getX(), entity1.getY(), entity1.getZ(), soundevent, entity1.getSoundSource(), 1.0F, 1.0F);
            } else {
                serverlevel.playSound(
                        null, entity1.getX(), entity1.getY(), entity1.getZ(), SoundEvents.MACE_SMASH_AIR, entity1.getSoundSource(), 1.0F, 1.0F
                );
            }

            knockback(serverlevel, entity1, entity);
        }

        return true;
    }

    private Vec3 calculateImpactPosition(ServerPlayer p_365384_) {
        return p_365384_.isIgnoringFallDamageFromCurrentImpulse()
                && p_365384_.currentImpulseImpactPos != null
                && p_365384_.currentImpulseImpactPos.y <= p_365384_.position().y
                ? p_365384_.currentImpulseImpactPos
                : p_365384_.position();
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity entity1) {
        itemStack.hurtAndBreak(1, entity1, EquipmentSlot.MAINHAND);
        if (canSmashAttack(entity1)) {
            entity1.resetFallDistance();
        }
    }

    @Override
    public float getAttackDamageBonus(Entity entity, float v, DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity livingentity) {
            if (!canSmashAttack(livingentity)) {
                return 0.0F;
            } else {
                float f3 = 3.0F;
                float f = 8.0F;
                float f1 = livingentity.fallDistance;
                float damage;
                if (f1 <= 3.0F) {
                    damage = 4.0F * f1;
                } else if (f1 <= 8.0F) {
                    damage = 12.0F + 2.0F * (f1 - 3.0F);
                } else {
                    damage = 22.0F + f1 - 8.0F;
                }

                lastCalculatedDamage = damage;

                return livingentity.level() instanceof ServerLevel serverlevel
                        ? damage + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), entity, damageSource, 0.0F) * f1
                        : damage;
            }
        } else {
            lastCalculatedDamage = 0.0F;
            return 0.0F;
        }
    }

    private void knockback(Level level, Entity entity, Entity entity1) {
        level.levelEvent(2013, entity1.getOnPos(), 750);
        level.getEntitiesOfClass(LivingEntity.class, entity1.getBoundingBox().inflate(3.5), knockbackPredicate(entity, entity1))
                .forEach(target -> {
                    Vec3 vec3 = target.position().subtract(entity1.position());
                    double d0 = getKnockbackPower(entity, target, vec3);
                    Vec3 vec31 = vec3.normalize().scale(d0);
                    if (d0 > 0.0) {
                        target.push(vec31.x, 0.7F, vec31.z);

                        if (lastCalculatedDamage > 0.0F) {
                            float pushDamage = lastCalculatedDamage / 6.0F;
                            target.hurtServer((ServerLevel) level, level.damageSources().mace(entity), pushDamage);
                        }

                        if (target instanceof ServerPlayer serverplayer) {
                            serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                        }
                    }
                });
    }

    private static Predicate<LivingEntity> knockbackPredicate(Entity entity, Entity entity1) {
        return livingEntity -> {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag6;
            label62: {
                flag = !livingEntity.isSpectator();
                flag1 = livingEntity != entity && livingEntity != entity1;
                flag2 = !entity.isAlliedTo(livingEntity);
                if (livingEntity instanceof TamableAnimal tamableanimal && tamableanimal.isTame() && entity.getUUID().equals(tamableanimal.getOwnerUUID())) {
                    flag6 = true;
                    break label62;
                }

                flag6 = false;
            }

            boolean flag3;
            label55: {
                flag3 = !flag6;
                if (livingEntity instanceof ArmorStand armorstand && armorstand.isMarker()) {
                    flag6 = false;
                    break label55;
                }

                flag6 = true;
            }

            boolean flag4 = flag6;
            boolean flag5 = entity1.distanceToSqr(livingEntity) <= Math.pow(3.5, 2.0);
            return flag && flag1 && flag2 && flag3 && flag4 && flag5;
        };
    }

    private static double getKnockbackPower(Entity entity, LivingEntity entity1, Vec3 vec3) {
        return (3.5 - vec3.length())
                * 0.7F
                * (double)(entity.fallDistance > 5.0F ? 2 : 1)
                * (1.0 - entity1.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }

    public static boolean canSmashAttack(LivingEntity entity) {
        return entity.fallDistance > 1.5F && !entity.isFallFlying();
    }

    @Nullable
    @Override
    public DamageSource getDamageSource(LivingEntity entity) {
        return canSmashAttack(entity) ? entity.damageSources().mace(entity) : super.getDamageSource(entity);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        float baseSpeed = material.speed();

        if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return baseSpeed * 0.1005F;
        }
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return baseSpeed * 0.3F;
        }
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return baseSpeed * 0.5F;
        }
        return baseSpeed;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(TOOLTIP);
    }
}