package net.BundR.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;


public class Alive implements CommandExecutor {
	
	private plugin1 plugin;

	public Alive(plugin1 pl) {
		plugin = pl;
	}
	
			
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 2) {
			if (sender.isOp()) {
				if (args[0].equals("add")) {
					
					String PlayerId = getPlayerConfigId.fromName(args[1], plugin);
					
					plugin.getConfig().set("Player" + PlayerId + ".alive", 1); 
					plugin.getConfig().set("Player" + PlayerId + ".aliveNew", 1); 
					plugin.saveConfig();
				
					sender.sendMessage(ChatColor.RED + args[1] +" wurde wiederbelebt!");
				
					return false;
				}
			}
		}
		
		sender.sendMessage(ChatColor.DARK_AQUA + "Noch am leben sind:");
		
		for(int i = 0; i < 8; i++) {
		
			int online = 0;
			
			if(plugin.getConfig().getInt("Player" + (i+1) +".alive") == 1) {
			
				for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					
					if (getPlayerConfigId.fromUUID(String.valueOf(p2.getUniqueId()), plugin).equals(String.valueOf(i + 1))) {
						online = 1;
					}
				}
				
				if (online == 1) {
					sender.sendMessage(ChatColor.AQUA + (String) plugin.getConfig().get("Player" + String.valueOf(i + 1) + ".name") + ": " + ChatColor.DARK_GREEN + "online");
				} else {
					sender.sendMessage(ChatColor.AQUA + (String) plugin.getConfig().get("Player" + String.valueOf(i + 1) + ".name") + ": " + ChatColor.DARK_RED + "offline");
				}
			
			}
			
			
		}
		
		
		return false;
		
	}

}
