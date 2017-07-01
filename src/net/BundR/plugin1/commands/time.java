package net.BundR.plugin1.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;
import net.BundR.plugin1.specialConfig;


public class time implements CommandExecutor {

	private plugin1 plugin;

	public time(plugin1 pl) {
		plugin = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//alone1//player.yml");
		
		//reset
		if (sender.isOp()) {
			if (args.length == 2) {
				if (args[0].equals(args[0])) {
					
					for (int i = 0; i < 8; i++) {
						
						Integer time = Integer.valueOf(args[1]);
						cfg.set("Player" + String.valueOf(i + 1) + ".time", time);
						specialConfig.saveConfig(cfg, "plugins//alone1//player.yml");
						
					} 
					
					sender.sendMessage("Alle Spieler Zeiten wurden zu " + args[1] + " zurückgesetzt");
					
					return false;

				}
					
			}
			
		
			if (args.length == 3) {
				if (args[0].equals(args[0])) {
				
					String PlayerId = getPlayerConfigId.fromName(args[2]);
					
					Integer time = Integer.valueOf(args[1]);
					cfg.set("Player" + PlayerId + ".time", time);
					specialConfig.saveConfig(cfg, "plugins//alone1//player.yml");
					
					sender.sendMessage(args[2] + " wurde zurückgesetzt");
					
					return false;

				}
			}
		
			if (args.length == 1) {
				if (args[0].equals(args[0])) {
					
					for (int i = 0; i < 8; i++) {
						
						int time = plugin.getConfig().getInt("NormalTime");
						cfg.set("Player" + String.valueOf(i + 1) + ".time", time);
						specialConfig.saveConfig(cfg, "plugins//alone1//player.yml");
						
					} 
					
					sender.sendMessage("Alle Spieler Zeiten wurden zurückgesetzt");
					
					return false;
					
				}
			}
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Du must ein Spieler sein um dies nutzen zu können!");
			return false;
		}
		
		String PlayerId = "BR";
		
		Player p = (Player) sender;
		
		PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p.getUniqueId()));
		
		int Zeit = cfg.getInt("Player" + PlayerId + ".time");
		int h = 0;
		int Min = 0;
		int Sek = 0;
		
		while(Zeit > 0) {
			
			if (3600 <= Zeit) {
				
				h = h + 1;
				Zeit = Zeit - 3600;
				
			} else {
				
				if ( 60 <= Zeit) {
					Min = Min + 1;
					Zeit = Zeit - 60;
					
				} else {
					
					Sek = Sek + 1;
					Zeit = Zeit -1;
				}
			}
				
		}
		
		if(h >= 1) {
			p.sendMessage(ChatColor.DARK_AQUA +"Du hast noch " + String.valueOf(h)  + " Stunde/n, " + String.valueOf(Min) + " Minute/n und " + String.valueOf(Sek) + " Sekunde/n Zeit!");
		} else {
			if (Min >= 1) {
				p.sendMessage(ChatColor.DARK_AQUA + "Du hast noch " + String.valueOf(Min) + " Minute/n und " + String.valueOf(Sek) + " Sekunde/n Zeit!");
			} else {
				p.sendMessage(ChatColor.DARK_AQUA + "Du hast noch " + String.valueOf(Sek) + " Sekunde/n Zeit!");
			}
		}
		
		
		return false;
	}
	
	
}