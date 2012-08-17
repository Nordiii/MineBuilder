package me.eistee2.minebuilder;

public class changeConfigFiles {
	configer settingConfig = new configer("plugins/MineBuilder","plugins/MineBuilder/Settings.yml");
	configer expConfig = new configer("plugins/MineBuilder","plugins/MineBuilder/ExpConfig.yml");
	configer moneyConfig = new configer("plugins/MineBuilder","plugins/MineBuilder/MoneyConfig.yml");
	String freizeile = System.getProperty("line.separator");
	
	public void CheckForConfigUpdate()
	{
		checkSettings();
		checkExpConfig();
		checkMoneyConfig();
	}
	
	private void checkSettings()
	{
		String settingsContent = settingConfig.fileGetContent();
		if(!settingsContent.contains("Setting Version:1.4.1"))
		{
			boolean add = false;
			String writeToFile = "Setting Version:1.4.1"+freizeile;
			if(settingsContent.contains("Setting Version:1.4"))
			{
				String[] settingSplit = settingsContent.split("\n");
				for(String search : settingSplit)
				{
					if(search.contains("---"))
					{
						add = true;
					}
					if(search.contains("Save Blocks to File:"))
					{
						String[] SearchSplit = search.split(":");
						search = "Save Blocks to file on server reload/stop:"+ SearchSplit[1];
					}
					if(search.contains("Block save intervall:"))
					{
						String[] SearchSplit = search.split(":");
						search = "Block save intervall in minutes:"+ SearchSplit[1];
					}
					if(add == true)
					{
						writeToFile += search+freizeile;
					}
				}
				settingConfig.writeFile(writeToFile);
				return;
			}
			String[] settingSplit = settingsContent.split("\n");
			for(String search : settingSplit)
			{
				if(search.contains("---"))
				{
					add = true;
				}
				if(add == true)
				{
					writeToFile += search+freizeile;
				}
				if(search.contains("MAXblocksaved:"))
				{
					writeToFile += "Save Blocks to File on server reload/stop:false"+freizeile+"Block save intervall in minutes:0"+freizeile+freizeile;
				}
				
				if(search.contains("Use MobExp:"))
				{
					writeToFile += freizeile + freizeile + "Spawn ExpOrbs for break Blocks:true" 
				                 + freizeile + "Spawn ExpOrbs for place Blocks:false"+freizeile;
				}
			}
			settingConfig.writeFile(writeToFile);
		}
	}
	
	private void checkExpConfig()
	{
		Boolean add = false;
		
		Boolean writeRandoms = false;
		
		String writeToFile = "Config Version:1.3"+freizeile+"# When you use levels it will change the percentage so when you are lvl 10 yo will get 10% percent more exp each block"
					   +freizeile+"# ItemID;1:5:10 here you will get 5 exp after breaking/placeing 10 blocks"
			           +freizeile+"# You can Write the name of the block under or over it but NEVER write it all upercase may its written like in Minecraft and then it throws errors"
			           +freizeile+"# you can use randoms like this ItemID:01:lowexp:10 it will give you a amount of between 5-10 you can add randoms the name does not matter"
					   +freizeile+freizeile+"# You can add every block! you need to make it like this ItemID;BlockID:EXP:How many blocks to break"+freizeile+freizeile;
		String expContent = expConfig.fileGetContent();
		if(expContent.contains("Config Version:1.3"))
		{
			return;
		}
		else
		{
			String[] expSplit = expContent.split("\n");
			for(String search : expSplit)
			{
				if(search.contains("----"))
				{
					add = true;
				}
				
				//---------------------Change Randoms-----------------//
				if(search.contains("Break Block:"))
				{
					writeRandoms = false;
				}
				if(search.contains(":") && writeRandoms == true)
				{
					String[] expAdd = search.split(":");
				      search = expAdd[0]+";"+expAdd[1]+":"+expAdd[2];
				}
				if(search.equalsIgnoreCase("Randoms:"))
				{
					writeRandoms = true;
				}
	
				
				if(add == true)
				{
					writeToFile += search+freizeile;
				}
			}

		}
		expConfig.writeFile(writeToFile);
	}
	
	private void checkMoneyConfig()
	{
		Boolean add = false;
		
		Boolean writeRandoms = false;		
		
		String writeToFile = "Config Version:1.3"+freizeile+"# ItemID;01:5:10 here you will get 5$ after breaking/placeing 10 blocks"
		           +freizeile+"# You can Write the name of the block under or over it but NEVER write it all upercase may its written like in Minecraft and then it throws errors"
		           +freizeile+"# you can use randoms like this ItemID:01:lowexp:10 it will give you a amount of between 5-10 you can add randoms the name does not matter"+freizeile
				   +freizeile+"# You can add every block! you need to make it like this ItemID;BlockID:MONEY:How many block you must break"+freizeile+freizeile;
		String expContent = moneyConfig.fileGetContent();
		if(expContent.contains("Config Version:1.3"))
		{
			return;
		}
		else
		{
			String[] expSplit = expContent.split("\n");
			for(String search : expSplit)
			{
				if(search.contains("----"))
				{
					add = true;
				}
				
				//---------------------Change Randoms-----------------//
				if(search.contains("Break Block:"))
				{
					writeRandoms = false;
				}
				if(search.contains(":") && writeRandoms == true)
				{
					String[] expAdd = search.split(":");
				      search = expAdd[0]+";"+expAdd[1]+":"+expAdd[2];
				}
				if(search.equalsIgnoreCase("Randoms:"))
				{
					writeRandoms = true;
				}
	
				
				if(add == true)
				{
					writeToFile += search+freizeile;
				}
			}

		}
		moneyConfig.writeFile(writeToFile);
	}

}
