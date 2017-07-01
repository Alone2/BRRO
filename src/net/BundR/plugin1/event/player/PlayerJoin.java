package net.BundR.plugin1.event.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;
import net.BundR.plugin1.specialConfig;

public class PlayerJoin implements Listener {

	String PlayerId = "BUG";
	
	private BukkitTask loop1;
	
	private plugin1 plugin;

	public PlayerJoin(plugin1 pl) {
		plugin = pl;
	}

	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = (Player) event.getPlayer();
		
		FileConfiguration cfg = specialConfig.config("plugins//alone1//player.yml");
		FileConfiguration cfg2 = specialConfig.config("plugins//alone1//data.yml");
		
		//cfg.set("Test", cfg.getInt("Test") + 1);
			
		//specialConfig.saveConfig(cfg2, "plugins//alone1//data.yml"); 
		//specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 

		
		//loc
		Location loc = p.getLocation();
		World w = loc.getWorld();
		
		double lineX1 = w.getWorldBorder().getCenter().getX();
		double lineX2 = loc.getZ();
		Location lineX = p.getLocation();		
		lineX.setX(lineX1);
		lineX.setZ(lineX2);
		
		double lineZ1 = w.getWorldBorder().getCenter().getZ();
		double lineZ2 = loc.getX();
		Location lineZ = p.getLocation();		
		lineZ.setX(lineZ2);
		lineZ.setZ(lineZ1);
		
		
	    if (lineX.distance(loc) > w.getWorldBorder().getSize() / 2) {
	      
	      p.teleport(w.getSpawnLocation());
		  
		  
		} else {
			
			if (lineZ.distance(loc) > w.getWorldBorder().getSize() / 2) {	  
				
				p.teleport(w.getSpawnLocation());			
				
			}
		}
			
				
		if (!p.hasPlayedBefore()) {
			p.sendMessage(ChatColor.AQUA + "Sende eine Teamanfrage mit: " + ChatColor.DARK_AQUA + "/team [Spielername]");
			p.sendTitle(ChatColor.AQUA + "Teamanfrage: ",ChatColor.AQUA + "/team [Spielername]", 20, 80, 20);
			int time = plugin.getConfig().getInt("NormalTime");
			int WieViele = cfg2.getInt("WieViele");
			
				
			//kein cracked
			cfg.set("Player" + String.valueOf(WieViele + 1) + ".UUID", String.valueOf(p.getUniqueId()));
			cfg.set("Player" + String.valueOf(WieViele + 1) + ".name", String.valueOf(p.getName()));
			
			
			cfg.set("Player" + String.valueOf(WieViele + 1) + ".alive", 1);
			

			cfg.set("Player" + String.valueOf(WieViele + 1) + ".time", time);
			cfg2.set("WieViele", WieViele + 1);
			specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 
			specialConfig.saveConfig(cfg, "plugins//alone1//data.yml");
			
			if(cfg2.getInt("build") == 1) {
				Location tp = p.getLocation();
				tp.setX(cfg.getDouble("Player"+ String.valueOf(WieViele + 1) + ".spawnpoint.X"));
				tp.setY(cfg.getDouble("Player"+ String.valueOf(WieViele + 1) + ".spawnpoint.Y"));
				tp.setZ(cfg.getDouble("Player"+ String.valueOf(WieViele + 1) + ".spawnpoint.Z"));
			
				p.teleport(tp);
				//p.sendMessage(String.valueOf(tp));
			}
			//p.sendMessage(plugin.getConfig().getString("Player1.UUID"));
		}
		
		int high = cfg2.getInt("high") + 1;
		cfg2.set("high", high);
		specialConfig.saveConfig(cfg, "plugins//alone1//data.yml");
		
		String[] on_p;
		
		on_p = new String[high+1];
				
