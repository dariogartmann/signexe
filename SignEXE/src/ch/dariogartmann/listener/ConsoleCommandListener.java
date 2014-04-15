package ch.dariogartmann.listener;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import ch.dariogartmann.main.main;

public class ConsoleCommandListener implements org.bukkit.event.Listener {
	
	private main plugin;
	
	public ConsoleCommandListener(main instance){
		this.plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/*
	 * START ConsoleCommandExecutor
	 */
	@EventHandler
	public void onLever(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.LEVER){
				
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				String cmd = main.ccfg.getString("locations."+ loc);
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());

				}catch(NullPointerException ex){
					new_cmd = cmd;
				}

				if(main.ccfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.console.use")) {
	    				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), new_cmd);
					}
				}
			}			
		}
	}
	@EventHandler
	public void onstone_btn(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.STONE_BUTTON){
				
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				String cmd = main.ccfg.getString("locations."+ loc);
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());

				}catch(NullPointerException ex){
					new_cmd = cmd;
				}
				
				if(main.ccfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.console.use")) {
	    				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), new_cmd);
					}
				}
			}			
		}
	}
	@EventHandler
	public void onWood_btn(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.WOOD_BUTTON){
				
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				String cmd = main.ccfg.getString("locations."+ loc);
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());

				}catch(NullPointerException ex){
					new_cmd = cmd;
				}
				
				if(main.ccfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.console.use")) {
	    				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), new_cmd);
					}
				}
			}			
		}
	}
	/*
	 * END ConsoleCommandExecutor
	 */
	
	/* Prevent from breaking - BETA */
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b.getType() == Material.LEVER || b.getType() == Material.STONE_BUTTON || b.getType() == Material.WOOD_BUTTON) {
			Location loc = b.getLocation();
			if(main.ccfg.get("locations." + loc) != null){
				if(p.hasPermission("signexe.cmdexecutor.break")) {
					
					main.cfg.getConfigurationSection("locations.").set(loc.toString(), null);
					
					p.sendMessage(ChatColor.AQUA + "Destroyed a Commandexecutor " + ChatColor.RED + "(CONSOLE)" + ChatColor.AQUA + "(" + b.getType().toString() + ")");
					
					try{
						main.ccfg.save(main.cfile);
					}catch(IOException e1) {
						p.sendMessage(ChatColor.RED + "An error occured while saving the file!");
						System.out.println(ChatColor.RED + "[SignEXE] An error occured! Please report it on:");
						System.out.println("http://dev.bukkit.org/bukkit-plugins/signexe/");
					}
				}else{
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED+ "You don't have the permission to break a Commandexecutor(C).");
				}
			}
			
		}
	}
	
	
	
	

}
