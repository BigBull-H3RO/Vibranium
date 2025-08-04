package de.bigbull.vibranium.event;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.config.ServerConfig;
import de.bigbull.vibranium.entity.VibraGolemEntity;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.EnchantmentInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Vibranium.MODID)
public class ModGameEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // This event is called when a player breaks a block with the Vibranium Mace
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        Level level = event.getPlayer().level();
        BlockState middleBlockState = event.getLevel().getBlockState(event.getPos());

        if (mainHandItem.getItem() instanceof VibraniumMaceItem && player instanceof ServerPlayer serverPlayer) {
            if (!player.isShiftKeyDown()) {
                BlockPos initialBlockPos = event.getPos();
                TagKey<Block> requiredTool = getRequiredToolForBlock(middleBlockState);

                if (requiredTool != null && isValidBlockForTool(middleBlockState, requiredTool)) {
                    boolean middleBlockNeedsAdvancedTool = middleBlockState.is(BlockTags.NEEDS_DIAMOND_TOOL) || middleBlockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL);

                    if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                        return;
                    }

                    List<BlockPos> affectedPositions = VibraniumMaceItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer);
                    affectedPositions.remove(initialBlockPos);

                    Holder<Enchantment> universalBreakerEnchantmentHolder = EnchantmentInit.UNIVERSAL_BREAKER;
                    int enchantmentLevel = mainHandItem.getEnchantmentLevel(universalBreakerEnchantmentHolder);
                    boolean hasUniversalBreaker = enchantmentLevel > 0;

                    for (BlockPos pos : affectedPositions) {
                        BlockState targetBlockState = level.getBlockState(pos);

                        if (hasUniversalBreaker || ((middleBlockNeedsAdvancedTool && isValidBlockForTool(targetBlockState, requiredTool)) ||
                                (!middleBlockNeedsAdvancedTool && !targetBlockState.is(BlockTags.NEEDS_DIAMOND_TOOL) && !targetBlockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL) && isValidBlockForTool(targetBlockState, requiredTool)))) {
                            HARVESTED_BLOCKS.add(pos);

                            if (ServerConfig.USE_FAST_MODE.get()) {
                                serverPlayer.gameMode.destroyBlock(pos);
                            } else {
                                level.destroyBlock(pos, false);
                                Block.getDrops(targetBlockState, (ServerLevel) level, pos, null, player, mainHandItem)
                                        .forEach(drop -> Block.popResource(level, pos, drop));
                                mainHandItem.hurtAndBreak(1, serverPlayer, EquipmentSlot.MAINHAND);
                            }
                            HARVESTED_BLOCKS.remove(pos);
                        }
                    }
                }
            }
        }
    }

    // This event is called when a player right-clicks a block with Raw Vibranium
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState blockState = level.getBlockState(pos);
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == ItemInit.RAW_VIBRANIUM.get()) {
            boolean transformed = false;
            if (blockState.is(Blocks.DIRT)) {
                level.setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), 3);
                transformed = true;
            } else if (blockState.is(Blocks.FARMLAND)) {
                int moisture = blockState.getValue(FarmBlock.MOISTURE);
                BlockState enrichedFarmlandState = BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get()
                        .defaultBlockState()
                        .setValue(FarmBlock.MOISTURE, moisture);

                level.setBlock(pos, enrichedFarmlandState, 3);
                transformed = true;
            }

            if (transformed) {
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                level.playSound(null, pos, SoundEvents.MUD_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }

    // This event is called when an Arrow hits a Vibranium Shield
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        HitResult hitResult = event.getRayTraceResult();
        Projectile projectile = event.getProjectile();

        if (hitResult.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) hitResult;
            Entity targetEntity = entityHitResult.getEntity();

            if (targetEntity instanceof Player player && player.isBlocking()) {
                ItemStack activeItem = player.getUseItem();

                if (activeItem.getItem() == ItemInit.VIBRANIUM_SHIELD.get()) {
                    if (projectile instanceof Arrow arrow && isProjectileInFront(player, arrow)) {
                        event.setCanceled(false);
                        shootArrowBack(player, arrow);
                    }
                }
            }
        }
    }

    // This event is called when a LivingEntity attacks a Player with a Vibranium Shield
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity() instanceof Player player && player.isBlocking()) {
            ItemStack activeItem = player.getUseItem();

            if (activeItem.getItem() == ItemInit.VIBRANIUM_SHIELD.get()) {
                Entity attacker = event.getSource().getDirectEntity();

                if (attacker instanceof LivingEntity && isAttackInFront(player, attacker)) {
                    knockbackAttacker(player, (LivingEntity) attacker);
                    event.getContainer().setNewDamage(0.0F);
                }
            }
        }
    }

    // This event is called when a Player breaks a Deppslate Vibranium Ore block
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState blockState = event.getState();
        Player player = event.getPlayer();

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        if (blockState.is(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get()) && !player.isCreative()) {
            List<VibraGolemEntity> golems = level.getEntitiesOfClass(VibraGolemEntity.class, player.getBoundingBox().inflate(20));
            for (VibraGolemEntity vibraGolem : golems) {
                if (!vibraGolem.isTame()) {
                    vibraGolem.setTarget(player);
                    vibraGolem.setAggressive(true);
                }
            }
        }
    }

    // This event is called when a Player has Raw Vibranium in their inventory
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (!(player.level() instanceof ServerLevel level)) {
            return;
        }
        boolean hasRawVibranium = false;

        if (!player.isCreative()) {
            for (ItemStack itemStack : player.getInventory()) {
                if (itemStack.getItem() == ItemInit.RAW_VIBRANIUM.get()) {
                    hasRawVibranium = true;
                    break;
                }
            }
            if (hasRawVibranium) {
                List<VibraGolemEntity> golems = level.getEntitiesOfClass(VibraGolemEntity.class, player.getBoundingBox().inflate(20));
                for (VibraGolemEntity vibraGolem : golems) {
                    if (!vibraGolem.isTame()) {
                        vibraGolem.setTarget(player);
                        vibraGolem.setAggressive(true);
                    }
                }
            }
        }

        if (!player.isEyeInFluid(FluidTags.WATER) && isEquipped(player, ItemInit.VIBRANIUM_TURTLE_HELMET.get())) {
            vibraniumTurtleHelmetTick(player);
        }
    }

    private static TagKey<Block> getRequiredToolForBlock(BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return BlockTags.MINEABLE_WITH_PICKAXE;
        }
        if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return BlockTags.MINEABLE_WITH_SHOVEL;
        }
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return BlockTags.MINEABLE_WITH_AXE;
        }
        return null;
    }

    public static boolean isValidBlockForTool(BlockState state, TagKey<Block> requiredTool) {
        return state.isSolidRender() &&
                state.is(requiredTool) &&
                state.getBlock() != Blocks.AIR;
    }

    private static boolean isProjectileInFront(Player player, Projectile projectile) {
        Vec3 playerToProjectile = projectile.position().subtract(player.position()).normalize();
        Vec3 playerLookDirection = player.getLookAngle().normalize();
        double dotProduct = playerLookDirection.dot(playerToProjectile);

        return dotProduct > 0;
    }

    private static boolean isAttackInFront(Player player, Entity attacker) {
        Vec3 playerToAttacker = attacker.position().subtract(player.position()).normalize();
        Vec3 playerLookDirection = player.getLookAngle().normalize();
        double dotProduct = playerLookDirection.dot(playerToAttacker);

        return dotProduct > 0;
    }

    private static void shootArrowBack(Player player, Arrow originalArrow) {
        Arrow newArrow = new Arrow(EntityType.ARROW, player.level());

        newArrow.setCritArrow(originalArrow.isCritArrow());
        newArrow.setRemainingFireTicks(originalArrow.getRemainingFireTicks());
        newArrow.setOwner(player);
        newArrow.setPos(originalArrow.getX(), originalArrow.getY(), originalArrow.getZ());

        Vec3 lookDirection = player.getLookAngle();
        Vec3 adjustedDirection = new Vec3(lookDirection.x, lookDirection.y + 0.15, lookDirection.z);
        newArrow.shoot(adjustedDirection.x, adjustedDirection.y, adjustedDirection.z, 1.0F, 1.0F);
        originalArrow.discard();

        Entity owner = originalArrow.getOwner();
        if (owner instanceof Player shooter) {
            if (shooter.getUseItem().getItem() instanceof net.minecraft.world.item.BowItem) {
                newArrow.pickup = Arrow.Pickup.CREATIVE_ONLY;
            } else {
                newArrow.pickup = Arrow.Pickup.ALLOWED;
            }
        } else if (owner == null) {
            newArrow.pickup = Arrow.Pickup.ALLOWED;
        } else {
            newArrow.pickup = Arrow.Pickup.CREATIVE_ONLY;
        }
        player.level().addFreshEntity(newArrow);
    }

    private static void knockbackAttacker(Player player, LivingEntity attacker) {
        double strength = 1.25;
        Vec3 knockbackDirection = new Vec3(attacker.getX() - player.getX(), 0, attacker.getZ() - player.getZ()).normalize();
        attacker.push(knockbackDirection.x * strength, 0.5, knockbackDirection.z * strength);
    }

    private static void vibraniumTurtleHelmetTick(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 405, 0, false, false, true));
    }

    private static boolean isEquipped(Player player, Item item) {
        for (EquipmentSlot slot : EquipmentSlot.VALUES) {
            ItemStack stack = player.getItemBySlot(slot);
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (stack.is(item) && equippable != null && equippable.slot() == slot) {
                return true;
            }
        }
        return false;
    }
}