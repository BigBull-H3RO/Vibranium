package de.bigbull.vibranium.init;

import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.structure.VibraniumGeodeFeature;
import de.bigbull.vibranium.init.custom.SoulTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FeatureInit {
    public static final DeferredRegister<MapCodec<? extends Feature>> FEATURES = DeferredRegister.create(Registries.FEATURE_TYPE, Vibranium.MODID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Vibranium.MODID);

    public static final Supplier<MapCodec<VibraniumGeodeFeature>> VIBRANIUM_GEODE = FEATURES.register("vibranium_geode", () -> VibraniumGeodeFeature.CODEC);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SoulTreeTrunkPlacer>> SOUL_TREE_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("soul_tree_trunk_placer", () -> new TrunkPlacerType<>(SoulTreeTrunkPlacer.CODEC));
}
