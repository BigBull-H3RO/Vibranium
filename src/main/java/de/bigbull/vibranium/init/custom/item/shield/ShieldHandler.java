package de.bigbull.vibranium.init.custom.item.shield;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ShieldHandler {

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

        newArrow.setBaseDamage(originalArrow.getBaseDamage());
        newArrow.setCritArrow(originalArrow.isCritArrow());
        newArrow.setRemainingFireTicks(originalArrow.getRemainingFireTicks());
        newArrow.setOwner(player);
        newArrow.setPos(originalArrow.getX(), originalArrow.getY(), originalArrow.getZ());

        Vec3 lookDirection = player.getLookAngle();
        Vec3 adjustedDirection = new Vec3(lookDirection.x, lookDirection.y + 0.15, lookDirection.z);
        newArrow.shoot(adjustedDirection.x, adjustedDirection.y, adjustedDirection.z, 1.0F, 1.0F);
        originalArrow.discard();

        Entity owner = originalArrow.getOwner();
        if (owner instanceof Player) {
            Player shooter = (Player) owner;

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
}