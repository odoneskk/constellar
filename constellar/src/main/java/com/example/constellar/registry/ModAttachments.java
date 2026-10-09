package com.example.constellar.registry;

import com.example.constellar.Constellar;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public final class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Constellar.MODID);

    /** Constelacoes que o jogador ja descobriu com o telescopio. */
    public static final Supplier<AttachmentType<List<ResourceLocation>>> DISCOVERED_CONSTELLATIONS =
            ATTACHMENTS.register("discovered_constellations",
                    () -> AttachmentType.<List<ResourceLocation>>builder(() -> List.of())
                            .serialize(ResourceLocation.CODEC.listOf())
                            .copyOnDeath()
                            .build());

    private ModAttachments() {}
}
