package com.example.constellar.registry;

import com.example.constellar.Constellar;
import com.example.constellar.item.ForcefieldReaderItem;
import com.example.constellar.item.TelescopeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constellar.MODID);

    public static final DeferredItem<BlockItem> STARLIGHT_GENERATOR =
            ITEMS.registerSimpleBlockItem("starlight_generator", ModBlocks.STARLIGHT_GENERATOR);

    public static final DeferredItem<BlockItem> FORCEFIELD_BATTERY =
            ITEMS.registerSimpleBlockItem("forcefield_battery", ModBlocks.FORCEFIELD_BATTERY);

    public static final DeferredItem<TelescopeItem> TELESCOPE =
            ITEMS.registerItem("telescope", TelescopeItem::new, new Item.Properties().stacksTo(1));

    public static final DeferredItem<ForcefieldReaderItem> FORCEFIELD_READER =
            ITEMS.registerItem("forcefield_reader", ForcefieldReaderItem::new, new Item.Properties().stacksTo(1));

    private ModItems() {}
}
