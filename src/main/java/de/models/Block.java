package de.models;

import java.util.Objects;

public class Block extends IEntity {
    private final String name;
    private final int ID;
    private final int typeID;
    private final int expInXBlocks;
    private final float expMinGain;
    private final float expMaxGain;

    public Block(org.bukkit.block.Block b,int expInXBlocks,float expMinGain,float expMaxGain){
        if(b != null)
        {
            this.name = b.getType().name();
            this.ID = b.getType().getId();
            this.typeID = b.getData();
            this.expInXBlocks = expInXBlocks;
            this.expMinGain = expMinGain;
            this.expMaxGain = expMaxGain;
        }
        else
        {
            this.name = "GRASS";
            this.ID = 2;
            this.typeID = 0;
            this.expInXBlocks = expInXBlocks;
            this.expMinGain = expMinGain;
            this.expMaxGain = expMaxGain;
        }

    }
    public Block(org.bukkit.block.Block b){
        if(b != null)
        {
            this.name = b.getType().name();
            this.ID = b.getType().getId();
            this.typeID = b.getData();
            this.expInXBlocks = 0;
            this.expMinGain = 0;
            this.expMaxGain = 0;
        }
        else
        {
            this.name = "GRASS";
            this.ID = 2;
            this.typeID = 0;
            this.expInXBlocks = 0;
            this.expMinGain = 0;
            this.expMaxGain = 0;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Comparing::::");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return ID == block.ID &&
                typeID == block.typeID &&
                Objects.equals(name, block.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, ID, typeID);
    }
}
