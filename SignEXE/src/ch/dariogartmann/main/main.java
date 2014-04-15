package ch.dariogartmann.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ch.dariogartmann.cmd.setcmdCommand;
import ch.dariogartmann.cmd.setconsolecmd;
import ch.dariogartmann.cmd.signEXECommand;
import ch.dariogartmann.listener.ConsoleCommandListener;
import ch.dariogartmann.listener.CommandListener;

public class main extends JavaPlugin {

	/*
	 * SignEXE
	 * -------------------------
	 * Full command usage on:
	 * http://dariogartmann.ch/signexe
	 * and
	 * http://dev.bukkit.org/bukkit-plugins/signexe/
	 */
	
	
	//File for player-executors
	public static File file = new File("plugins/SignEXE", "loc.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	//File for console-executors
	public static File cfile = new File("plugins/SignEXE", "consoleloc.yml");
	public static FileConfiguration ccfg = YamlConfiguration.loadConfiguration(cfile);
	
	
	public void onEnable() {
		System.out.println("[SignEXE] Up and running!");
		
		/* register all commands */
		this.getCommand("signexe").setExecutor(new signEXECommand());
		this.getCommand("setcmd").setExecutor(new setcmdCommand());
		this.getCommand("setconsolecmd").setExecutor(new setconsolecmd());
		
		/* register both Listeners in package ch.dariogartmann.listener */
		registerCommandListener();
		registerConsoleCommandListener();
	

	}
	
	public void onDisable() {
		System.out.println("[SignEXE] Disabled!");
		
		/* Try to save files. */
		try{
			cfg.save(file);
			ccfg.save(cfile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	private CommandListener PlayerListener;
	private void registerCommandListener() {
		PlayerListener = new CommandListener(this);
	}
	
	private ConsoleCommandListener Listener;
	private void registerConsoleCommandListener() {
		Listener = new ConsoleCommandListener(this);
	}
	
	

}
