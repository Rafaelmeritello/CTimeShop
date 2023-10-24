package com.cshoptime;
import org.bukkit.configuration.file.YamlConfiguration;

import com.avaje.ebeaninternal.server.core.ConcurrencyMode;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.io.IOException;
public class configfile {
	public String nome;
	private FileConfiguration yml;
	public configfile(String nome) {
		this.nome = nome;
	}
	public void save() {
	    File f = new File(Main.plugin.getDataFolder(), this.nome);
	    try {
	        this.yml.save(f);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void reload() {
	    File f = new File(Main.plugin.getDataFolder(), this.nome);
	    this.yml = YamlConfiguration.loadConfiguration(f);
	}
	
public void load() {
	File f = new  File(Main.plugin.getDataFolder() , this.nome);
    if(!f.exists()) {
 	     Main.plugin.saveResource(this.nome, false);
    }
    this.yml = YamlConfiguration.loadConfiguration(f);

}


public FileConfiguration getyml() {
	return this.yml;
}

public String getstring_sessao(String sessao, String chave) {
	return this.yml.getConfigurationSection(sessao).getString(chave);
}

public ConfigurationSection getsessao(String sessao) {
	return this.yml.getConfigurationSection(sessao);
}
public String ColorReplacedString(String Getter) {
	return this.getyml().getString(Getter).replace("&", "§");
}
}