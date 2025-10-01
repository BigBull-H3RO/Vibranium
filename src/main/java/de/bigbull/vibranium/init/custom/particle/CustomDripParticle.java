package de.bigbull.vibranium.init.custom.particle;

import de.bigbull.vibranium.init.ParticleInit;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class CustomDripParticle extends SingleQuadParticle {
    private final Fluid type;
    protected boolean isGlowing;

    protected CustomDripParticle(ClientLevel level, double x, double y, double z, Fluid type, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.type = type;
        this.gravity = 0.06F;
        this.setColor(1.0F, 0.2F, 0.2F);
    }

    protected Fluid getType() {
        return this.type;
    }

    @Override
    protected Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public int getLightColor(float p_106065_) {
        return this.isGlowing ? 240 : super.getLightColor(p_106065_);
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

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {
    }

    // ---------- Provider: DRIPPING (Hang) ----------
    public static class VibraniumHangProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;
        public VibraniumHangProvider(SpriteSet sprite) { this.sprite = sprite; }

        @Override
        public Particle createParticle(
                SimpleParticleType type, ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            DripHangParticle p = new DripHangParticle(level, x, y, z, Fluids.EMPTY, ParticleInit.FALLING_VIBRANIUM.get(), this.sprite.get(random));
            p.isGlowing = true;
            p.gravity *= 0.01F;
            p.lifetime = 100;
            p.setColor(0.149F, 0.149F, 0.498F);
            return p;
        }
    }

    // ---------- Provider: FALLING ----------
    public static class VibraniumFallProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;
        public VibraniumFallProvider(SpriteSet sprite) { this.sprite = sprite; }

        @Override
        public Particle createParticle(
                SimpleParticleType type, ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            CustomDripParticle p = new FallAndLandParticle(level, x, y, z, Fluids.EMPTY, ParticleInit.LANDING_VIBRANIUM.get(), this.sprite.get(random));
            p.isGlowing = true;
            p.gravity = 0.01F;
            p.setColor(0.149F, 0.149F, 0.498F);
            return p;
        }
    }

    // ---------- Provider: LANDING ----------
    public static class VibraniumLandProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;
        public VibraniumLandProvider(SpriteSet sprite) { this.sprite = sprite; }

        @Override
        public Particle createParticle(
                SimpleParticleType type, ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            CustomDripParticle p = new DripLandParticle(level, x, y, z, Fluids.EMPTY, this.sprite.get(random));
            p.isGlowing = true;
            p.lifetime = (int)(28.0 / (Math.random() * 0.8 + 0.2));
            p.setColor(0.149F, 0.149F, 0.498F);
            return p;
        }
    }

    // ---------- deine inneren Partikelklassen bleiben im Prinzip gleich ----------
    static class DripHangParticle extends CustomDripParticle {
        private final ParticleOptions fallingParticle;
        public DripHangParticle(ClientLevel lvl, double x, double y, double z, Fluid fluid, ParticleOptions falling, TextureAtlasSprite sprite) {
            super(lvl, x, y, z, fluid, sprite);
            this.fallingParticle = falling;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }
        @Override protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }
        @Override protected void postMoveUpdate() { this.xd *= 0.02; this.yd *= 0.02; this.zd *= 0.02; }
    }

    static class FallAndLandParticle extends CustomDripParticle.FallingParticle {
        protected final ParticleOptions landParticle;
        FallAndLandParticle(ClientLevel lvl, double x, double y, double z, Fluid fluid, ParticleOptions land, TextureAtlasSprite sprite) {
            super(lvl, x, y, z, fluid, sprite);
            this.landParticle = land;
        }
        @Override protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
                SoundEvent sound = SoundEvents.POINTED_DRIPSTONE_DRIP_WATER;
                float vol = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, sound, SoundSource.BLOCKS, vol, 0.8F, false);
            }
        }
    }

    static class FallingParticle extends CustomDripParticle {
        FallingParticle(ClientLevel lvl, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
            this(lvl, x, y, z, fluid, (int)(64.0 / (Math.random() * 0.8 + 0.2)), sprite);
        }
        FallingParticle(ClientLevel lvl, double x, double y, double z, Fluid fluid, int lifetime, TextureAtlasSprite sprite) {
            super(lvl, x, y, z, fluid, sprite);
            this.lifetime = lifetime;
        }
        @Override protected void postMoveUpdate() { if (this.onGround) this.remove(); }
    }

    static class DripLandParticle extends CustomDripParticle {
        DripLandParticle(ClientLevel lvl, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
            super(lvl, x, y, z, fluid, sprite);
            this.lifetime = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }
    }
}