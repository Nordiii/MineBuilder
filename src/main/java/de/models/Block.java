package de.models;

import com.google.gson.annotations.Expose;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Block extends IEntity {
    @Expose
    private final String name;
    @Expose
    private final int repeat;
    @Expose
    private final int expMinGain;
    @Expose
    private final int expMaxGain;

    private int expInXBlocks;

    public Block(String name, int expInXBlocks, int expMinGain, int expMaxGain) {
        this.name = name;
        this.expInXBlocks = repeat = expInXBlocks;
        this.expMinGain = expMinGain;
        this.expMaxGain = expMaxGain;

    }

    public Block(Block b) {
        this.name = b.name;
        this.expInXBlocks = repeat = b.expInXBlocks;
        this.expMinGain = b.expMinGain;
        this.expMaxGain = b.expMaxGain;
    }
    public Block(org.bukkit.block.Block b) {
        if (b == null) {
            this.name = "NO";
            this.expInXBlocks = repeat = 0;
            this.expMinGain = 0;
            this.expMaxGain = 0;
            return;
        }

        this.name = b.getBlockData().getAsString().split(":")[1].split("\\[")[0];
        this.expInXBlocks = repeat = 0;
        this.expMinGain = 0;
        this.expMaxGain = 0;
    }

    @Override
    public String toString() {
        return "Block{" +
                "name='" + name + '\'' +
                ", blocksToBreak=" + repeat +
                ", expMinGain=" + expMinGain +
                ", expMaxGain=" + expMaxGain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return name.equals(block.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int getExp() {
        decrease();
        if (expInXBlocks <= 0) {
            expInXBlocks = repeat;
            return ThreadLocalRandom.current().nextInt(expMinGain, expMaxGain + 1);
        }

        return 0;

    }

    @Override
    public void decrease() {
        expInXBlocks--;
    }
}
