package com.example.constellar.fd;

import com.example.constellar.Constellar;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import org.jetbrains.annotations.Nullable;

public final class FDCapabilities {
    public static final BlockCapability<IForcefield, @Nullable Direction> BLOCK =
            BlockCapability.createSided(
                    ResourceLocation.fromNamespaceAndPath(Constellar.MODID, "forcefield"),
                    IForcefield.class);

    private FDCapabilities() {}
}
