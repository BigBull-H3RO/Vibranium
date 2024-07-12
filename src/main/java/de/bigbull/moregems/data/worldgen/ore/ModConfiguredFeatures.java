package de.bigbull.moregems.data.worldgen.ore;

import de.bigbull.moregems.init.BlockInit;
import de.bigbull.moregems.main.ModInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VIBRANIUM_ORE = createKey("overworld_vibranium_ore");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> VibraniumOre =
                List.of(OreConfiguration.target(stoneReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_VIBRANIUM_ORE, Feature.ORE, new OreConfiguration(VibraniumOre, 4));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
