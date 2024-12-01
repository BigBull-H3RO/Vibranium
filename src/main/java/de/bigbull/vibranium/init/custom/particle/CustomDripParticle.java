package de.bigbull.vibranium.init.custom.particle;

import de.bigbull.vibranium.init.ParticleInit;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomDripParticle extends TextureSheetParticle {
    private final Fluid type;
    protected boolean isGlowing;

    protected CustomDripParticle(ClientLevel level, double x, double y, double z, Fluid type) {
        super(level, x, y, z);
        this.type = type;
        this.gravity = 0.06F;
        this.setColor(1.0F, 0.2F, 0.2F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getLightColor(float p_106065_) {
        return this.isGlowing ? 240 : super.getLightColor(p_106065_);
    }

    protected Fluid getType() {
        return this.type;
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd = this.yd - (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                if (this.type != Fluids.EMPTY) {
                    BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidstate = this.level.getFluidState(blockpos);
                    if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                        this.remove();
                    }
                }
            }
        }
    }

    public static TextureSheetParticle createVibraniumHangParticle(
            SimpleParticleType p_273120_,
            ClientLevel p_272664_,
            double p_272879_,
            double p_272592_,
            double p_272967_,
            double p_272834_,
            double p_273440_,
            double p_272888_
    ) {
        CustomDripParticle.DripHangParticle particle = new CustomDripParticle.DripHangParticle(
                p_272664_, p_272879_, p_272592_, p_272967_, Fluids.EMPTY, ParticleInit.FALLING_VIBRANIUM.get()
        );
        particle.isGlowing = true;
        particle.gravity *= 0.01F;
        particle.lifetime = 100;
        particle.setColor(0.149F, 0.149F, 0.498F);
        return particle;
    }

    public static TextureSheetParticle createVibraniumFallParticle(
            SimpleParticleType p_272859_,
            ClientLevel p_273478_,
            double p_273621_,
            double p_273279_,
            double p_273227_,
            double p_273061_,
            double p_273257_,
            double p_273164_
    ) {
        CustomDripParticle particle = new CustomDripParticle.FallAndLandParticle(
                p_273478_, p_273621_, p_273279_, p_273227_, Fluids.EMPTY, ParticleInit.LANDING_VIBRANIUM.get()
        );
        particle.isGlowing = true;
        particle.gravity = 0.01F;
        particle.setColor(0.149F, 0.149F, 0.498F);
        return particle;
    }

    public static TextureSheetParticle createVibraniumLandParticle(
            SimpleParticleType p_272836_,
            ClientLevel p_273162_,
            double p_273543_,
            double p_273247_,
            double p_272921_,
            double p_273397_,
            double p_273472_,
            double p_273488_
    ) {
        CustomDripParticle particle = new CustomDripParticle.DripLandParticle(p_273162_, p_273543_, p_273247_, p_272921_, Fluids.EMPTY);
        particle.isGlowing = true;
        particle.lifetime = (int)(28.0 / (Math.random() * 0.8 + 0.2));
        particle.setColor(0.149F, 0.149F, 0.498F);
        return particle;
    }

    @OnlyIn(Dist.CLIENT)
    static class DripHangParticle extends CustomDripParticle {
        private final ParticleOptions fallingParticle;

        DripHangParticle(ClientLevel p_106085_, double p_106086_, double p_106087_, double p_106088_, Fluid p_106089_, ParticleOptions p_106090_) {
            super(p_106085_, p_106086_, p_106087_, p_106088_, p_106089_);
            this.fallingParticle = p_106090_;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        @Override
        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        @Override
        protected void postMoveUpdate() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallAndLandParticle extends CustomDripParticle.FallingParticle {
        protected final ParticleOptions landParticle;

        FallAndLandParticle(ClientLevel p_106116_, double p_106117_, double p_106118_, double p_106119_, Fluid p_106120_, ParticleOptions p_106121_) {
            super(p_106116_, p_106117_, p_106118_, p_106119_, p_106120_);
            this.landParticle = p_106121_;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class FallingParticle extends CustomDripParticle {
        FallingParticle(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_) {
            this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int)(64.0 / (Math.random() * 0.8 + 0.2)));
        }

        FallingParticle(ClientLevel p_172022_, double p_172023_, double p_172024_, double p_172025_, Fluid p_172026_, int p_172027_) {
            super(p_172022_, p_172023_, p_172024_, p_172025_, p_172026_);
            this.lifetime = p_172027_;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class DripLandParticle extends CustomDripParticle {
        DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_) {
            super(p_106102_, p_106103_, p_106104_, p_106105_, p_106106_);
            this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }
    }
}