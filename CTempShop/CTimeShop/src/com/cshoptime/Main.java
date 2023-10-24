package com.cshoptime;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import java.io.File;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.CommandSender;

import br.com.ystoreplugins.product.economy.EconomyProvider;
import br.com.ystoreplugins.product.yeconomias.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	public static Plugin plugin;
	
	public static Economy economy;


	@Override
	public void onEnable() {
		
	     if (!setupEconomy()) {
	            getLogger().severe("Vault ou um plugin de economia compatível não foi encontrado!");
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
		
		
		
		getServer().getPluginManager().registerEvents(new ConfirmClick(), this);
		getServer().getPluginManager().registerEvents(new ShopClick(), this);
		File configFile = new File(getDataFolder() , "config.yml");
		if(!configFile.exists()) {
			saveResource("config.yml", false);
		}

		Main.plugin = this;
		configadm.defaultconfig.load();
		getCommand("timeshop").setExecutor(new ComandClass());
		getCommand("viajante").setExecutor(new ComandClass());
		
	
        int intervaloSegundos = 57;
        int intervaloTicks = intervaloSegundos * 20;
        Runner runner = new Runner(this);
        runner.runTaskTimer(this, 0, intervaloTicks);
	
	}
	
	
	public static void saveconfig() {
		saveconfig();
	
		
	}
	
	
    private boolean setupEconomy() {
    	
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
        return (economy != null);
    }

}
