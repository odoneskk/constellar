package com.example.constellar.block;

import com.example.constellar.fd.FDStorage;
import com.example.constellar.fd.IForcefield;
import com.example.constellar.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ForcefieldBatteryBlockEntity extends BlockEntity {
    private final FDStorage fd = new FDStorage(1_000_000, 5_000, 5_000);

    public ForcefieldBatteryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORCEFIELD_BATTERY.get(), pos, state);
    }

    public IForcefield getFD() {
        return new IForcefield() {
            @Override
            public int getFD() {
                return fd.getFD();
            }

            @Override
            public int getMaxFD() {
                return fd.getMaxFD();
            }

            @Override
            public int receiveFD(int amount, boolean simulate) {
                int r = fd.receiveFD(amount, simulate);
                if (r > 0 && !simulate) setChanged();
                return r;
            }

            @Override
            public int extractFD(int amount, boolean simulate) {
                int r = fd.extractFD(amount, simulate);
                if (r > 0 && !simulate) setChanged();
                return r;
            }
        };
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
