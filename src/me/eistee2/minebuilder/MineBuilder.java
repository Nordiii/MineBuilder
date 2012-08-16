package me.eistee2.minebuilder;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.garbagemule.MobArena.MobArenaHandler;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class MineBuilder extends JavaPlugin{
//Events
	playerExpChange expChange = new playerExpChange();
	playerJoinQuit joinquit = playerJoinQuit.getInstance();
	playerBreakBlock breakBlock = new playerBreakBlock();
	playerPlaceBlock placeBlock = new playerPlaceBlock();
	playerSlainEntity slainEntity = new playerSlainEntity();
	playerFishing fishing = new playerFishing();
	BlockSaver BlockSaveFile = BlockSaver.getInstance();
	pistonMoveBlockEvent pistonMove = new pistonMoveBlockEvent();
	
	changeConfigFiles update = new changeConfigFiles();
	
	//Folderpaths	//Files
	configer oldPath = new configer("plugins/MineBuilder[Exp]","buh");
	configer Settings = new configer("plugins/MineBuilder","plugins/MineBuilder/Settings.yml");
	configer ExpConfig = new configer("plugins/MineBuilder","plugins/MineBuilder/ExpConfig.yml");
	configer MoneyConfig = new configer("plugins/MineBuilder","plugins/MineBuilder/MoneyConfig.yml");
	
	//Read all Configs
	ReadOut read = ReadOut.getInstance();
	
	//Hook into WolrdGuard
    public WorldGuardPlugin getWorldGuard()
    {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if(plugin == null)
        {
            return null;
        }
        return (WorldGuardPlugin)plugin;
    }
    
	//Hook into MobArena
    public MobArenaHandler maHandler;
    public void setupMobArenaHandler()
    {
        Plugin maPlugin = Bukkit.getServer().getPluginManager().getPlugin("MobArena");
        
        if (maPlugin == null){
            return;
        }
        maHandler = new MobArenaHandler();
    }
    
    public static Economy economy = null;
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(economyProvider != null)
            economy = (Economy)economyProvider.getProvider();
        return economy != null;
    }
	
	public void onDisable()
	{
		read.ReadAll();
		BlockSaveFile.stopTimer();
		if(read.getBlockSaveFileBoolean() == true)
		{
			BlockSaveFile.writeBlockSaveFile();
		}
	}
	
	public void onEnable()
	{
		//CHeck if it goes to MineBuilderExp and not MineBuilder
		oldPath.checkFolderPath();
		//Check Config
		System.out.println("[MineBuilderExp] Check Configs!");
		ConfigCheck();
		System.out.println("[MineBuilderExp] Configs checked!");
		
		//Check for Config update
		update.CheckForConfigUpdate();
		
		//Read the Configs
		read.ReadAll();
		
		//Load SavedBlocks
		if(read.getBlockSaveFileBoolean() == true)
		{
			BlockSaveFile.readBlockSaveFile();
		}
		
		//Check if any player is allready on the server
		joinquit.PluginReloaded();
		
		//Check if the server have a Permission Plugin
		checkPermissionPlugin();
		
		//Set up valut
		if(Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
		{
		setupEconomy();
		}
		//register events
		Bukkit.getServer().getPluginManager().registerEvents(joinquit, this);
		
		Bukkit.getServer().getPluginManager().registerEvents(expChange, this);
		
		Bukkit.getServer().getPluginManager().registerEvents(pistonMove, this);
		
		breakBlock.iniInts();
		Bukkit.getServer().getPluginManager().registerEvents(breakBlock, this);
		
		placeBlock.iniInts();
		Bukkit.getServer().getPluginManager().registerEvents(placeBlock, this);

		slainEntity.iniInts();
		Bukkit.getServer().getPluginManager().registerEvents(slainEntity, this);
		
		fishing.iniInts();
		Bukkit.getServer().getPluginManager().registerEvents(fishing, this);
		
		

	}
	
	private void ConfigCheck(){
		
		//Check if a config exist , when not it will creat on
				if(Settings.configCheck() == false)
				{
					Settings.creatSettings();
					System.out.println("[MineBuilderExp] Created Settings.yml");
				}
				
				if(ExpConfig.configCheck() == false)
				{
					ExpConfig.creatExpConfig();
					System.out.println("[MineBuilderExp] Created ExpConfig.yml");
				}
				
				if(MoneyConfig.configCheck() == false)
				{
					MoneyConfig.creatMoneyConfig();
					System.out.println("[MineBuilderExp] Created MoneyConfig.yml");
				}
				
	}
	
	Boolean PermissionPlugin = false;
	private void checkPermissionPlugin()
	{
		String[] permissionPlugins =
		{
			"Permissions", "GroupManager", "bPermissions", "PermissionsEx"	
		};
		for(Plugin permplug : Bukkit.getServer().getPluginManager().getPlugins())
		{
			for(int x = 0; x < permissionPlugins.length; x++)
			{
				if(permplug.getName().equalsIgnoreCase(permissionPlugins[x]))
				{
					PermissionPlugin = true;
				}
			}
		}
		
	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[])
    {
    	boolean succeed = false;
    	
    	if(PermissionPlugin == true)
    	{
    		if(cmd.getName().equalsIgnoreCase("reminer"))
    		{
    			if(sender.hasPermission("minebuilder.reminer"))
    			{
    				if(args.length == 0)
    				{
    					sender.sendMessage("[MineBuilder] Reload Configs");
    					ConfigCheck();
    					BlockSaveFile.stopTimer();
    					read.ReadAll();
    					joinquit.PluginReloaded();
    					breakBlock.iniInts();
    					placeBlock.iniInts();
    					slainEntity.iniInts();
    					fishing.iniInts();
    					sender.sendMessage("[MineBuilder] Configs reloaded");
    					succeed = true;
    				}
    				else
    				{
        				sender.sendMessage("[MineBuilder] You used to many arguments");
        				succeed = false;
    				}
    			}
    			else
    			{
    				sender.sendMessage("[MineBuilder] You haven´t the permissons to do this");
    				succeed = false;
    			}
    		}
    	}
    	else
    	{
    		if(cmd.getName().equalsIgnoreCase("reminer"))
    		{
    			if(sender.isOp())
    			{
    				if(args.length == 0)
    				{
    					sender.sendMessage("[MineBuilder] Reload Configs");
    					ConfigCheck();
    					BlockSaveFile.stopTimer();
    					read.ReadAll();
    					joinquit.PluginReloaded();
    					breakBlock.iniInts();
    					placeBlock.iniInts();
    					slainEntity.iniInts();
    					fishing.iniInts();
    					sender.sendMessage("[MineBuilder] Configs reloaded");
    					succeed = true;
    				}
    				else
    				{
        				sender.sendMessage("[MineBuilder] You used to many arguments");
        				succeed = false;
    				}
    			}
    			else
    			{
    				sender.sendMessage("[MineBuilder] You haven´t the permissons to do this");
    				succeed = false;
    			}
    		}
    	}
    	
    	
    	return succeed;
    }
	
}
