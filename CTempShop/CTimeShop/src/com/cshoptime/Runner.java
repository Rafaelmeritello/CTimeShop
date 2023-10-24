package com.cshoptime;

import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Runner extends BukkitRunnable {
    private final Main plugin; 

    public Runner(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
       for (TimeShopItem item : ShopInv.ShopItens()) {
    	 Map<String,Integer> atual =  Utils.converterStringParaMap(Utils.formatarDataHoraAtual());
    	 Map<String,Integer> liberacao = Utils.converterStringParaMap(item.data_liberacao);
    	 Map<String,Integer> restante = Utils.calcularDiferencaDatas(atual, liberacao);
    	
    	 

    	 
    	 if( restante.get("anos") == 0 && restante.get("horas") == 0 && restante.get("dias") == 0 && restante.get("meses") == 0) {
    	 
    	 // 15 min
    	 if(restante.get("minutos") == 15) {
    		 if(configadm.defaultconfig.getsessao("broadcast_15m").getBoolean("ativar")) {
    			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
    				p.sendMessage(configadm.defaultconfig.getsessao("broadcast_15m").getString("mensagem").replaceAll("&", "§").replaceAll("<item_display>", item.display_name));
    			}
    		 }
    	 }
    	 // 10 min
    	 if(restante.get("minutos") == 10) {
    		 if(configadm.defaultconfig.getsessao("broadcast_10m").getBoolean("ativar")) {
    			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
    				p.sendMessage(configadm.defaultconfig.getsessao("broadcast_10m").getString("mensagem").replaceAll("&", "§").replaceAll("<item_display>", item.display_name));
    			}
    		 }
    	 }
    	 
    	 
    	 // 5 min
    	 if(restante.get("minutos") == 5) {
    		 if(configadm.defaultconfig.getsessao("broadcast_5m").getBoolean("ativar")) {
    			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
    				p.sendMessage(configadm.defaultconfig.getsessao("broadcast_5m").getString("mensagem").replaceAll("&", "§").replaceAll("<item_display>", item.display_name));
    			}
    		 }
    	 }
    	 
    	 }
	}
    }
}
