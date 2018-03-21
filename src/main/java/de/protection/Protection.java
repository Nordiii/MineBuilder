package de.protection;


import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Protection {

    private static Protection instance = new Protection();

    public static Protection getInstance(){
        return instance;
    }


    private static ArrayList<Location> protectedBlocks = new ArrayList<>();

    private Protection() {
    }

    public boolean isProtected(Block b)
    {
        return protectedBlocks.remove(b.getLocation());
    }

    public void addProtection(Block b)
    {
        protectedBlocks.add(b.getLocation());
    }

}
