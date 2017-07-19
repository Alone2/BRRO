package net.BundR.plugin1.event.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.plugin1;
import net.BundR.plugin1.specialConfig;

public class PlayerDamageFromPlayer implements Listener {
	
	private plugin1 plugin;

	public PlayerDamageFromPlayer(plugin1 pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerDamageFromPlayer(EntityDamageByEntityEvent event) {
		
		FileConfiguration cfg = specialConfig.config("plugins//BRRO//player.yml");
		
		Entity e1 = event.getDamager();
		Entity e2 =event.getEntity();
		
		if (e1 instanceof Player) {
			if (e2 instanceof Player) {
				
				if(plugin.getConfig().getInt("FiendlyFire") == 0) {
					
					String PlayerId = "BUG";
					String PlayerId2 = "BUG";
					
					Player p1 = (Player) e1;
					Player p2 = (Player) e2;
					
					PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p1.getUniqueId()));
					
					PlayerId2 = getPlayerConfigId.fromUUID(String.valueOf(p2.getUniqueId()));
					
					if (cfg.getString("Player" + PlayerId +".teamm8").equals(PlayerId2)) {
						event.setCancelled(true);
					}
					
				}
				
			}
		}
		
	}
}
