package de.bigbull.vibranium.init.item;

import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class VibraniumMaceItem extends DiggerItem {
    private float lastCalculatedDamage = 0.0F;

    public VibraniumMaceItem(Tier tier, TagKey<Block> tag, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (!player.isShiftKeyDown() || player.isCreative()) {
                if (isValidBlockForTool(state)) {
                    List<BlockPos> affectedPositions = getAffectedPositions(player, pos);

                    for (BlockPos targetPos : affectedPositions) {
                        BlockState targetState = level.getBlockState(targetPos);
                        if (isValidBlockForTool(targetState)) {
                            level.destroyBlock(targetPos, false);
                            Block.getDrops(targetState, (ServerLevel) level, targetPos, null, entity, stack)
                                    .forEach(drop -> Block.popResource(level, targetPos, drop));

                            stack.hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);

                            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                                break;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    public List<BlockPos> getAffectedPositions(Player player, BlockPos pos) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hitResult = getPlayerPOVHitResult(player.level(), player, ClipContext.Fluid.NONE);

        if (hitResult instanceof BlockHitResult) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos center = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            positions.add(center);

            switch (direction) {
                case UP, DOWN -> {
                    positions.add(center.north());
                    positions.add(center.south());
                    positions.add(center.east());
                    positions.add(center.west());
                    positions.add(center.north().east());
                    positions.add(center.north().west());
                    positions.add(center.south().east());
                    positions.add(center.south().west());
                }
                case NORTH, SOUTH -> {
                    positions.add(center.above());
                    positions.add(center.below());
                    positions.add(center.east());
                    positions.add(center.west());
                    positions.add(center.east().above());
                    positions.add(center.west().above());
                    positions.add(center.east().below());
                    positions.add(center.west().below());
                }
                case EAST, WEST -> {
                    positions.add(center.above());
                    positions.add(center.below());
                    positions.add(center.north());
                    positions.add(center.south());
                    positions.add(center.north().above());
                    positions.add(center.south().above());
                    positions.add(center.north().below());
                    positions.add(center.south().below());
                }
            }
        }
        return positions;
    }

    @Override
    public boolean canAttackBlock(BlockState p_333875_, Level p_333847_, BlockPos p_334073_, Player p_334042_) {
        return !p_334042_.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack p_334046_, LivingEntity p_333712_, LivingEntity p_333812_) {
        if (p_333812_ instanceof ServerPlayer serverplayer && canSmashAttack(serverplayer)) {
            ServerLevel serverlevel = (ServerLevel)p_333812_.level();
            if (serverplayer.isIgnoringFallDamageFromCurrentImpulse() && serverplayer.currentImpulseImpactPos != null) {
                if (serverplayer.currentImpulseImpactPos.y > serverplayer.position().y) {
                    serverplayer.currentImpulseImpactPos = serverplayer.position();
                }
            } else {
                serverplayer.currentImpulseImpactPos = serverplayer.position();
            }

            serverplayer.setIgnoreFallDamageFromCurrentImpulse(true);
            serverplayer.setDeltaMovement(serverplayer.getDeltaMovement().with(Direction.Axis.Y, 0.01F));
            serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
            if (p_333712_.onGround()) {
                serverplayer.setSpawnExtraParticlesOnFall(true);
                SoundEvent soundevent = serverplayer.fallDistance > 5.0F ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND;
                serverlevel.playSound(
                        null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), soundevent, serverplayer.getSoundSource(), 1.0F, 1.0F
                );
            } else {
                serverlevel.playSound(
                        null, serverplayer.getX(), serverplayer.getY(), serverplayer.getZ(), SoundEvents.MACE_SMASH_AIR, serverplayer.getSoundSource(), 1.0F, 1.0F
                );
            }

            knockback(serverlevel, serverplayer, p_333712_);
        }

        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack p_345716_, LivingEntity p_345817_, LivingEntity p_346003_) {
        p_345716_.hurtAndBreak(1, p_346003_, EquipmentSlot.MAINHAND);
        if (canSmashAttack(p_346003_)) {
            p_346003_.resetFallDistance();
        }
    }

    @Override
    public float getAttackDamageBonus(Entity targetEntity, float baseDamage, DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity livingentity) {
            if (!canSmashAttack(livingentity)) {
                return 0.0F;
            } else {
                float fallDistance = livingentity.fallDistance;
                float damage;

                if (fallDistance <= 3.0F) {
                    damage = 4.0F * fallDistance;
                } else if (fallDistance <= 8.0F) {
                    damage = 12.0F + 2.0F * (fallDistance - 3.0F);
                } else {
                    damage = 22.0F + fallDistance - 8.0F;
                }

                if (livingentity.level() instanceof ServerLevel serverlevel) {
                    damage += EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), targetEntity, damageSource, 0.0F) * fallDistance;
                }

                this.lastCalculatedDamage = damage;
                return damage;
            }
        } else {
            return 0.0F;
        }
    }

    private void knockback(Level level, Player player, Entity targetEntity) {
        level.levelEvent(2013, targetEntity.getOnPos(), 750);
        level.getEntitiesOfClass(LivingEntity.class, targetEntity.getBoundingBox().inflate(3.5), knockbackPredicate(player, targetEntity))
                .forEach(entity -> {
                    Vec3 vec3 = entity.position().subtract(targetEntity.position());
                    double knockbackPower = getKnockbackPower(player, entity, vec3);
                    Vec3 knockbackVec = vec3.normalize().scale(knockbackPower);
                    if (knockbackPower > 0.0) {
                        entity.push(knockbackVec.x, 0.7F, knockbackVec.z);
                        if (entity instanceof ServerPlayer serverplayer) {
                            serverplayer.connection.send(new ClientboundSetEntityMotionPacket(serverplayer));
                        }

                        Holder<DamageType> damageTypeHolder = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC);
                        DamageSource damageSource = new DamageSource(damageTypeHolder, player);
                        float reducedDamage = this.lastCalculatedDamage / 8.0F;
                        entity.hurt(damageSource, reducedDamage);
                    }
                });
    }

    private static Predicate<LivingEntity> knockbackPredicate(Player player, Entity entity) {
        return p_344407_ -> {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag6;
            label62: {
                flag = !p_344407_.isSpectator();
                flag1 = p_344407_ != player && p_344407_ != entity;
                flag2 = !player.isAlliedTo(p_344407_);
                if (p_344407_ instanceof TamableAnimal tamableanimal && tamableanimal.isTame() && player.getUUID().equals(tamableanimal.getOwnerUUID())) {
                    flag6 = true;
                    break label62;
                }

                flag6 = false;
            }

            boolean flag3;
            label55: {
                flag3 = !flag6;
                if (p_344407_ instanceof ArmorStand armorstand && armorstand.isMarker()) {
                    flag6 = false;
                    break label55;
                }

                flag6 = true;
            }

            boolean flag4 = flag6;
            boolean flag5 = entity.distanceToSqr(p_344407_) <= Math.pow(3.5, 2.0);
            return flag && flag1 && flag2 && flag3 && flag4 && flag5;
        };
    }

    private static double getKnockbackPower(Player player, LivingEntity livingEntity, Vec3 vec3) {
        return (3.5 - vec3.length())
                * 0.7F
                * (double)(player.fallDistance > 5.0F ? 2 : 1)
                * (1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }

    public static boolean canSmashAttack(LivingEntity p_344836_) {
        return p_344836_.fallDistance > 1.5F && !p_344836_.isFallFlying();
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_334031_, ItemStack p_334058_) {
        return p_334058_.is(ItemInit.VIBRANIUM_INGOT);
    }

    public boolean isValidBlockForTool(BlockState state) {
        return state.isSolidRender(null, BlockPos.ZERO) &&
                (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.is(BlockTags.MINEABLE_WITH_AXE)) &&
                state.getBlock() != Blocks.AIR;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.vibranium_mace.tooltip").withStyle(ChatFormatting.GRAY));
    }
}