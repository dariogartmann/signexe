package ch.dariogartmann.cmd;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Lever;

import ch.dariogartmann.main.main;

public class setcmdCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("[SignEXE] You aren't a player.");
			return true;
		}
		
		Player p = (Player)sender;  //the player who performed the cmd
		
		if(p.hasPermission("signexe.cmdexecutor.create")) {  //check if p has permission
			
			/* block the player is looking at */
			Block b = p.getTargetBlock(null, 100);  
			
			/* b's location */
			Location loc = b.getLocation();         
			
			if(b.getType() == Material.LEVER || b.getType() == Material.STONE_BUTTON || b.getType() == Material.WOOD_BUTTON) {  //if p clicked on a button, lever, ...
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /setcmd <command>"); //not enough arguments
				}else{
					
					StringBuilder cmdString = new StringBuilder();
					cmdString.append(args[0]);
				
					for(int ii = 1; ii < args.length; ii++){
						cmdString.append(" ");
						cmdString.append(args[ii]);	
					}
					
					main.cfg.set("locations." + loc, cmdString.toString());      //save to config

					try{
						main.cfg.save(main.file);   
						
						/* Send notifications to player */
						p.sendMessage(ChatColor.AQUA + "Successfully created a CommandExecutor (" + b.getType().toString() + ")");
						p.sendMessage(ChatColor.AQUA + "Command: /" + cmdString.toString());
						
						/* Special effects \o/ */
						p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 100, 10);
						p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, 10);

					}catch(IOException e) {
						p.sendMessage(ChatColor.RED + "An error occured while saving the file!");
						System.out.println(ChatColor.RED + "[SignEXE] An error occured! Please report it on:");
						System.out.println("http://dev.bukkit.org/bukkit-plugins/signexe/");
					}	
				}
				
			}else{
				p.sendMessage(ChatColor.RED + "The block you are looking at must be a lever or button!");  
			}
		}else{
			p.sendMessage("§cPermission denied.");
		}
		return true;
	}

}
