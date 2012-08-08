package me.eistee2.minebuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class configer {

	private BufferedReader br;
	private BufferedWriter bw;
	private File myFile; //File which get createt / readed
	private File myFolder; //FolderPath if it dosent exist
	String freizeile = System.getProperty("line.separator"); // Used to make a line breaks (make it possible to edit with TextEditor)
	
	public configer(String PathName,String FileName)
	{
		myFolder = new File(PathName);
		myFile = new File(FileName);
	}
	
	//Read myFile and return the content
	public String fileGetContent()
    {
        String buffer = "";
        String line = "";
        
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));
            while((line = br.readLine()) != null) 
                buffer = (new StringBuilder(String.valueOf(buffer))).append(line = (new StringBuilder(String.valueOf(line))).append("\n").toString()).toString();
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("[MineBuilder] A File is missing pleas reload when this error appears again");
        }
        return buffer;
    }
	
	
	//Write everything into the file 
	public void writeFile(String toWrite)

    {

        try
        {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(myFile)));
            bw.append(toWrite);
            bw.newLine();
            bw.close();
        }
        catch(Exception exception) { }
    }
	
	// Check if myFile exist
	public boolean configCheck()
	{
		if(myFile.exists() == true)
		{
			return true;
		}else
		{
			return false;
		}
		
		
	}
	//Creat the setting file
	public void creatSettings()
	{
		try
		{
			myFolder.mkdirs();
			
			myFile.createNewFile();
			
			writeFile("Version:1.2"+freizeile+"---------------------"+freizeile+"MAXplayer:200"+freizeile+"MAXblocksaved:100"+freizeile+"Use Permissions:false"+freizeile+"Vip percentage:0"
			+freizeile+"---------------------"+freizeile+"Give Exp:true"
			+freizeile+"(general option)"+freizeile+"Use MinerExp:true"+freizeile+"Use BuilderExp:true"+freizeile+"Use FishingExp:true"+freizeile+"Use MobExp:true"
					+freizeile+"---------------------"+freizeile
					+"Give Money:false"
					+freizeile+"(general option)"+freizeile+"Use MinerMoney:true"+freizeile+"Use BuilderMoney:true"+freizeile+"Use FishingMoney:true"+freizeile+"Use MobMoney:true");
		}catch(Exception e)
		{
			
			System.out.println("[MineBuilder] Failure to create a ExpConfig");
			
		}
		
	}
	
	//Creats The exp Config
	public void creatExpConfig()
	{
		try
		{
			myFolder.mkdirs();
			
			myFile.createNewFile();
			
			writeFile("# When you use levels it will change the percentage so when you are lvl 10 yo will get 10% percent more exp each block"
			           +freizeile+"# You can add every block! you need to make it like this ItemID;BlockID/Name(Like in my older version):EXP:How often"
					   +freizeile+"# ItemID;1:5:10 here you will get 5 exp after breaking/placeing 10 blocks"
			           +freizeile+"# You can Write the name of the block under or over it but NEVER write it all upercase may its written like in Minecraft and then it throws errors"
			           +freizeile+"# you can use randoms like this ItemID:01:lowexp:10 it will give you a amount of between 5-10 you can add randoms the name does not matter"
					   +freizeile+freizeile
					   
					   +"---------------------"+freizeile+"Levels:"+freizeile+"---------------------"
			           +freizeile+"Use Level:true"+freizeile+"MAXlvl:200"+freizeile+"lvl1:0"+freizeile+"lvl10:10"+freizeile+"lvl20:20"+freizeile+"lvl30:30"
					   +freizeile
					   
					   +freizeile+"---------------------"+freizeile+"Randoms:"+freizeile+"---------------------"
					   +freizeile+"lowexp:5:10"+freizeile+"highexp:10:15"+freizeile+"random:1:100"+freizeile+freizeile
					   
					   +"---------------------"+freizeile+"Break Block:"+freizeile+"---------------------"
					   +freizeile+"Stone"+freizeile+"ItemID;1:10:10"+freizeile+freizeile+"Coal ore"+freizeile+"ItemID;16:5:6"+freizeile+freizeile+"Gold ore"+freizeile+"ItemID;14:6:3"+freizeile
					   +freizeile+"Iron ore"+freizeile+"ItemID;15:4:3"+freizeile+freizeile+"Lapis ore"+freizeile+"ItemID;21:6:4"+freizeile+freizeile+"Netherrack"+freizeile+"ItemID;87:5:15"+freizeile
					   +freizeile+"Glowstone"+freizeile+"ItemID;89:5:4"+freizeile+freizeile+"Soul Sand"+freizeile+"ItemID;88:5:10"+freizeile+freizeile+"Nether brick"+freizeile+"ItemID;112:5:10"+freizeile+freizeile
					   +freizeile+"Redstone"+freizeile+"ItemID;74:4:8"+freizeile+freizeile+"Diamond ore"+freizeile+"ItemID;56:20:1"+freizeile+freizeile+"Obsidian"+freizeile+"ItemID;49:6:1"+freizeile+freizeile
					   
                       +"---------------------"+freizeile+"Place Block:"+freizeile+"---------------------"
                       +freizeile+"Dirt"+freizeile+"ItemID;3:4:1"+freizeile+freizeile+"Wood"+freizeile+"ItemID;17:4:3"+freizeile+freizeile+"Wooden Planks"+freizeile+"ItemID;5:2:7"+freizeile+freizeile+"Wool"+freizeile+"ItemID;35:4:10"+freizeile
                       +freizeile+"Stone"+freizeile+"ItemID;1:1:5"+freizeile+freizeile+"Cobblestone"+freizeile+"ItemID;4:2:6"+freizeile+freizeile+"Glass"+freizeile+"ItemID;20:4:10"+freizeile+freizeile+"Glass Pane"+freizeile+"ItemID;102:4:10"+freizeile
                       +freizeile+"Netherrack"+freizeile+"ItemID;87:5:15"+freizeile+freizeile+"Glowstone"+freizeile+"ItemID;89:5:4"+freizeile
                       +freizeile+"Soul Sand"+freizeile+"ItemID;88:5:10"+freizeile+freizeile+"Stone bricks"+freizeile+"ItemID;98:5:10"+freizeile+freizeile
                       +"Fence"+freizeile+"ItemID;85:2:7"+freizeile+freizeile+"Fence Gate"+freizeile+"ItemID;107:1:2"+freizeile+freizeile+"Nether Brick Fence"+freizeile+"ItemID;113:3:2"+freizeile
                       +freizeile+"Nether Brick"+freizeile+"ItemID;112:5:10"+freizeile+freizeile+"Nether Brick Stairs"+freizeile+"ItemID;114:4:4"+freizeile+freizeile+"Wood Stairs"+freizeile+"ItemID;53:2:4"+freizeile+freizeile
                       +"Cobblestone Stairs"+freizeile+"ItemID;67:3:4"+freizeile+freizeile+"Brick Stairs"+freizeile+"ItemID;108:4:4"+freizeile+freizeile+"Stone Brick Stairs"+freizeile+"ItemID;109:4:4"+freizeile
                       +freizeile+"Obsidian"+freizeile+"ItemID;49:6:1"+freizeile+freizeile
                       
                       +"---------------------"+freizeile+"Slain Mobs:"+freizeile+"---------------------"
                       +freizeile+"ItemID;Chicken:1:2"+freizeile+freizeile+"ItemID;Cow:2:1"+freizeile+freizeile+"ItemID;Pig:1:1"+freizeile+freizeile+"ItemID;Sheep:1:1"+freizeile
                       +freizeile+"ItemID;Squid:1:2"+freizeile+freizeile+"ItemID;Wolf:1:1"+freizeile+freizeile+"ItemID;Ozelot:1:1"+freizeile+freizeile+"ItemID;Village:0:1"+freizeile
                       +freizeile+"ItemID;VillagerGolem:3:1"+freizeile+freizeile+"ItemID;SnowMan:1:1"+freizeile+freizeile+"ItemID;Silverfish:2:1"+freizeile+freizeile+"ItemID;Spieder:1:1"+freizeile
                       +freizeile+"ItemID;Skeleton:2:1"+freizeile+freizeile+"ItemID;Creeper:3:1"+freizeile
                       +freizeile+"ItemID;Blaze:5:1"+freizeile+freizeile+"ItemID;Zombie:1:2"+freizeile+freizeile+"ItemID;PigZombie:1:1"+freizeile+freizeile+"ItemID;Ghast:5:1"+freizeile
                       +freizeile+"ItemID;Slime:1:4"+freizeile+freizeile+"ItemID;LavaSlime:2:4"+freizeile
                       +freizeile+"ItemID;Enderman:5:1"+freizeile+freizeile+"ItemID;EnderDragon:500:1"+freizeile+freizeile+
                       "---------------------"+freizeile+"Fishing:"+freizeile+"---------------------"+freizeile+"ItemID;Fish:1:1"
					);
		}catch(Exception e)
		{
			
			System.out.println("[MineBuilder] Failure to create a ExpConfig");
			
		}
		
	}
	
	//Creats a MoneyConfig
	public void creatMoneyConfig()
	{
		try
		{
			myFolder.mkdirs();
			
			myFile.createNewFile();
			
			writeFile("# You can add every block! you need to make it like this ItemID;BlockID/Name(Like in my older version):MONEY:How often"
					   +freizeile+"# ItemID;01:5:10 here you will get 5$ after breaking/placeing 10 blocks"
			           +freizeile+"# You can Write the name of the block under or over it but NEVER write it all upercase may its written like in Minecraft and then it throws errors"
			           +freizeile+"# you can use randoms like this ItemID:01:lowexp:10 it will give you a amount of between 5-10 you can add randoms the name does not matter"
					   +freizeile
					   
					   +freizeile+"---------------------"+freizeile+"Randoms:"+freizeile+"---------------------"
					   +freizeile+"lowexp:5:10"+freizeile+"highexp:10:15"+freizeile+"random:1:100"+freizeile+freizeile
					   
					   +"---------------------"+freizeile+"Break Block:"+freizeile+"---------------------"
					   +freizeile+"Stone"+freizeile+"ItemID;1:10:10"+freizeile+freizeile+"Coal ore"+freizeile+"ItemID;16:5:6"+freizeile+freizeile+"Gold ore"+freizeile+"ItemID;14:6:3"+freizeile
					   +freizeile+"Iron ore"+freizeile+"ItemID;15:4:3"+freizeile+freizeile+"Lapis ore"+freizeile+"ItemID;21:6:4"+freizeile+freizeile+"Netherrack"+freizeile+"ItemID;87:5:15"+freizeile
					   +freizeile+"Glowstone"+freizeile+"ItemID;89:5:4"+freizeile+freizeile+"Soul Sand"+freizeile+"ItemID;88:5:10"+freizeile+freizeile+"Nether brick"+freizeile+"ItemID;112:5:10"+freizeile+freizeile
					   +freizeile+"Redstone"+freizeile+"ItemID;74:4:8"+freizeile+freizeile+"Diamond ore"+freizeile+"ItemID;56:20:1"+freizeile+freizeile+"Obsidian"+freizeile+"ItemID;49:6:1"+freizeile+freizeile
					   
                       +"---------------------"+freizeile+"Place Block:"+freizeile+"---------------------"
                       +freizeile+"Dirt"+freizeile+"ItemID;3:4:1"+freizeile+freizeile+"Wood"+freizeile+"ItemID;17:4:3"+freizeile+freizeile+"Wooden Planks"+freizeile+"ItemID;5:2:7"+freizeile+freizeile+"Wool"+freizeile+"ItemID;35:4:10"+freizeile
                       +freizeile+"Stone"+freizeile+"ItemID;1:1:5"+freizeile+freizeile+"Cobblestone"+freizeile+"ItemID;4:2:6"+freizeile+freizeile+"Glass"+freizeile+"ItemID;20:4:10"+freizeile+freizeile+"Glass Pane"+freizeile+"ItemID;102:4:10"+freizeile
                       +freizeile+"Netherrack"+freizeile+"ItemID;87:5:15"+freizeile+freizeile+"Glowstone"+freizeile+"ItemID;89:5:4"+freizeile
                       +freizeile+"Soul Sand"+freizeile+"ItemID;88:5:10"+freizeile+freizeile+"Stone bricks"+freizeile+"ItemID;98:5:10"+freizeile+freizeile
                       +"Fence"+freizeile+"ItemID;85:2:7"+freizeile+freizeile+"Fence Gate"+freizeile+"ItemID;107:1:2"+freizeile+freizeile+"Nether Brick Fence"+freizeile+"ItemID;113:3:2"+freizeile
                       +freizeile+"Nether Brick"+freizeile+"ItemID;112:5:10"+freizeile+freizeile+"Nether Brick Stairs"+freizeile+"ItemID;114:4:4"+freizeile+freizeile+"Wood Stairs"+freizeile+"ItemID;53:2:4"+freizeile+freizeile
                       +"Cobblestone Stairs"+freizeile+"ItemID;67:3:4"+freizeile+freizeile+"Brick Stairs"+freizeile+"ItemID;108:4:4"+freizeile+freizeile+"Stone Brick Stairs"+freizeile+"ItemID;109:4:4"+freizeile
                       +freizeile+"Obsidian"+freizeile+"ItemID;49:6:1"+freizeile+freizeile
                       
                       +"---------------------"+freizeile+"Slain Mobs:"+freizeile+"---------------------"
                       +freizeile+"ItemID;Chicken:1:2"+freizeile+freizeile+"ItemID;Cow:2:1"+freizeile+freizeile+"ItemID;Pig:1:1"+freizeile+freizeile+"ItemID;Sheep:1:1"+freizeile
                       +freizeile+"ItemID;Squid:1:2"+freizeile+freizeile+"ItemID;Wolf:1:1"+freizeile+freizeile+"ItemID;Ozelot:1:1"+freizeile+freizeile+"ItemID;Village:0:1"+freizeile
                       +freizeile+"ItemID;VillagerGolem:3:1"+freizeile+freizeile+"ItemID;SnowMan:1:1"+freizeile+freizeile+"ItemID;Silverfish:2:1"+freizeile+freizeile+"ItemID;Spieder:1:1"+freizeile
                       +freizeile+"ItemID;Skeleton:2:1"+freizeile+freizeile+"ItemID;Creeper:3:1"+freizeile
                       +freizeile+"ItemID;Blaze:5:1"+freizeile+freizeile+"ItemID;Zombie:1:2"+freizeile+freizeile+"ItemID;PigZombie:1:1"+freizeile+freizeile+"ItemID;Ghast:5:1"+freizeile
                       +freizeile+"ItemID;Slime:1:4"+freizeile+freizeile+"ItemID;LavaSlime:2:4"+freizeile
                       +freizeile+"ItemID;Enderman:5:1"+freizeile+freizeile+"ItemID;EnderDragon:500:1"+freizeile+freizeile+
                       "---------------------"+freizeile+"Fishing:"+freizeile+"---------------------"+freizeile+"ItemID;Fish:1:1"	   
					);
		}catch(Exception e)
		{
			
			System.out.println("[MineBuilder] Failure to create a MoneyConfig");
			
		}
		
	}

	
	
}
