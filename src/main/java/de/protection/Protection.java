package de.protection;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import de.minebuilder.Settings;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

import static de.minebuilder.Settings.path.PROTECTION_COUNT;

public class Protection {

    private static Protection instance;
    private final Plugin mineBuilder;
    private final WorldGuardPlugin wg;
    private ArrayList<Location> protectedBlocks;
    private int protectedCount;

    private Protection(Plugin MineBuilder) {
        protectedCount = Settings.getConfig().getInt(PROTECTION_COUNT.path);
        protectedBlocks = new ArrayList<>(protectedCount);
        mineBuilder = MineBuilder;
        wg = getWorldGuard();
    }

    public static synchronized void createInstance(Plugin mineBuilder) {
        if (instance == null)
            instance = new Protection(mineBuilder);
    }

    public static Protection getInstance() {
        return instance;
    }

    public void updateProtectionCount() {
        protectedCount = Settings.getConfig().getInt(PROTECTION_COUNT.path);
        protectedBlocks = new ArrayList<>(protectedCount);
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin worldGuard = mineBuilder.getServer().getPluginManager().getPlugin("WorldGuard");
        if (!(worldGuard instanceof WorldGuardPlugin))
            return null;
        return (WorldGuardPlugin) worldGuard;
    }

    public boolean isProtected(Player p, Block b) {
        synchronized (instance) {
            if (isProtectedWG(p, b))
                return true;
            if (protectedCount == 0)
                return false;
            return protectedBlocks.remove(b.getLocation());
        }
    }

    private boolean isProtectedWG(Player p, Block b) {
        if (wg == null)
            return false;
        LocalPlayer localPlayer = wg.wrapPlayer(p);
        com.sk89q.worldedit.util.Location block = BukkitAdapter.adapt(b.getLocation());
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = regionContainer.createQuery();
        return !query.testState(block, localPlayer, Flags.BUILD);
    }

    public synchronized void addProtection(Block b) {
        synchronized (instance) {
            if (protectedCount == 0)
                return;
            if (protectedBlocks.size() >= protectedCount)
                protectedBlocks.remove(0);
            protectedBlocks.add(b.getLocation());
        }
    }

}
