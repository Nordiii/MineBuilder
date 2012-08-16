package me.eistee2.minebuilder;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

import com.avaje.ebean.EbeanServer;

public class BlockSaver implements Plugin {
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
	
	private ArrayList<String> saveBlock = new ArrayList<String>();
	
	String freizeile = System.getProperty("line.separator");
	
	int amountOfSavedBlocks = 0;
	public void addLocation(Location location)
	{
		if(info.getMaxBlockSaved() <= saveBlock.size())
		{
			if(amountOfSavedBlocks <= info.getMaxBlockSaved())
			{
				amountOfSavedBlocks = 0;
			}
			saveBlock.set(amountOfSavedBlocks, location.toString());
			amountOfSavedBlocks += 1;
		}
		else
		{
			amountOfSavedBlocks = 0;
			saveBlock.add(location.toString());
		}
	}
	public void checkPistonLoc(Location oldLoc,Location newLoc)
	{
		if(saveBlock.contains(oldLoc.toString()))
		{
			saveBlock.set(saveBlock.indexOf(oldLoc.toString()), newLoc.toString());
		}
	}
	
	public boolean checkLocation(Location location)
	{
		boolean contains = false;
		if(saveBlock.contains(location.toString()))
		{
			saveBlock.remove(saveBlock.indexOf(location.toString()));
			contains = true;
		}		
		return contains;
	}
	
	public void writeBlockSaveFile()
	{
	    ArrayList<String> saveBlockCopy = new ArrayList<String>();
	    saveBlockCopy.add("Latest Block at:"+amountOfSavedBlocks);
	    saveBlockCopy.addAll(saveBlock);
		configer saveBlockYml = new configer("plugins/MineBuilder","plugins/MineBuilder/SavedBlocks.ser");
		try {
			saveBlockYml.writeBlockSaveFile(saveBlockCopy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopTimer()
	{
		Bukkit.getServer().getScheduler().cancelTasks(getInstance());
	}

	public void writeTimerBlockSaveFile(int timer)
	{
		timer = timer * 1200;
	      Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

        	   public void run() {
         		  writeBlockSaveFile();
        	   }
        	}, 20 , timer);
      }

	
	public void readBlockSaveFile()
	{
		configer saveBlockYml = new configer("plugins/MineBuilder","plugins/MineBuilder/SavedBlocks.ser");

		ArrayList<String> saveBlocks = saveBlockYml.fileGetArrayList();
		try{
		String[] numberSplit = saveBlocks.get(0).split(":");
		amountOfSavedBlocks = Integer.parseInt(numberSplit[1]);
		saveBlocks.remove(0);
		}catch(Exception e)
		{
			System.out.println("[MineBuilder] Creat BlockSave.ser");
		}
		saveBlock.addAll(saveBlocks);

	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileConfiguration getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDataFolder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EbeanServer getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginDescriptionFile getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PluginLoader getPluginLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getResource(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNaggable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reloadConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDefaultConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveResource(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNaggable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
