package net.BundR.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.BundR.plugin1.specialConfig;

public class Teams  implements CommandExecutor {
		
	@Override
	public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//alone1//player.yml");
		
		p.sendMessage(ChatColor.DARK_AQUA + "Teams:");
		p.sendMessage("");
		
		for (int i = 0; i < cfg.getInt("team"); i++) { 
			
			int e = i + 1; 
			
			String config = cfg.getString("team" + String.valueOf(e) + ".player1");	
			String myself = cfg.getString("Player" + config + ".name");
			
			int start = 0;
			for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
				if (p2.getName().equals(myself)) {
					start = 1;
				}
			}
				
			int myself_alive = cfg.getInt("Player" + config + ".alive");
			
			//p.sendMessage(myself);
			if (start == 1) {
				Player myselfP = Bukkit.getServer().getPlayer(myself);
				if (myself_alive == 1) {
					if (myselfP.isOnline()) {
						p.sendMessage(ChatColor.AQUA + myself + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_GREEN + " online"); 
					} else {
						p.sendMessage(ChatColor.AQUA + myself + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_RED + " offline"); 
					}
				} else {
					p.sendMessage(ChatColor.AQUA + myself + ": " + ChatColor.DARK_RED + "Tod ");
				}
			} else {
				if (myself_alive == 1) {
					p.sendMessage(ChatColor.AQUA + myself + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_RED + " offline");
				} else {
					p.sendMessage(ChatColor.AQUA + myself + ": " + ChatColor.DARK_RED + "Tod ");
				}
			}
			
			String PlayerId2 = cfg.getString("team" + String.valueOf(e) + ".player2");
			String teamm8UUID = cfg.getString("Player" + PlayerId2 + ".name");
			
			int start2 = 0;
			for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
				if (p2.getName().equals(teamm8UUID)) {
					start2 = 1;
				}
			}
			
			int teamm8UUID_alive = cfg.getInt("Player" + PlayerId2 + ".alive");
			
			if (start2 == 1)  {
				Player teamm8UUIDP = Bukkit.getServer().getPlayer(teamm8UUID);
				if (teamm8UUID_alive == 1) {
					if (teamm8UUIDP.isOnline()) {
						p.sendMessage(ChatColor.AQUA + teamm8UUID + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_GREEN + " online"); 
					} else {
						p.sendMessage(ChatColor.AQUA + teamm8UUID + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_RED + " offline"); 
					}
				} else {
					p.sendMessage(ChatColor.AQUA + teamm8UUID + ": " + ChatColor.DARK_RED + "Tod ");
				}
			
			}else {
				if (teamm8UUID_alive == 1) {
					p.sendMessage(ChatColor.AQUA + teamm8UUID + ": " + ChatColor.DARK_GREEN + "am Leben, " + ChatColor.DARK_RED + " offline");
				} else {
					p.sendMessage(ChatColor.AQUA + teamm8UUID + ": " + ChatColor.DARK_RED + "Tod ");
				}
			}
			
			//Player teamm8 = Bukkit.getServer().getPlayer(teamm8UUID);		
			

			
			p.sendMessage("");
		}
		
		return false;
	}

}
