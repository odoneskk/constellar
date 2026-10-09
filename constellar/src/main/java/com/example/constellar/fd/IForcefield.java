package com.example.constellar.fd;

/** Interface do sistema de energia Forcefield (FD). */
public interface IForcefield {
    int getFD();

    int getMaxFD();

    /** @return quanto FD realmente entrou */
    int receiveFD(int amount, boolean simulate);

    /** @return quanto FD realmente saiu */
    int extractFD(int amount, boolean simulate);

    default boolean canReceive() {
        return true;
    }

    default boolean canExtract() {
        return true;
    }
}
