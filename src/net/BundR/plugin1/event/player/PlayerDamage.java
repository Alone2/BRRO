package net.BundR.plugin1.event.player;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.specialConfig;

public class PlayerDamage implements Listener {
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
			
			FileConfiguration cfg = specialConfig.config("plugins//BRRO//player.yml");
			
			String PlayerId = "BUG";
			Entity BR = event.getEntity();
		
			if (BR instanceof Player) {

		
				PlayerId = getPlayerConfigId.fromUUID(String.valueOf(BR.getUniqueId()));
		
				if(cfg.getInt("Player" + PlayerId + ".schutz") == 1) {
			
					event.setCancelled(true);
				
				}
		
			
			}
				
	}
}
