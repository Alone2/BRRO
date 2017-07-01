package net.BundR.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.specialConfig;


public class Alive implements CommandExecutor {
			
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//alone1//player.yml");
		
		if (args.length == 2) {
			if (sender.isOp()) {
				if (args[0].equals("add")) {
					
					String PlayerId = getPlayerConfigId.fromName(args[1]);
					
					cfg.set("Player" + PlayerId + ".alive", 1); 
					cfg.set("Player" + PlayerId + ".aliveNew", 1); 
					specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 
				
					sender.sendMessage(ChatColor.RED + args[1] +" wurde wiederbelebt!");
				
					return false;
				}
			}
		}
		
		sender.sendMessage(ChatColor.DARK_AQUA + "Noch am leben sind:");
		
		for(int i = 0; i < 8; i++) {
		
			int online = 0;
			
			if(cfg.getInt("Player" + (i+1) +".alive") == 1) {
			
				for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					
					if (getPlayerConfigId.fromUUID(String.valueOf(p2.getUniqueId())).equals(String.valueOf(i + 1))) {
						online = 1;
					}
				}
				
				if (online == 1) {
					sender.sendMessage(ChatColor.AQUA + (String) cfg.get("Player" + String.valueOf(i + 1) + ".name") + ": " + ChatColor.DARK_GREEN + "online");
				} else {
					sender.sendMessage(ChatColor.AQUA + (String) cfg.get("Player" + String.valueOf(i + 1) + ".name") + ": " + ChatColor.DARK_RED + "offline");
				}
			
			}
			
			
		}
		
		
		return false;
		
	}

}
