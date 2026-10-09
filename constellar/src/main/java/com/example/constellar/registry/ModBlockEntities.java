package com.example.constellar.registry;

import com.example.constellar.Constellar;
import com.example.constellar.block.ForcefieldBatteryBlockEntity;
import com.example.constellar.block.StarlightGeneratorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Constellar.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StarlightGeneratorBlockEntity>> STARLIGHT_GENERATOR =
            BLOCK_ENTITIES.register("starlight_generator", () -> BlockEntityType.Builder
                    .of(StarlightGeneratorBlockEntity::new, ModBlocks.STARLIGHT_GENERATOR.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ForcefieldBatteryBlockEntity>> FORCEFIELD_BATTERY =
            BLOCK_ENTITIES.register("forcefield_battery", () -> BlockEntityType.Builder
                    .of(ForcefieldBatteryBlockEntity::new, ModBlocks.FORCEFIELD_BATTERY.get())
                    .build(null));

    private ModBlockEntities() {}
}
