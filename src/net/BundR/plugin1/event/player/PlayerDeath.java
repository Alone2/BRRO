package net.BundR.plugin1.event.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;

public class PlayerDeath implements Listener {
	
	private plugin1 plugin;

	public PlayerDeath(plugin1 pl) {
		plugin = pl;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player p = (Player) event.getEntity();
		
		Location loc = p.getLocation();

		World w = loc.getWorld();
		
		String PlayerId = "BUG";
		
		PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p.getUniqueId()), plugin);

		plugin.getConfig().set("Player" + PlayerId + ".alive", 0);
		plugin.getConfig().set("Worldborder", plugin.getConfig().getInt("Worldborder") + 1);
		plugin.saveConfig();
		
		/*for (int i = 0; i < plugin.getConfig().getInt("team"); i++) { 
			if() {
				
			}
		}*/
		
		if(plugin.getConfig().getInt("startWorldborderLoop") == 1) {
			
			plugin.getConfig().set("startWorldborderLoop", 0);
			
			plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

				@Override
				public void run() {
				
					if(plugin.getConfig().getInt("Worldborder") >= 1) {
						
						plugin.getConfig().set("Worldborder", plugin.getConfig().getInt("Worldborder") - 1);
						plugin.saveConfig();
						
						w.getWorldBorder().setSize(w.getWorldBorder().getSize() - plugin.getConfig().getInt("defaultWorldborder.schrumpfen"), 120);
						
						//debug
						/* for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
							p2.sendMessage("Worldborder -1");
						} */
						
					}
				
				}
			
			}, (0 * 1L), 2400 * 1);
		
		}
		
		

		
		for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					
			p2.sendTitle(ChatColor.RED + p.getName() + " starb!", ChatColor.DARK_RED + "Die Border schrumpft!", 20, 80, 20);
			p2.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH , 3.0F, 0.1F);
			
		}
				

		
		p.setWhitelisted(false);
		p.kickPlayer(ChatColor.DARK_RED + "Du aus " + plugin.getConfig().getString("name") + " ausgeschieden!");
		
		
		int alive = 0;
		int teamalive = 0;
		
		for (int i = 0; i < plugin.getConfig().getInt("team"); i++) {
			String playerID1 = plugin.getConfig().getString("team"+ String.valueOf(i+1) + ".player1");
			String playerID2 = plugin.getConfig().getString("team"+ String.valueOf(i+1) + ".player2");
			
			if(plugin.getConfig().getString("Player" + playerID1 + ".alive").equals("1")) {
				
				alive = alive + 1;
				
				if(plugin.getConfig().getString("Player" + playerID2 + ".alive").equals("1")) {
					
					teamalive = teamalive + 1;
					
				}
			}
			
			if(plugin.getConfig().getString("Player" + playerID2 + ".alive").equals("1")) {
				
				alive = alive + 1;
				
			}
			
		}
		
		if (alive <= 2) {
			if (teamalive == 1) {
				for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					p2.sendTitle(ChatColor.AQUA + "Ihr habt "+ plugin.getConfig().getString("name") +" gewonnen!", ChatColor.DARK_RED + "Bravo!", 20, 80, 20);
				}
			}
			if (alive == 1) {
				for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
					p2.sendTitle(ChatColor.AQUA + "Du hast "+ plugin.getConfig().getString("name") +" gewonnen!", ChatColor.DARK_RED + "Bravo!", 20, 80, 20);
				}
			}
		}
		

	}
}
