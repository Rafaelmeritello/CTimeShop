package com.cshoptime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopInv {
	
	public static int size = configadm.defaultconfig.getyml().getInt("tamanho_menu");
	public static String title = configadm.defaultconfig.getyml().getString("TituloShop").replaceAll("&", "§");
	private static Inventory i = Bukkit.createInventory(null, size,title);;
	
	
	
	
	
	
	
	public static List<TimeShopItem> ShopItens(){
		List<TimeShopItem> lista = new ArrayList<TimeShopItem>() ;
		for (String itemstr : configadm.defaultconfig.getsessao("ShopItens").getKeys(false)) {
			 ConfigurationSection sessao = configadm.defaultconfig.getsessao("ShopItens."+itemstr);
			 
			 String id = sessao.getString("id");
			 int pos = sessao.getInt("pos");
			 float preco = (float) sessao.getDouble("preco");
			 int estoque = sessao.getInt("estoque");
			 
			 
			 
			 String data_liberacao = sessao.getString("data_liberacao");
			 String data_bloqueio = sessao.getString("data_bloqueio");
			 
			 
			 
			 
			 String display = sessao.getString("Nome_display").replaceAll("&", "§");
			 List<String> lore = (List<String>) sessao.getList("lore");
			 List<String> lore_expirado = (List<String>) sessao.getList("lore_expirado");
			 List<String> lore_pendente = (List<String>) sessao.getList("lore_pendente");
			 List<String> lore_esgotado = (List<String>) sessao.getList("lore_esgotado");
			 String nome = itemstr;
			 lista.add(new TimeShopItem(nome, estoque, preco, data_liberacao,data_bloqueio, id, display, pos, lore, lore_expirado,lore_pendente,lore_esgotado));
		}
		return lista;
	}
	
	
	
	
	
	public static void popularinventario() {
		i.clear();
		for (TimeShopItem item : ShopInv.ShopItens()) {
			i.setItem(item.invpos, item.getitemstack());
		}
	}


	
	public static TimeShopItem TSI_by_name(String name) {
    	TimeShopItem item = null;
    	for (TimeShopItem tsi : ShopInv.ShopItens()) {
    		
    		if(Utils.ToVisibleString(tsi.nome).equals(name)) {
    			item = tsi;
    			
    		}
	}
    	return item;
	}
	
	
	
	
	public static Inventory getinventario() {
		ShopInv.popularinventario();
	
		return ShopInv.i;
	}
}
