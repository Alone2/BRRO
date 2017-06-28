package net.BundR.plugin1.event.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class PlayerChat implements Listener {
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		String message = event.getMessage().toLowerCase();
		String message2 = event.getMessage();

		if (message.contains("br")) {

			event.setCancelled(true);

			for (Player p : Bukkit.getServer().getOnlinePlayers()) {

				p.sendMessage("<BR " + player.getName() + "> " + message2 );

			}

		}
		

	}
}