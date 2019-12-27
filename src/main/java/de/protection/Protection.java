package de.protection;


import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Protection {

    private static final Protection instance = new Protection();

    public static Protection getInstance(){
        return instance;
    }


    private static final ArrayList<Location> protectedBlocks = new ArrayList<>(400);

    private Protection() {
    }

    public synchronized boolean isProtected(Block b) {
        return protectedBlocks.remove(b.getLocation());
    }

    public synchronized void addProtection(Block b) {
        if (protectedBlocks.size() >= 400)
            protectedBlocks.remove(0);
        protectedBlocks.add(b.getLocation());
    }

}
