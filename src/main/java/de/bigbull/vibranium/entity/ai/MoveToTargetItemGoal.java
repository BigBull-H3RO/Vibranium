package de.bigbull.vibranium.entity.ai;

import de.bigbull.vibranium.entity.VibraGolemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.function.Predicate;

public class MoveToTargetItemGoal extends Goal {
    private final VibraGolemEntity vibraGolem;
    private final double speedModifier;
    private final Predicate<ItemStack> items;

    public MoveToTargetItemGoal(VibraGolemEntity vibraGolem, double speedModifier, Predicate<ItemStack> items) {
        this.vibraGolem = vibraGolem;
        this.speedModifier = speedModifier;
        this.items = items;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return vibraGolem.getOwnerUUID() != null;
    }

    @Override
    public void tick() {
        ItemEntity itemEntity = (ItemEntity) ((ServerLevel) vibraGolem.level()).getEntity(vibraGolem.getOwnerUUID());
        if (itemEntity != null) {
            vibraGolem.getNavigation().moveTo(itemEntity, this.speedModifier);
            if (vibraGolem.distanceTo(itemEntity) < 1.5) {
                vibraGolem.setOwnerUUID(null);
            }
        } else {
            vibraGolem.setOwnerUUID(null);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return vibraGolem.getOwnerUUID() != null && !vibraGolem.getNavigation().isDone();
    }

    @Override
    public void stop() {
        vibraGolem.getNavigation().stop();
    }
}
