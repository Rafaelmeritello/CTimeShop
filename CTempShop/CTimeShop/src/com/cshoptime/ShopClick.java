package com.cshoptime;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.avaje.ebeaninternal.server.subclass.GetterSetterMethods;

import net.md_5.bungee.api.ChatColor;



public class ShopClick implements Listener{
	@EventHandler
	public void aoclicar(InventoryClickEvent e) {
		
		if(e.getInventory().getTitle().equals(configadm.defaultconfig.getyml().getString("TituloShop"))) {
			try {
				ItemStack item = e.getCurrentItem();
				List<String> lore = e.getCurrentItem().getItemMeta().getLore();
				
				e.getWhoClicked().openInventory((new BuyInv(TimeShopItem.get_TSI_name_by_lore(lore))).getinventario());
	
			}catch (Exception ex) {
				
			}finally {
				e.setCancelled(true);
			}
			
			if(e.getRawSlot() > 53) {
				e.setCancelled(true);
			}
			
		}
	}
	
	
}
