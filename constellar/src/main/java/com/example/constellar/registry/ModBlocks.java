package com.example.constellar.registry;

import com.example.constellar.Constellar;
import com.example.constellar.block.ForcefieldBatteryBlock;
import com.example.constellar.block.StarlightGeneratorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constellar.MODID);

    public static final DeferredBlock<StarlightGeneratorBlock> STARLIGHT_GENERATOR = BLOCKS.registerBlock(
            "starlight_generator", StarlightGeneratorBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.0f, 6.0f));

    public static final DeferredBlock<ForcefieldBatteryBlock> FORCEFIELD_BATTERY = BLOCKS.registerBlock(
            "forcefield_battery", ForcefieldBatteryBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(3.0f, 6.0f));

    private ModBlocks() {}
}
