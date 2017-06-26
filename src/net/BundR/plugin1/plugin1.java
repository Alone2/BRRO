package net.BundR.plugin1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
		//PluginDescriptionFile pdffile = getDescription();
		//Logger logger = getLogger();

		registerCommands();
		registerEvents();
		registerConfig();
		this.getConfig().set("startWorldborderLoop", 1);
		this.getConfig().set("high", 0);
		this.saveConfig();

		//logger.info(pdffile.getName() + " wurde gestartet! (Version: " + pdffile.getVersion() + ")");
	}

	public void onDisable() {
		//PluginDescriptionFile pdffile = getDescription();
		//Logger logger = getLogger();
		for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
			
			p2.kickPlayer(ChatColor.RED + "Der Server wird neu gestartet. Versuche wieder zu connecten.");
		}
		
		this.saveConfig();
		//logger.info(pdffile.getName() + " wurde gestoppt! (Version: " + pdffile.getVersion() + ")");
	}

	public void registerCommands() {

		getCommand("BR").setExecutor(new BR());
		getCommand("build").setExecutor(new build(this));
		getCommand("zeit").setExecutor(new time(this));
		getCommand("start").setExecutor(new Start(this));
		getCommand("alive").setExecutor(new Alive(this));
		getCommand("team").setExecutor(new Team(this));
		getCommand("teams").setExecutor(new Teams(this));

	}

	public void registerEvents() {

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerDeath(this), this);	
		pm.registerEvents(new PlayerDamage(this), this);
		pm.registerEvents(new PlayerDamageFromPlayer(this), this);

	}
	
	public void registerConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}


}
