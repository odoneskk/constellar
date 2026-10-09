package com.example.constellar.registry;

import com.example.constellar.Constellar;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constellar.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.constellar"))
                    .icon(() -> new ItemStack(ModItems.TELESCOPE.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.TELESCOPE.get());
                        output.accept(ModItems.FORCEFIELD_READER.get());
                        output.accept(ModItems.STARLIGHT_GENERATOR.get());
                        output.accept(ModItems.FORCEFIELD_BATTERY.get());
                    })
                    .build());

    private ModCreativeTabs() {}
}
