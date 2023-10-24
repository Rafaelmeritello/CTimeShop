package com.cshoptime;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.avaje.ebeaninternal.server.subclass.GetterSetterMethods;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import com.ystoreplugins.ymodel.util.economy.method.PointsManager;

import br.com.ystoreplugins.product.economy.manager.*;


public class ConfirmClick implements Listener{
	@EventHandler
	public void aoclicar(InventoryClickEvent e) {
		
		if(Utils.ToVisibleString(e.getInventory().getTitle()).contains("CitemShopConfirm")) {
			try {
				ItemStack item = e.getCurrentItem();
				if(e.getCurrentItem().getType().equals(Material.WOOL)) {
					if(e.getCurrentItem().getDurability() == 14) {
						e.getWhoClicked().closeInventory();
					}else {

					ItemStack product = e.getInventory().getItem(13);
					TimeShopItem tsi = ShopInv.TSI_by_name(TimeShopItem.get_TSI_name_by_lore(product.getItemMeta().getLore()));		
				
					
					// se comprar
					String eco = configadm.defaultconfig.getyml().getString("economia");
					double PlayerTokens = PointsManager.get(eco, e.getWhoClicked().getName(), null);
					
					if(PlayerTokens >= tsi.preco){
						tsi.atualizadisponibilidade();
						if(tsi.disponibilidade != 0) {
							e.getWhoClicked().sendMessage("§cEste item não esta mais disponivel");
							e.getWhoClicked().closeInventory();
							e.setCancelled(true);
							return;
						}
					
						PointsManager.remove(eco, e.getWhoClicked().getName(), tsi.preco, null);
						ConfigurationSection sessao_itens = configadm.defaultconfig.getsessao("ShopItens");
						configadm.defaultconfig.getyml().getConfigurationSection("ShopItens").getConfigurationSection(tsi.nome).set("estoque", tsi.estoque -1);
						configadm.defaultconfig.save();
						
						List<String> comandos = (List<String>) sessao_itens.getList(tsi.nome + ".comandos");
					  
			
						for (String string : comandos) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("<player>", e.getWhoClicked().getName()));
						}
						String msg = configadm.defaultconfig.getyml().getString("msg_player_compra").replaceAll("&", "§");
						e.getWhoClicked().sendMessage(msg);
						e.getWhoClicked().closeInventory();
						
						if(configadm.defaultconfig.getsessao("Broadcast_compra").getBoolean("ativar")) {
							for (Player p : Bukkit.getServer().getOnlinePlayers()) {
								p.sendMessage(configadm.defaultconfig.getsessao("Broadcast_compra").getString("mensagem").replaceAll("&", "§").replaceAll("<player>", e.getWhoClicked().getName()));
								
							}
						}
					
					// compra mal sucedida
					}else {
						e.getWhoClicked().sendMessage(configadm.defaultconfig.getyml().getString("sem_money").replaceAll("&", "§"));
					}
					
					
					}
				}
	
			}catch (Exception ex) {
				
			}finally {
				e.setCancelled(true);
			}
			

			
		}
	}
	
	
}
