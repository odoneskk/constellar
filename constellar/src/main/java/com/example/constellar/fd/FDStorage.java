package com.example.constellar.fd;

import net.minecraft.nbt.CompoundTag;

/** Implementacao simples de armazenamento de FD, com save/load em NBT. */
public class FDStorage implements IForcefield {
    private int fd;
    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;

    public FDStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public int getFD() {
        return fd;
    }

    @Override
    public int getMaxFD() {
        return capacity;
    }

    @Override
    public int receiveFD(int amount, boolean simulate) {
        if (!canReceive() || amount <= 0) return 0;
        int accepted = Math.min(capacity - fd, Math.min(maxReceive, amount));
        if (!simulate) fd += accepted;
        return accepted;
    }

    @Override
    public int extractFD(int amount, boolean simulate) {
        if (!canExtract() || amount <= 0) return 0;
        int extracted = Math.min(fd, Math.min(maxExtract, amount));
        if (!simulate) fd -= extracted;
        return extracted;
    }

    @Override
    public boolean canReceive() {
        return maxReceive > 0;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    /** Uso interno (geradores): adiciona FD ignorando o limite de entrada. */
    public int generate(int amount) {
        int added = Math.min(capacity - fd, Math.max(0, amount));
        fd += added;
        return added;
    }

    public void save(CompoundTag tag) {
        tag.putInt("fd", fd);
    }

    public void load(CompoundTag tag) {
        fd = Math.max(0, Math.min(capacity, tag.getInt("fd")));
    }
}
