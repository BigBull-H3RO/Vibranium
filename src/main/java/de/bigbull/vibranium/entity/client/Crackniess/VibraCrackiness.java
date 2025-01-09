package de.bigbull.vibranium.entity.client.Crackniess;

import net.minecraft.world.item.ItemStack;

public class VibraCrackiness {
    public static final VibraCrackiness VIBRA_GOLEM = new VibraCrackiness(0.75F, 0.5F, 0.25F);
    private final float fractionLow;
    private final float fractionMedium;
    private final float fractionHigh;

    private VibraCrackiness(float low, float medium, float high) {
        this.fractionLow = low;
        this.fractionMedium = medium;
        this.fractionHigh = high;
    }

    public VibraCrackiness.Level byFraction(float p_331959_) {
        if (p_331959_ < this.fractionHigh) {
            return VibraCrackiness.Level.HIGH;
        } else if (p_331959_ < this.fractionMedium) {
            return VibraCrackiness.Level.MEDIUM;
        } else {
            return p_331959_ < this.fractionLow ? VibraCrackiness.Level.LOW : VibraCrackiness.Level.NONE;
        }
    }

    public VibraCrackiness.Level byDamage(ItemStack itemStack) {
        return !itemStack.isDamageableItem() ? VibraCrackiness.Level.NONE : this.byDamage(itemStack.getDamageValue(), itemStack.getMaxDamage());
    }

    public VibraCrackiness.Level byDamage(int p_330780_, int p_330215_) {
        return this.byFraction((float)(p_330215_ - p_330780_) / (float)p_330215_);
    }

    public enum Level {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }
}