		on_p[high] = "0";
		
		
		PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p.getUniqueId()));
		
		
		int alive = cfg.getInt("Player" + PlayerId + ".alive");
		int time_p = cfg.getInt("Player" + PlayerId + ".time");
		
		if (alive == 0) {
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 200));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 200));

			loop1 = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

				@Override
				public void run() {
					p.kickPlayer(ChatColor.DARK_RED + "Du aus " + plugin.getConfig().getString("name") + " ausgeschieden!");
					
					loop1.cancel();
					
					
				}
				
			}, (20 * 1L), 20 * 1);
		}
		
		if (time_p < 1) {
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 200));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 200));

			loop1 = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

				@Override
				public void run() {
					p.kickPlayer(ChatColor.RED + "Du warst schon genug lange auf dem Server");
					
					loop1.cancel();
					
					
				}
				
			}, (20 * 1L), 20 * 1);
		}
		
		cfg.set("Player" + PlayerId + ".name", p.getName());
		specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 
		
		plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
			@Override
			public void run() {
				
				if (on_p[high] == "0"){
					
					
					
						
					//normal UUID kein Cracked

					PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p.getUniqueId()));
						

					//debug
					//int HowOnline = Bukkit.getOnlinePlayers().size();
				
					if (cfg2.getInt("start") == 1) {
						
						if (p.isOnline()){
					
							int time_p = cfg.getInt("Player" + PlayerId + ".time");
							cfg.set("Player" + PlayerId + ".time", time_p - 1);
							specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 
						
							if (time_p == 120) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 2 Minuten Zeit!");	
							}
							if (time_p == 60) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch eine Minute Zeit!");	
							}
							if (time_p == 30) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 30 Sekunden Zeit!");	
							}
							if (time_p == 10) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 10 Sekunden Zeit!");	
							}
							if (time_p == 5) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 5 Sekunden Zeit!");	
							}
							if (time_p == 4) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 4 Sekunden Zeit!");	
							}
							if (time_p == 3) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 3 Sekunden Zeit!");	
							}
							if (time_p == 2) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch 2 Sekunden Zeit!");	
							}
							if (time_p == 1) {
								p.sendMessage(ChatColor.RED + "Du hast nur noch eine Sekunde Zeit!");	
							}
							if (time_p < 1) {
								int notkick = 0;
								for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
									
									if (cfg.getInt("Player" + PlayerId + ".team") == 1) {
										
										String PlayerId2 = cfg.getString("Player" + PlayerId + ".teamm8");
										
										String teamm8UUID = cfg.getString("Player" + PlayerId2 + ".name");
										
										int weiter = 0;
										for (Player p3 : Bukkit.getServer().getOnlinePlayers()) {
											if (teamm8UUID == p3.getName()) {
												weiter = 1;
											}
										}
										if (weiter == 1) {
										Player teamm8 = Bukkit.getServer().getPlayer(teamm8UUID);
										
										if(p != p2) {
											
												if (p2 != teamm8) {
													if (p.getLocation().distance(p2.getLocation()) < 50) {
											
														notkick = notkick + 1;
											
													}
												} 
										 	
										}
										
										
										} else {
											if(p != p2) {
												if (p.getLocation().distance(p2.getLocation()) < 50) {
												
													notkick = notkick + 1;
										
												}
											}
										}
									} else {
										
										if(p != p2) {
											
											if (p.getLocation().distance(p2.getLocation()) < 50) {
												
												notkick = notkick + 1;
											
											}
										} 
			
									}
									
									
									
								}
								
								if (notkick == 0) {
									p.kickPlayer(ChatColor.RED + "Du warst schon genug lange auf dem Server");
									//p.sendMessage(String.valueOf(notkick));
								} else {
									p.sendMessage(ChatColor.RED + "Es ist ein Spieler in deiner Nähe! Entferne dich um gekickt werden zu können!");
								}
								
							}
							// debug
							// p.sendMessage(String.valueOf(time_p - 1));			
							// p.sendMessage(String.valueOf(w.getWorldBorder().getSize()/2));
							// p.sendMessage("X= " + String.valueOf(lineX.distance(loc)));
							// p.sendMessage("Z= " + String.valueOf(lineZ.distance(loc)));

					
						}else {
						
							on_p[high] = "1";
						
						}
					
					}
				
				}
				
			}

		}, (20 * 1L), 20 * 1);
		
	}
}
