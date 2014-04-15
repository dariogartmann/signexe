package ch.dariogartmann.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class signEXECommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player)sender;
		
		//basic info command
		
		p.sendMessage("§8[#]=======[" + "§bSignEXE - Developed by D4rio" + "§8]=======[#]");
		p.sendMessage("§7How it works:");
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Commandsign");
		p.sendMessage("§71st line of sign:" + " §b[SignEXE]" + " §7or " + "§b[SignEXE] §cC");
		p.sendMessage("§7Other lines: " + "§e<command without the />");
		p.sendMessage("");
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Commandexecutor "+ChatColor.RESET+"§7(Button or lever)");
		p.sendMessage(ChatColor.GRAY + "Look at a button or lever and do §e/setcmd <command without the />");
		p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "-> §cCONSOLE: " + "§e/setconsolecmd <command without the />");
		return true;
	}

}
