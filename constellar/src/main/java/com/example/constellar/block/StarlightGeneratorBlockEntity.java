package com.example.constellar.block;

import com.example.constellar.constellation.Constellation;
import com.example.constellar.constellation.ConstellationRegistry;
import com.example.constellar.fd.FDCapabilities;
import com.example.constellar.fd.FDStorage;
import com.example.constellar.fd.IForcefield;
import com.example.constellar.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Gera FD captando luz das estrelas: precisa ser noite e ter ceu aberto.
 * Se alguma constelacao favorece a fase atual da lua, a geracao aumenta.
 */
public class StarlightGeneratorBlockEntity extends BlockEntity {
    private static final int BASE_RATE = 10;      // FD por tick
    private static final int PUSH_RATE = 2000;    // FD por tick enviado aos vizinhos

    private final FDStorage fd = new FDStorage(100_000, 0, PUSH_RATE);
    private float multiplier = 1.0f;

    public StarlightGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STARLIGHT_GENERATOR.get(), pos, state);
    }

    public IForcefield getFD() {
        return fd;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, StarlightGeneratorBlockEntity be) {
        // Recalcula o bonus de constelacao a cada segundo.
        if (level.getGameTime() % 20 == 0) {
            be.multiplier = computeMultiplier(level);
            be.setChanged();
        }

        if (canCaptureStarlight(level, pos)) {
            be.fd.generate(Math.round(BASE_RATE * be.multiplier));
        }

        // Envia FD para blocos vizinhos que aceitem.
        if (be.fd.getFD() > 0) {
            for (Direction dir : Direction.values()) {
                IForcefield target = level.getCapability(FDCapabilities.BLOCK, pos.relative(dir), dir.getOpposite());
                if (target == null || !target.canReceive()) continue;
                int offered = be.fd.extractFD(PUSH_RATE, true);
                int accepted = target.receiveFD(offered, false);
                be.fd.extractFD(accepted, false);
                if (be.fd.getFD() <= 0) break;
            }
        }
    }

    private static boolean canCaptureStarlight(Level level, BlockPos pos) {
        if (!level.dimensionType().hasSkyLight()) return false;
        long t = level.getDayTime() % 24000L;
        boolean night = t >= 13000L && t < 23000L;
        return night && level.canSeeSky(pos.above());
    }

    private static float computeMultiplier(Level level) {
        int phase = level.getMoonPhase();
        float best = 1.0f;
        for (Constellation c : level.registryAccess().registryOrThrow(ConstellationRegistry.KEY)) {
            if (c.preferredMoonPhase() == phase) {
                best = Math.max(best, c.fdBonus());
            }
        }
        return best;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        fd.save(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        fd.load(tag);
    }
}
