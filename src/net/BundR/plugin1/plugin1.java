package net.BundR.plugin1;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.BundR.plugin1.commands.Alive;
import net.BundR.plugin1.commands.BR;
import net.BundR.plugin1.commands.Start;
import net.BundR.plugin1.commands.Team;
import net.BundR.plugin1.commands.Teams;
import net.BundR.plugin1.commands.build;
import net.BundR.plugin1.commands.time;
import net.BundR.plugin1.event.player.PlayerChat;
import net.BundR.plugin1.event.player.PlayerDamage;
import net.BundR.plugin1.event.player.PlayerDamageFromPlayer;
import net.BundR.plugin1.event.player.PlayerDeath;
import net.BundR.plugin1.event.player.PlayerJoin;

public class plugin1 extends JavaPlugin {

	public void onEnable() {
		
		registerCommands();
		registerEvents();
		registerConfig();

	}

	public void onDisable() {

		for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
			
			p2.kickPlayer(ChatColor.RED + "Der Server wird neu gestartet. Versuche wieder zu connecten.");
		}
		
	}

	public void registerCommands() {

		getCommand("BR").setExecutor(new BR());
		getCommand("build").setExecutor(new build(this));
		getCommand("zeit").setExecutor(new time(this));
		getCommand("start").setExecutor(new Start(this));
		getCommand("alive").setExecutor(new Alive());
		getCommand("team").setExecutor(new Team());
		getCommand("teams").setExecutor(new Teams());

	}

	public void registerEvents() {

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerDeath(this), this);	
		pm.registerEvents(new PlayerDamage(), this);
		pm.registerEvents(new PlayerDamageFromPlayer(this), this);

	}
	
	public void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
        File specialf = new File(getDataFolder(), "data.yml");

        File specialf2 = new File(getDataFolder(), "player.yml");

        if (!specialf.exists()) {
            specialf.getParentFile().mkdirs();
            saveResource("data.yml", false);
         }
        
        if (!specialf2.exists()) {
            specialf2.getParentFile().mkdirs();
            saveResource("player.yml", false);
         }
        
        FileConfiguration cfg2 = specialConfig.config("plugins//BRRO//data.yml");
		cfg2.set("startWorldborderLoop", 1);
		cfg2.set("high", 0);
		specialConfig.saveConfig(cfg2, "plugins//BRRO//data.yml"); 
		
         	
	}


}
