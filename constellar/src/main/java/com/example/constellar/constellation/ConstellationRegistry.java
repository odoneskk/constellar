package com.example.constellar.constellation;

import com.example.constellar.Constellar;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public final class ConstellationRegistry {
    public static final ResourceKey<Registry<Constellation>> KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Constellar.MODID, "constellation"));

    private ConstellationRegistry() {}
}
