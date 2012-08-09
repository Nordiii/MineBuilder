package me.eistee2.minebuilder;

import java.util.ArrayList;

import org.bukkit.Location;

public class BlockSaver {
	private static BlockSaver instance = null;
	public static BlockSaver getInstance()
	{
		if(instance == null)
		{
			instance = new BlockSaver();
		}
		
		return instance;
	}
	
	private ReadOut info = ReadOut.getInstance();
	
	private ArrayList<Location> saveBlock = new ArrayList<Location>();
	
	
	int amountOfSavedBlocks = 0;
	public void addLocation(Location location)
	{
		if(info.getMaxBlockSaved() == saveBlock.size())
		{
			if(amountOfSavedBlocks == info.getMaxBlockSaved())
			{
				amountOfSavedBlocks = 0;
			}
			saveBlock.set(amountOfSavedBlocks, location);
			amountOfSavedBlocks += 1;
		}
		else
		{
			saveBlock.add(location);
		}
	}
	
	public boolean checkLocation(Location location)
	{
		boolean contains = false;
		if(saveBlock.contains(location))
		{
			saveBlock.remove(saveBlock.indexOf(location));
			contains = true;
		}		
		return contains;
	}
	
}
