package net.BundR.plugin1.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.BundR.plugin1.getPlayerConfigId;
import net.BundR.plugin1.specialConfig;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Team  implements CommandExecutor {
	
	String PlayerId = "Bug";
	String PlayerId2 = "Bug";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//BRRO//player.yml");
		FileConfiguration cfg2 = specialConfig.config("plugins//BRRO//data.yml");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Du must ein Spieler sein um dies nutzen zu können!");
			return false;
		}
		
		Player p = (Player) sender;
		
		PlayerId = getPlayerConfigId.fromUUID(String.valueOf(p.getUniqueId()));
		
		int team = cfg.getInt("Player" + PlayerId + ".team");
		if (team == 1) {
			p.sendMessage(ChatColor.RED + "Du bist bereits in einem Team!");
			return false;
		}
			
		if (args.length >= 1) {
			
			int start = 0;
			for (Player p3 : Bukkit.getServer().getOnlinePlayers()) {
				if (args[0].equals(p3.getName())) {
					start = start + 1;
				}
			}
			
			if (args[0].equals("accept")){
				
				if (args.length != 2) {
					p.sendMessage(ChatColor.DARK_RED + "Du verwendest den Command nicht richtig! Er funktioniert so:");
					p.sendMessage(ChatColor.RED + "/team accept [Name des Spielers]");
					
					return false;
				}
				
				int start2 = 0;
				for (Player p3 : Bukkit.getServer().getOnlinePlayers()) {
					if (args[1].equals(p3.getName())) {
						start2 = start2 + 1;
					}
				}
				if (!(start2 > 0)) {
					
					p.sendMessage(ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " befindet sich nicht auf dem Server!");
					return false;
					
				}
				
				Player p4 = Bukkit.getServer().getPlayer(args[1]);
				
				PlayerId2 = getPlayerConfigId.fromUUID(String.valueOf(p4.getUniqueId()));
				
				Player p5 = Bukkit.getServer().getPlayer(cfg.getString("Player" + PlayerId2 + ".g-teamm8"));
				
				if (!(p5.getName().equals(p.getName()))) {
					p.sendMessage(ChatColor.RED + "Du hast keine Team Anfrage von "  + ChatColor.DARK_RED + args[1] + ChatColor.RED + " erhalten!");
					return false;
				}
				
				p.sendMessage(ChatColor.DARK_AQUA  + "Du und " + p4.getName() +" sind jetzt ein Team!");
				p4.sendMessage(ChatColor.DARK_AQUA + "Du und " + p.getName() + " sind jetzt ein Team!");
				
				cfg2.set("team", cfg2.getInt("team") + 1);
				specialConfig.saveConfig(cfg2, "plugins//BRRO//data.yml"); 
				
				cfg.set("Player" + PlayerId + ".teamm8", PlayerId2);
				cfg.set("Player" + PlayerId2 + ".teamm8", PlayerId);
				
				cfg.set("team" + cfg2.getInt("team") + ".player1", PlayerId );
				cfg.set("team" + cfg2.getInt("team") + ".player2", PlayerId2 );
								
				cfg.set("Player" + PlayerId + ".team", 1);
				cfg.set("Player" + PlayerId2 + ".team", 1);
				
				specialConfig.saveConfig(cfg2, "plugins//BRRO//data.yml"); 
				specialConfig.saveConfig(cfg, "plugins//BRRO//player.yml");
				
				return false;
				
			} 
			
			if (args[0].equals("deny")){
				
				Player p6 = Bukkit.getServer().getPlayer(args[1]);
				
				PlayerId2 = getPlayerConfigId.fromUUID(String.valueOf(p6.getUniqueId()));
				
				Player p7 = Bukkit.getServer().getPlayer(cfg.getString("Player" + PlayerId2 + ".g-teamm8"));
				
				if (!(p7.getName().equals(p.getName()))) {
					p.sendMessage(ChatColor.RED + "Du hast keine Team Anfrage von "  + ChatColor.DARK_RED + args[1] + ChatColor.RED + " erhalten!");
					return false;
				}
				
				p6.sendMessage(ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " hat deine Anfrage abgehlent");
				p.sendMessage(ChatColor.RED + "Du hast die Anfrage von " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " abgehlent");
			
				
			} else  {
				
				if (start > 0) {
				
					Player p2 = Bukkit.getServer().getPlayer(args[0]);
			
					if (p2 != p) {
			
						Player playerother = p.getServer().getPlayer(args[0]);
				
						IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"Willst du mit " + p.getName() + " im Team sein?\",\"color\":\"dark_aqua\"}");
						PacketPlayOutChat chat = new PacketPlayOutChat(comp);
						((CraftPlayer)playerother).getHandle().playerConnection.sendPacket(chat);
			
						IChatBaseComponent comp2 = ChatSerializer.a("{\"text\":\" [JA]\",\"color\":\"dark_green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/team accept " + p.getName() +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"/team accept "+ args[0]+ "\",\"color\":\"dark_green\"}]}}}");
						PacketPlayOutChat chat2 = new PacketPlayOutChat(comp2);
						((CraftPlayer)playerother).getHandle().playerConnection.sendPacket(chat2);
			
						IChatBaseComponent comp3 = ChatSerializer.a("{\"text\":\" [NEIN]\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/team deny " + p.getName() +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"/team deny "+ args[0]+ "\",\"color\":\"dark_red\"}]}}}");
						PacketPlayOutChat chat3 = new PacketPlayOutChat(comp3);
						((CraftPlayer)playerother).getHandle().playerConnection.sendPacket(chat3);
			
						p.sendMessage(ChatColor.DARK_AQUA + "Du hast eine Team Anfrage an " + args[0] + " geschickt");
						cfg.set("Player" + PlayerId + ".g-teamm8", args[0]);
						
						specialConfig.saveConfig(cfg2, "plugins//BRRO//data.yml"); 
						specialConfig.saveConfig(cfg, "plugins//BRRO//player.yml");
				
					} else {
				
						p.sendMessage(ChatColor.RED + "Du kannst dich nicht selber dazu einladen, mit dir im Team zu sein... Idiot...");
			
					}
				
				} else {
				
					p.sendMessage(ChatColor.RED + "Der Spieler " + ChatColor.DARK_RED + args[0] + ChatColor.RED + " befindet sich nicht auf dem Server!");
				
				}
				
			}
			
			
		} else {
			
				p.sendMessage(ChatColor.RED + "Gib bitte einen Namen ein!   ( /team [Spielername] )");
			
		}
		
		return false;
	}

}
