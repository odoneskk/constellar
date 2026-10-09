package com.example.constellar.item;

import com.example.constellar.fd.FDCapabilities;
import com.example.constellar.fd.IForcefield;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

/** Item de debug: mostra o FD armazenado no bloco clicado. */
public class ForcefieldReaderItem extends Item {
    public ForcefieldReaderItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        Player player = context.getPlayer();
        if (level.isClientSide || player == null) return InteractionResult.SUCCESS;

        IForcefield fd = level.getCapability(FDCapabilities.BLOCK, context.getClickedPos(), context.getClickedFace());
        if (fd == null) {
            player.displayClientMessage(Component.translatable("message.constellar.no_fd"), true);
        } else {
            player.displayClientMessage(
                    Component.translatable("message.constellar.fd_amount", fd.getFD(), fd.getMaxFD()), true);
        }
        return InteractionResult.CONSUME;
    }
}
