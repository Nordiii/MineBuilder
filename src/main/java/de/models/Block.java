package de.models;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Block extends IEntity {
    private final String name;
    private final int ID;
    private final int typeID;
    private final int blocksToBreak;
    private final int expMinGain;
    private final int expMaxGain;

    private int expInXBlocks;

    public Block(String name,int ID,int expInXBlocks,int expMinGain,int expMaxGain){
        this.name = name;
        this.ID = ID;
        this.typeID = 0;
        this.expInXBlocks = blocksToBreak = expInXBlocks;
        this.expMinGain = expMinGain;
        this.expMaxGain = expMaxGain;

    }
    public Block(Block b){
        this.name = b.name;
        this.ID = b.ID;
        this.typeID = 0;
        this.expInXBlocks = blocksToBreak = b.expInXBlocks;
        this.expMinGain = b.expMinGain;
        this.expMaxGain = b.expMaxGain;
    }
    public Block(org.bukkit.block.Block b){
        if(b == null)
        {
            this.name = "NO";
            this.ID = -10;
            this.typeID = -10;
            this.expInXBlocks = blocksToBreak = 0;
            this.expMinGain = 0;
            this.expMaxGain = 0;
            return;
        }

        this.name = b.getType().name();
        this.ID = b.getType().getId();
        this.typeID = b.getData();
        this.expInXBlocks = blocksToBreak = 0;
        this.expMinGain = 0;
        this.expMaxGain = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return ID == block.ID &&
               // typeID == block.typeID &&
                Objects.equals(name, block.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, ID, typeID);
    }

    @Override
    public int getExp() {
        decrease();
        if(expInXBlocks == 0)
        {
            expInXBlocks = blocksToBreak;
            return ThreadLocalRandom.current().nextInt(expMinGain,expMaxGain+1);
        }

        return 0;

    }

    @Override
    public void decrease() {
        expInXBlocks--;
    }
}
