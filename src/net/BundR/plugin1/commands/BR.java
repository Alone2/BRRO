package net.BundR.plugin1.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BR implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Du must ein Spieler sein um dies nutzen zu können!");
			return false;
		}
		
		Player player = (Player) sender;

		player.sendTitle(ChatColor.DARK_AQUA +"BR", ChatColor.AQUA + "BR  BR  BR BR", 20, 80, 20);

		return false;

	}

}
