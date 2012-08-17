package me.eistee2.minebuilder;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class pistonMoveBlockEvent implements Listener{

	BlockSaver blockSaver = BlockSaver.getInstance();
	
	@EventHandler(priority = EventPriority.NORMAL)
	private void pistonMove(BlockPistonExtendEvent event)
	{
		int x = 0;
		int y = 0;
		int z = 0;
		if(event.getBlocks().size() > 0)
		{
			switch(event.getDirection())
			{
			case NORTH:
				x = -1;
				y = 0;
				z = 0;
				break;
			
			case SOUTH:
				x = 1;
				y = 0;
				z = 0;
				break;
			
			case UP:
				x = 0;
				y = 1;
				z = 0;
				break;
				
			case DOWN:
				x = 0;
				y = -1;
				z = 0;
				break;
				
			case EAST:
				x = 0;
				y = 0;
				z = -1;
				break;
				
			case WEST:
				x = 0;
				y = 0;
				z = 1;
				break;
			default:
				x = 0;
				y = 0;
				z = 0;
				break;		
			}
			for(Block block : event.getBlocks())
			{
				blockSaver.checkPistonLoc(block.getLocation(), block.getLocation().add(x, y, z));
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	private void pistonRetrackt(BlockPistonRetractEvent event)
	{
	
		if(event.isSticky()){
			int x = 0;
			int y = 0;
			int z = 0;
			
			switch(event.getDirection())
			{
			case NORTH:
				x = 1;
				y = 0;
				z = 0;
				break;
			
			case SOUTH:
				x = -1;
				y = 0;
				z = 0;
				break;
			
			case UP:
				x = 0;
				y = -1;
				z = 0;
				break;
				
			case DOWN:
				x = 0;
				y = 1;
				z = 0;
				break;
				
			case EAST:
				x = 0;
				y = 0;
				z = 1;
				break;
				
			case WEST:
				x = 0;
				y = 0;
				z = -1;
				break;
			default:
				x = 0;
				y = 0;
				z = 0;
				break;		
			}
			blockSaver.checkPistonLoc(event.getRetractLocation(), event.getRetractLocation().add(x, y, z));
		}
	}

}
