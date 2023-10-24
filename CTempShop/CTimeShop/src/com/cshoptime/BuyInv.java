package com.cshoptime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyInv {
	
	public static int size = 27;
	public  String title = "";
	private  Inventory i;
	private String TimeItemName;
	private int rejectpos = 10;
	private int rejectmat = 35;
	private short rejectdur = 14;
	
	private int acceptpos = 16;
	private int acceptmat = 35;
	private short acceptdur = 5;
    private int itempos = 13;
    
   
	public BuyInv(String TimeItemname) {
		this.TimeItemName = TimeItemname;
	}
	
	
	public List<String> replacee_lore_itemname(List<String> lore, String itemname){
		List<String> nova_lore = new ArrayList<String>();
		for (String string : lore) {
			nova_lore.add(string.replaceAll("&", "§").replaceAll("<item_display>", itemname));
		}
		return nova_lore;
	}
	
	
    private void popularinventario() {
    	ConfigurationSection menu_compra = configadm.defaultconfig.getsessao("menu_compra");
    	// definir item a ser comprado
    	String itemdisplay = "";
    	ItemStack item = new ItemStack(Material.BARRIER,1);
    	for (TimeShopItem tsi : ShopInv.ShopItens()) {
    		
    		if(Utils.ToVisibleString(tsi.nome).equals(this.TimeItemName)) {
    			item = tsi.getitemstack();
    			itemdisplay = tsi.display_name;
    		}
		}
    	
    	
    	// definir material de aceitar compra
//    	Material acceptm = Material.getMaterial(acceptmat);
     	ItemStack acceptitem;
//    	acceptitem.setDurability(acceptdur);
    	String acceptstr = configadm.defaultconfig.getyml().getString("menu_compra.ItemAceitar");
    	acceptitem = Utils.getitemstackbyformat(acceptstr);
    	ItemMeta acceptIM = acceptitem.getItemMeta();
    	acceptIM.setDisplayName(menu_compra.getString("display_aceitar").replaceAll("&", "§").replaceAll("<item_display>", itemdisplay));
    	List<String> acceptlore = (List<String>) menu_compra.getList("lore_aceitar");
    	acceptIM.setLore(replacee_lore_itemname(acceptlore, itemdisplay));
     	acceptitem.setItemMeta(acceptIM);
   	
    	
    	// decidir material de recusar compra
//      	Material rejectm = Material.getMaterial(rejectmat);
    	ItemStack rejectitem;
//    	rejectitem.setDurability(rejectdur);
    	String rejectstr = configadm.defaultconfig.getyml().getString("menu_compra.ItemRecusar");
    	rejectitem = Utils.getitemstackbyformat(acceptstr);
    	ItemMeta rejectIM = rejectitem.getItemMeta();
    	rejectIM.setDisplayName(menu_compra.getString("display_recusar").replaceAll("&", "§").replaceAll("<item_display>", itemdisplay));
    	List<String> rejectlore = (List<String>) menu_compra.getList("lore_recusar");
    	rejectIM.setLore(replacee_lore_itemname(rejectlore, itemdisplay));
     	rejectitem.setItemMeta(rejectIM);
    	

    	
    	
    	
    	
     	// popular inventario
    	this.title = configadm.defaultconfig.getyml().getString("Titulo_confirmacao").replaceAll("&", "§").replaceAll("<itemdisplay>", item.getItemMeta().getDisplayName()) + Utils.toinvisiblestrng("CitemShopConfirm");
    	this.i = Bukkit.createInventory(null, size,title);
    	this.i.setItem(itempos, item);
    	this.i.setItem(acceptpos, acceptitem);
    	this.i.setItem(rejectpos, rejectitem);
    	
    	
    	
    	
    	
    }
	
	public Inventory getinventario() {
		
		popularinventario();
		return this.i;
	}
}
