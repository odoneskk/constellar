package com.example.constellar.constellation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

/**
 * Constelacao definida por datapack:
 * data/<namespace>/constellar/constellation/<nome>.json
 */
public record Constellation(
        String name,
        int color,
        int preferredMoonPhase,
        float fdBonus,
        List<Star> stars
) {
    public record Star(int x, int y) {
        public static final Codec<Star> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.INT.fieldOf("x").forGetter(Star::x),
                Codec.INT.fieldOf("y").forGetter(Star::y)
        ).apply(i, Star::new));
    }

    public static final Codec<Constellation> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("name").forGetter(Constellation::name),
            Codec.INT.fieldOf("color").forGetter(Constellation::color),
            Codec.intRange(0, 7).fieldOf("preferred_moon_phase").forGetter(Constellation::preferredMoonPhase),
            Codec.FLOAT.optionalFieldOf("fd_bonus", 1.0f).forGetter(Constellation::fdBonus),
            Star.CODEC.listOf().fieldOf("stars").forGetter(Constellation::stars)
    ).apply(i, Constellation::new));
}
