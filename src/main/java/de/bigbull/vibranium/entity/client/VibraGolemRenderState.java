package de.bigbull.vibranium.entity.client;

import de.bigbull.vibranium.entity.client.Crackniess.VibraCrackiness;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VibraGolemRenderState extends LivingEntityRenderState {
    public float attackTicksRemaining;
    public VibraCrackiness.Level crackiness = VibraCrackiness.Level.NONE;
}
