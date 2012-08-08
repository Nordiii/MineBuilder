package me.eistee2.minebuilder;

import java.util.ArrayList;

import org.bukkit.Bukkit;

public class expCalculator {

	ReadOut getlvl = ReadOut.getInstance();
	
	
	public int CalculateExp(int playerlvl,int exp,String playername)
	{
		if(getlvl.useLevel() == false)
		{
			return exp;
		}
		Boolean lvlLoop = false;
		int CalculatedExp = exp;
		int percentage = 0;

		ArrayList<String> levels = getlvl.getLevels();
		
		if(Bukkit.getPlayer(playername).getLevel()>0)
		{
			while(lvlLoop == false)
			{
				for (String lvl : levels) 
				{
					if (lvl.indexOf("lvl"+playerlvl) > -1)
					{
						String[] percentSplit = lvl.split(":");
						percentage = Integer.parseInt(percentSplit[1]);
						percentage = (percentage / 100) + 1;
						lvlLoop = true;
					}
				}
				playerlvl -=1;
			}
			exp = exp * percentage;
			if(getlvl.getPermissionStat() == true)
			{
				exp = exp * ((getlvl.getVipPercentge()/100) + 1);
			}
			CalculatedExp = Math.round(exp);
		}
		
		return CalculatedExp;
	}
	
	
}
