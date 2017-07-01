package net.BundR.plugin1.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.BundR.plugin1.plugin1;
import net.BundR.plugin1.specialConfig;

public class build implements CommandExecutor {
	
	private plugin1 plugin;

	public build(plugin1 pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		FileConfiguration cfg = specialConfig.config("plugins//alone1//player.yml");
		FileConfiguration cfg2 = specialConfig.config("plugins//alone1//data.yml");
		
		if(sender.isOp()) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Du must ein Spieler sein um dies nutzen zu können!");
			return false;
		}
		Player player = (Player) sender;

		Location loc = player.getLocation();

		World w = loc.getWorld();

		
		cfg2.set("Mitte.X", player.getLocation().getBlockX());
		cfg2.set("Mitte.Y", player.getLocation().getBlockY());
		cfg2.set("Mitte.Z", player.getLocation().getBlockZ());
		specialConfig.saveConfig(cfg2, "plugins//alone1//data.yml"); 		
		
		Location StandOrtArt = player.getLocation();
		
		StandOrtArt.setX(cfg2.getInt("Mitte.X")  + 0.5);
		StandOrtArt.setY(cfg2.getInt("Mitte.Y"));
		StandOrtArt.setZ(cfg2.getInt("Mitte.Z") + 0.5);
		
		player.teleport(StandOrtArt);	
		
		int hoehe = 100;
		for (int i = 256; i > 0; i--) {
			
			Location StandOrtArt2 = player.getLocation();
			
			StandOrtArt2.setX(cfg2.getInt("Mitte.X")  + 0.5);
			StandOrtArt2.setY(i);
			StandOrtArt2.setZ(cfg2.getInt("Mitte.Z") + 0.5);
			
			if (StandOrtArt2.getBlock().getType() != Material.AIR) {
				hoehe = i+1;
				i = 0;
			}
			
		}
		
		w.setSpawnLocation(cfg2.getInt("Mitte.X"), hoehe, cfg2.getInt("Mitte.Z"));
		// PlayerSpawnKapsel
		Material Blockoben = Material.LOG;
		Material Blockunten = Material.PURPUR_SLAB;

		int add = 14;
		int add2 = 0;
		
		
		// block1 (richtblock)
		for (int i = 1; i < 9; i++) {
				
			Location block1 = player.getLocation();
			Location block2 = player.getLocation();
			Location block3 = player.getLocation();
			Location block4 = player.getLocation();
			Location block5 = player.getLocation();
			Location block6 = player.getLocation();
			Location block7 = player.getLocation();
			Location block8 = player.getLocation();
			Location block9 = player.getLocation();
			Location Spawnpoint = player.getLocation();
			
			if(i == 2){
				add = 10;
				add2 = 10;
			}
			
			if(i == 3){
				add = 0;
				add2 = 14;
			}
			
			if(i == 4){
				add = -10;
				add2 = 10;
			}
			
			if(i == 5){
				add = -14;
				add2 = 0;
			}
			
			if(i == 6){
				add = -10;
				add2 = -10;
			}
			
			if(i == 7){
				add = 0;
				add2 = -14;
			}
			
			if(i == 8){
				add = 10;
				add2 = -10;
			}

			block1.setX(block1.getX() + add2 - 0);
			block1.setY(block1.getY() - 1);
			block1.setZ(block1.getZ() + add);

			Block b = w.getBlockAt(block1);
			b.setType(Blockoben);
			
			//spawnpoint		

			Spawnpoint.setX(Spawnpoint.getX() + add2 - 0);
			Spawnpoint.setY(Spawnpoint.getY());
			Spawnpoint.setZ(Spawnpoint.getZ() + add);
			
			cfg.set("Player" + i  + ".spawnpoint.X", Spawnpoint.getX());
			cfg.set("Player" + i  + ".spawnpoint.Y", Spawnpoint.getY());
			cfg.set("Player" + i  + ".spawnpoint.Z", Spawnpoint.getZ());
			specialConfig.saveConfig(cfg, "plugins//alone1//player.yml"); 
			
			// block2
			block2.setX(block2.getX() + add2 - 0);
			block2.setY(block2.getY() + 0);
			block2.setZ(block2.getZ() + add + 1);

			Block b2 = w.getBlockAt(block2);
			b2.setType(Blockoben);

			// block3
			block3.setX(block3.getX() + add2 - 0);
			block3.setY(block3.getY() + 0);
			block3.setZ(block3.getZ() + add - 1);

			Block b3 = w.getBlockAt(block3);
			b3.setType(Blockoben);

			// block4
			block4.setX(block4.getX() + add2 - 0);
			block4.setY(block4.getY() + 1);
			block4.setZ(block4.getZ() + add - 1);

			Block b4 = w.getBlockAt(block4);
			b4.setType(Blockunten);

			// block5
			block5.setX(block5.getX() + add2 - 0);
			block5.setY(block5.getY() + 1);
			block5.setZ(block5.getZ() + add + 1);

			Block b5 = w.getBlockAt(block5);
			b5.setType(Blockunten);

			// block6
			block6.setX(block6.getX() + add2 - 1);
			block6.setY(block6.getY() + 0);
			block6.setZ(block6.getZ() + add);

			Block b6 = w.getBlockAt(block6);
			b6.setType(Blockoben);

			// block7
			block7.setX(block7.getX() + add2 + 1);
			block7.setY(block7.getY() + 0);
			block7.setZ(block7.getZ() + add);

			Block b7 = w.getBlockAt(block7);
			b7.setType(Blockoben);

			// block8
			block8.setX(block8.getX() + add2 - 1);
			block8.setY(block8.getY() + 1);
			block8.setZ(block8.getZ() + add);

			Block b8 = w.getBlockAt(block8);
			b8.setType(Blockunten);

			// block9
			block9.setX(block9.getX() + add2 + 1);
			block9.setY(block9.getY() + 1);
			block9.setZ(block9.getZ() + add);

			Block b9 = w.getBlockAt(block9);
			b9.setType(Blockunten);

		}
		w.getWorldBorder().setCenter(loc);
		w.getWorldBorder().setSize(plugin.getConfig().getInt("defaultWorldborder.groesse"));
		
		player.sendMessage("DONE");
		cfg2.set("build", 1);
		specialConfig.saveConfig(cfg2, "plugins//alone1//data.yml"); 
		
	} else {
		sender.sendMessage(ChatColor.RED + "Du hast die Rechte für diesen Command nicht!");
	}
		return false;

	}

}
