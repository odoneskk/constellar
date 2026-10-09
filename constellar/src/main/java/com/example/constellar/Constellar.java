package com.example.constellar;

import com.example.constellar.constellation.Constellation;
import com.example.constellar.constellation.ConstellationRegistry;
import com.example.constellar.fd.FDCapabilities;
import com.example.constellar.registry.ModAttachments;
import com.example.constellar.registry.ModBlockEntities;
import com.example.constellar.registry.ModBlocks;
import com.example.constellar.registry.ModCreativeTabs;
import com.example.constellar.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(Constellar.MODID)
public class Constellar {
    public static final String MODID = "constellar";

    public Constellar(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
        ModAttachments.ATTACHMENTS.register(modBus);

        modBus.addListener(this::registerCapabilities);
        modBus.addListener(this::registerDatapackRegistries);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(FDCapabilities.BLOCK,
                ModBlockEntities.STARLIGHT_GENERATOR.get(), (be, side) -> be.getFD());
        event.registerBlockEntity(FDCapabilities.BLOCK,
                ModBlockEntities.FORCEFIELD_BATTERY.get(), (be, side) -> be.getFD());
    }

    private void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(ConstellationRegistry.KEY, Constellation.CODEC, Constellation.CODEC);
    }
}
