package de.bigbull.vibranium.entity.ai;

import de.bigbull.vibranium.entity.VibraGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;

public class DefensiveGoal extends Goal {
    private final VibraGolemEntity entity;
    private BlockPos defensivePosition;

    public DefensiveGoal(VibraGolemEntity entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        return this.entity.isDefensiveMode() && this.entity.getDefensivePosition() != null;
    }

    @Override
    public void start() {
        this.defensivePosition = this.entity.getDefensivePosition();
    }

    @Override
    public void stop() {
        this.entity.setTarget(null);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!this.entity.isDefensiveMode()) {
            return;
        }

        List<Monster> monsters = this.entity.level().getEntitiesOfClass(Monster.class, new AABB(defensivePosition).inflate(40));
        if (!monsters.isEmpty()) {
            for (Monster monster : monsters) {
                if (this.entity.hasLineOfSight(monster) && !monster.isUnderWater()) {
                    this.entity.getNavigation().moveTo(monster, 1.0D);
                    this.entity.setTarget(monster);
                    return;
                }
            }
        } else {
            if (this.entity.distanceToSqr(defensivePosition.getX(), defensivePosition.getY(), defensivePosition.getZ()) > 1.0) {
                this.entity.getNavigation().moveTo(defensivePosition.getX(), defensivePosition.getY(), defensivePosition.getZ(), 1.0D);
            } else {
                this.entity.setTarget(null);
                this.entity.getNavigation().stop();
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.entity.isDefensiveMode() && this.entity.getDefensivePosition() != null;
    }
}