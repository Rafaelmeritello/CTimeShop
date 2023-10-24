package com.cshoptime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class TimeShopItem {
	public int id;
	public int durability = 0;
	public String display_name;
	public String data_liberacao;
	public String data_bloqueio;
	public int invpos;
	public String nome;
	public List<String> lore = new ArrayList<String>();
	public List<String> lore_pendente = new ArrayList<String>();
	public List<String> lore_expirado = new ArrayList<String>();
	public List<String> lore_esgotado = new ArrayList<String>();
	Map<String, Integer> restante;
	public int estoque;
	public float preco;
	public int disponibilidade = 0;
	
	
	public TimeShopItem(String nome, int estoque, float preco, String data_liberacao , String data_bloqueio, String id, String display_name , int invpos, List<String> itemlore, List<String> lore_expirado,List<String> lore_pendente,List<String> lore_esgotado ) {
	   
		if(id.contains("-")) {
			this.id = Integer.parseInt(id.split("-")[0]);
			this.durability = Integer.parseInt(id.split("-")[1]);
		}else {
			this.id = Integer.parseInt(id);
		}
		
		this.data_liberacao = data_liberacao;
		this.data_bloqueio = data_bloqueio;
		this.display_name = display_name;
		this.invpos = invpos;
		this.nome = nome;
		this.estoque = estoque;
		this.preco = preco;
		for (String str : itemlore) {
			this.lore.add(default_replacer(str));
		}
		for (String str : lore_expirado) {
			this.lore_expirado.add(default_replacer(str));
		}
		for (String str : lore_pendente) {
			this.lore_pendente.add(default_replacer(str));
		}
		for (String str : lore_esgotado) {
			this.lore_esgotado.add(default_replacer(str));
		}
	}
	
	
	
	
	
	private String default_replacer(String str) {
		atualizadisponibilidade();
		str = str.replaceAll("&", "§");
		str = str.replaceAll("<data_liberacao>", this.data_liberacao.split("-")[0]);
		str = str.replaceAll("<hora_liberacao>", this.data_liberacao.split("-")[1]);
		str = str.replaceAll("<minuto_liberacao>", this.data_liberacao.split("-")[2]);
		
		str = str.replaceAll("<data_bloqueio>", this.data_bloqueio.split("-")[0]);
		str = str.replaceAll("<hora_bloqueio>", this.data_bloqueio.split("-")[1]);
		str = str.replaceAll("<minuto_bloqueio>", this.data_bloqueio.split("-")[2]);
		
		
		
		str = str.replaceAll("<estoque>", this.estoque+"");
	    str = str.replaceAll("<preco>", this.preco+"");

	    str = str.replaceAll("<dias_restantes>", restante.get("dias")+"");
	    str = str.replaceAll("<meses_restantes>", restante.get("meses")+"");
	    str = str.replaceAll("<horas_restantes>", restante.get("horas")+"");
	    str = str.replaceAll("<minutos_restantes>", restante.get("minutos")+"");
	    str = str.replaceAll("<anos_restantes>", restante.get("anos")+"");
		return str;
	}
	
	
	
	public void atualizadisponibilidade() {
		this.restante = Utils.calcularDiferencaDatas(Utils.converterStringParaMap(Utils.formatarDataHoraAtual()), Utils.converterStringParaMap(this.data_liberacao));
		    
		if(this.estoque == 0) {
			this.disponibilidade = 3;
		}
		if(this.disponibilidade != 3) {
		this.disponibilidade = Utils.estaEntreDatas(this.data_liberacao,this.data_bloqueio);
		}
		}
	
	public ItemStack getitemstack() {
		String atual = Utils.formatarDataHoraAtual();
		
		
		
		// disponibilidade = 0 se estiver entre as datas, 1 se for maior do que a do bloqueio e -1 se for menor do que a liberacao
		  
		  if(this.disponibilidade != 0){
			  
		    	ItemStack i = new ItemStack(Material.WOOL);
		    	i.setDurability((short) 14);
		    	
		    	ItemMeta im = i.getItemMeta();
		    	im.setDisplayName(this.display_name.replaceAll("&", "§"));
		    	im.setLore(lore_expirado);
		    	if(disponibilidade == 3) {
		    		im.setLore(this.lore_esgotado);
		    	}
		    	if(disponibilidade == -1) {
		    		im.setLore(this.lore_pendente);
		    	}
		    	i.setItemMeta(im);
			  return i;
		    }
		  
		  
		  
		  
		  
	    ItemStack i = new ItemStack(Material.getMaterial(this.id),1);
	    i.setDurability((short) this.durability);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(display_name);
	    ConfigurationSection customhead = configadm.defaultconfig.getsessao("ShopItens").getConfigurationSection(this.nome).getConfigurationSection("custom_head");
	    this.lore.add(Utils.toinvisiblestrng("TimeItem: "+this.nome));
		
	    im.setLore(this.lore);
	    i.setItemMeta(im);
	    if(customhead.getBoolean("ativar")) {
	        i.setDurability((short) 0);
	    	String base64 = customhead.getString("base64");
	    	Bukkit.getConsoleSender().sendMessage(base64);
	        i = Utils.getCustomTextureHead(base64);
	        ItemMeta imh = i.getItemMeta();
	        imh.setLore(this.lore);
	        imh.setDisplayName(display_name);
	        i.setItemMeta(imh);
	        // Remover a durability
	
	    }

	   
	    return i;
	}
	
	
	
	
	
	//retorna o nome do itemshop de acordo com sua lore
	public static String get_TSI_name_by_lore(List<String> lore) {
		return Utils.ToVisibleString(lore.get(lore.size()-1)).split("TimeItem: ")[1];
	}
	

}
