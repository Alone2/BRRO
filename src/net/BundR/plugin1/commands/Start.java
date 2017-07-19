package net.BundR.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;
import net.BundR.plugin1.specialConfig;

public class Start implements CommandExecutor {
	
	String PlayerId = "BUG";

	private plugin1 plugin;
	
	private BukkitTask loop1;

	public Start(plugin1 pl) {
		plugin = pl;
	}
	
	int Zehn = 22;

	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//BRRO//player.yml");
		FileConfiguration cfg2 = specialConfig.config("plugins//BRRO//data.yml");
		
		if(sender.isOp()) {
			
			if (cfg2.getInt("start") == 1) {
				sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("name") + " hat schon begonnen!");
				return false;
			}
		
			if (cfg2.getInt("WieViele") / 2 != cfg2.getInt("team")) {
				for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					p2.sendMessage(ChatColor.RED + "Es sind noch nicht alle in einem Team! " + plugin.getConfig().getString("name") + " kann somit noch nicht gestartet werden!");
				}
				return false;
			}

			for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
			
				p2.setGameMode(GameMode.SURVIVAL);
			
				PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p2.getUniqueId()));	
				String Team = getPlayerConfigId.getTeam(PlayerId);
			
				Location tp = p2.getLocation();
				
				tp.setX(cfg.getDouble("team"+ Team + "_spawnpoint.X"));
				tp.setY(cfg.getDouble("team"+ Team + "_spawnpoint.Y"));
				tp.setZ(cfg.getDouble("team"+ Team + "_spawnpoint.Z"));
		
				p2.teleport(tp);
				//p2.sendMessage(String.valueOf(tp));

			}
		
		loop1 = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

			@Override
			public void run() {
				if (Zehn == 0) {
					for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
						
						p2.sendTitle(ChatColor.RED + String.valueOf(Zehn),ChatColor.RED + "LET'S GO!", 20, 80, 20);
						p2.playSound(p2.getLocation(), Sound.ENTITY_PLAYER_LEVELUP , 3.0F, 0.5F);
						cfg2.set("start", 1);
						for (int i = 0; i < 8; i++) { 
							int e = i + 1; 
							cfg.set("Player" + String.valueOf(e) + ".schutz", 1); 
						}
						specialConfig.saveConfig(cfg2, "plugins//BRRO//data.yml"); 
						specialConfig.saveConfig(cfg, "plugins//BRRO//player.yml"); 
						
						PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p2.getUniqueId()));
						
						Location tp = p2.getLocation();
						tp.setX(cfg.getDouble("Player"+ PlayerId + ".spawnpoint.X"));
						tp.setY(cfg.getDouble("Player"+ PlayerId + ".spawnpoint.Y") - 1);
						tp.setZ(cfg.getDouble("Player"+ PlayerId + ".spawnpoint.Z"));
						
						tp.getBlock().setType(Material.AIR);
						
						p2.sendTitle(ChatColor.DARK_AQUA + "Schutzzeit",ChatColor.AQUA + "endet in " + String.valueOf(plugin.getConfig().getInt("SchutzTime") + 5) + " Sekunden", 20, 80, 20);
						
						//loop1.cancel();
						
						
					}
				} else {
					
					if (Zehn == 13) {
						for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
							p2.sendTitle(ChatColor.DARK_AQUA +"/alive",ChatColor.AQUA + "Zeigt alle lebenden Spieler an.", 20, 80, 20);
						}
					}
					
					if (Zehn == 16) {
						for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
							p2.sendTitle(ChatColor.DARK_AQUA +"/teams",ChatColor.AQUA + "Zeigt alle Teams an.", 20, 80, 20);
						}

					}
					
					if (Zehn == 19) {
						for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
							p2.sendTitle(ChatColor.DARK_AQUA +"/zeit",ChatColor.AQUA + "Zeigt an, wann du gekickt wirst.", 20, 80, 20);
						}

					}
					
					if (Zehn == 22) {
						for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
							p2.sendTitle(ChatColor.DARK_AQUA +"Wichtige Commands:",ChatColor.AQUA + "", 20, 80, 20);
						}
					}
					
					
					if (Zehn < 11) {
						if (Zehn > 0) {
							for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					
								p2.sendTitle(ChatColor.RED + String.valueOf(Zehn),ChatColor.RED + "", 20, 80, 20);
								p2.playSound(p2.getLocation(), Sound.BLOCK_NOTE_PLING , 3.0F, 2F);
								
							}
						} else {
							
							if( Zehn == (plugin.getConfig().getInt("SchutzTime")+ 5)*-1 + 30 ){
								for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
									p2.sendTitle(ChatColor.DARK_AQUA + "Schutzzeit",ChatColor.AQUA + "endet in " + String.valueOf(plugin.getConfig().getInt("SchutzTime")+5  - (Zehn*-1)) + " Sekunden", 20, 80, 20);		
								}
							}
							
							if( Zehn <= (plugin.getConfig().getInt("SchutzTime")+ 5)*-1 + 10 ){
								if( Zehn == (plugin.getConfig().getInt("SchutzTime") + 5)*-1){
									for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
										p2.sendTitle(ChatColor.DARK_AQUA + "Schutzzeit",ChatColor.AQUA + "endet jetzt!", 20, 80, 20);
										loop1.cancel();
										
										for (int i = 0; i < 8; i++) { 
											int e = i + 1; 
											cfg.set("Player" + String.valueOf(e) + ".schutz", 0); 
										}
										specialConfig.saveConfig(cfg, "plugins//BRRO//player.yml"); 
										
									}
								} else {
									for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
										p2.sendTitle(ChatColor.DARK_AQUA + "Schutzzeit",ChatColor.AQUA + "endet in " + String.valueOf(plugin.getConfig().getInt("SchutzTime")+5  - (Zehn*-1)) + " Sekunden", 20, 80, 20);		
									}
								}
							}
						}
					}
				}
				Zehn = Zehn - 1;
				
			}
			
		}, (20 * 1L), 20 * 1);
		
		} else {
			sender.sendMessage(ChatColor.RED + "Du hast die Rechte für diesen Command nicht!");
		}
		return false;
	
	}
}
