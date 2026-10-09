package com.example.constellar.item;

import com.example.constellar.constellation.Constellation;
import com.example.constellar.constellation.ConstellationRegistry;
import com.example.constellar.registry.ModAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Telescopio: de noite, com ceu aberto, descobre uma constelacao nova.
 * As descobertas ficam salvas no jogador (attachment).
 */
public class TelescopeItem extends Item {
    public TelescopeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) return InteractionResultHolder.success(stack);

        long t = level.getDayTime() % 24000L;
        boolean night = t >= 13000L && t < 23000L;
        if (!level.dimensionType().hasSkyLight() || !night || !level.canSeeSky(player.blockPosition())) {
            player.displayClientMessage(Component.translatable("message.constellar.cannot_observe"), true);
            return InteractionResultHolder.fail(stack);
        }

        List<ResourceLocation> known = player.getData(ModAttachments.DISCOVERED_CONSTELLATIONS);
        var registry = level.registryAccess().registryOrThrow(ConstellationRegistry.KEY);

        List<Map.Entry<ResourceLocation, Constellation>> undiscovered = new ArrayList<>();
        for (var entry : registry.entrySet()) {
            ResourceLocation id = entry.getKey().location();
            if (!known.contains(id)) {
                undiscovered.add(Map.entry(id, entry.getValue()));
            }
        }

        if (undiscovered.isEmpty()) {
            player.displayClientMessage(Component.translatable("message.constellar.nothing_new"), true);
            return InteractionResultHolder.success(stack);
        }

        var found = undiscovered.get(level.random.nextInt(undiscovered.size()));
        List<ResourceLocation> updated = new ArrayList<>(known);
        updated.add(found.getKey());
        player.setData(ModAttachments.DISCOVERED_CONSTELLATIONS, List.copyOf(updated));

        Component name = Component.literal(found.getValue().name())
                .withStyle(Style.EMPTY.withColor(found.getValue().color()));
        player.sendSystemMessage(Component.translatable("message.constellar.discovered", name));
        player.getCooldowns().addCooldown(this, 100);
        return InteractionResultHolder.success(stack);
    }
}
