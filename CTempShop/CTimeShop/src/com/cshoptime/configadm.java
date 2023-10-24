package com.cshoptime;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public abstract class configadm {
	
	
	// configuracoes
	public static configfile defaultconfig = new configfile("config.yml");
	
	
	// configuracoes
	
	
	public static File root;
	
	public static boolean loadallconfig() {
		configadm.defaultconfig.load();
		return false;
		
	}
	
	
	
	
		
		
	}
