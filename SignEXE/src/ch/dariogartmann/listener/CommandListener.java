package ch.dariogartmann.listener;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.material.Lever;

import ch.dariogartmann.main.main;


public class CommandListener implements Listener {
	
	private main plugin;
	
	public CommandListener(main instance){
		this.plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/*
	 * Called when player creates a SignExecutor
	 */
	@EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
 
        if (e.getLine(0).equalsIgnoreCase("[SignEXE]")) {
        	if(p.hasPermission("signexe.create")) {
        	
        		
               	e.setLine(0, "§b[SignEXE]");
                p.sendMessage("§bSuccessfully created a SignExecutor.");
                p.sendMessage("§bCommand: /" + e.getLine(1) + e.getLine(2) + e.getLine(3));
     
        	}else{
        		e.setCancelled(true);
        		p.sendMessage("§cNo.");
        	}
 
        } 
        if(e.getLine(0).equalsIgnoreCase("[SignEXE] C")) {
        	if(p.hasPermission("signexe.console.create")) {
        		e.setLine(0, "§b[SignEXE] §cC");
                p.sendMessage("§bSuccessfully created a SignExecutor"+ "§c(CONSOLE)"+"§b.");
                p.sendMessage("§bCommand: /" + e.getLine(1) + e.getLine(2) + e.getLine(3));
        	}else{
        		e.setCancelled(true);
        		p.sendMessage("§cNo.");
        	}
        }
 
    }
	
	/*
	 * START CommandExecutor
	 */
	
	@EventHandler
	public void onLever(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.LEVER){
				
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				String cmd = main.cfg.getString("locations." + loc);		
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());
					
				}catch(NullPointerException ex){
					new_cmd = cmd;
				}
				
				if(main.cfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.use")) {
						p.performCommand(new_cmd);
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
				String cmd = main.cfg.getString("locations." + loc);		
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());
					
				}catch(NullPointerException ex){
					new_cmd = cmd;
				}
				
				if(main.cfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.use")) {
						p.performCommand(new_cmd);
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
				String cmd = main.cfg.getString("locations." + loc);		
				String new_cmd = "";
				try{
					new_cmd = cmd.replaceAll("@p", p.getName());
					
				}catch(NullPointerException ex){
					new_cmd = cmd;
				}
				
				if(main.cfg.get("locations." + loc) != null) {
					if(p.hasPermission("signexe.cmdexecutor.use")) {
						p.performCommand(new_cmd);
					}
				}
			}			
		}
	}
	/*
	 * END CommandExecutor
	 */
	
	/* Prevent from breaking - BETA */
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b.getType() == Material.LEVER || b.getType() == Material.STONE_BUTTON || b.getType() == Material.WOOD_BUTTON) {
			Location loc = b.getLocation();
			if(main.cfg.get("locations." + loc) != null){
				if(p.hasPermission("signexe.cmdexecutor.break")) {
					p.sendMessage(ChatColor.AQUA + "Destroyed a Commandexecutor (" + b.getType().toString() + ")");
					
					main.cfg.getConfigurationSection("locations.").set(loc.toString(), null);
					
					try{
						main.cfg.save(main.file);
					}catch(IOException e1) {
						p.sendMessage(ChatColor.RED + "An error occured while saving the file!");
						System.out.println(ChatColor.RED + "[SignEXE] An error occured! Please report it on:");
						System.out.println("http://dev.bukkit.org/bukkit-plugins/signexe/");
					}
				}else{
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED+"You don't have the permission to break a Commandexecutor.");
				}
			}
			
		}
	}

	
	/*
	 * Sign - Executor
	 */
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
 
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;
 
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§b[SignEXE]")) {
            	if(p.hasPermission("signexe.use")) {
            		
            		String cmd = s.getLine(1)+ s.getLine(2) + s.getLine(3);
            	
            		p.performCommand(cmd);	
            	}else{
            		p.sendMessage("§cYou can't use '" + "§bSignExecutor§c'");
            	}
            	
            } 
            if(s.getLine(0).equalsIgnoreCase("§b[SignEXE] §cC")) {
            	if(p.hasPermission("signexe.console.use")) {
            		
            		String cmd = s.getLine(1)+ s.getLine(2) + s.getLine(3);
            	
    				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);

            	}else{
            		p.sendMessage("§cYou can't use '" + "§bSignExecutor - Console§c'");
            	}
            }   
        }   
    }
}
