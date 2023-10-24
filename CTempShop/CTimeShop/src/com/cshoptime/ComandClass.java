package com.cshoptime;

import java.util.List;

import org.apache.logging.log4j.core.jmx.LoggerConfigAdmin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;



public class ComandClass implements CommandExecutor{
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(cmd.getName().equalsIgnoreCase("timeshop")) {
		
        

        if(args.length >= 1) {
            if(args[0].equalsIgnoreCase("abrir")) {
            	if(s.hasPermission("ctimeshop.admin")) {
            		if(args.length != 2) {
            			s.sendMessage("§cDigite timeshop abrir (player)");
            			return true;
            			
            		}
            		try {
                		Player player_digitado = Bukkit.getPlayer(args[1]);
                		player_digitado.openInventory(ShopInv.getinventario());
                		
            		}catch (Exception e) {
						s.sendMessage("§cVerifique se o player esta online");
					}

	            		return true;
					

            		
            	}
            }
        	
        	
        Player p = (Player) s;	
    	if(args[0].equalsIgnoreCase("ajuda")) {
    		if(p.hasPermission("ctimeshop.admin")) {
    			p.sendMessage("§a§l----- §6§lCTimeShop 1.0 §a§l------");
    			p.sendMessage("§7Use os seguintes comandos");
    			p.sendMessage("§6/timeshop abrir (player) §7- Abre o timeshop para um player.");
    			p.sendMessage("§6/timeshop broadcast15m §7- Testa o broadcast de 15 minutos apenas para você.");
    			p.sendMessage("§6/timeshop broadcast10m §7- Testa o broadcast de 10 minutos apenas para você.");
    			p.sendMessage("§6/timeshop broadcast5m §7- Testa o broadcast de 5 minutos apenas para você.");
    			p.sendMessage("§6/timeshop testaritem (item) §7- Simula a compra de um item (sem gastar o limite).");
    			p.sendMessage("§a§l-----------");

    		}
    		return true;
    	}
        if(args[0].equalsIgnoreCase("broadcast15m")) {
        	
        	if(p.hasPermission("ctimeshop.admin") || p.hasPermission("*")) {
        		List<String> bd15 = (List<String>) configadm.defaultconfig.getyml().getList("broadcast_15m.mensagem");
        	
        	for(String s1 : bd15) {
        		p.sendMessage(s1.replaceAll("&", "§"));
        	}
        	return true;
        	}
        }
        if(args[0].equalsIgnoreCase("broadcast10m")) {
        	if(p.hasPermission("ctimeshop.admin")) {
        		List<String> bd15 = (List<String>) configadm.defaultconfig.getyml().getList("broadcast_10m.mensagem");
            	
        	for(String s1 : bd15) {
        		p.sendMessage(s1.replaceAll("&", "§"));
        	}
        	return true;
        	}
        }
        
        if(args[0].equalsIgnoreCase("broadcast5m")) {
        	if(p.hasPermission("ctimeshop.admin")) {
        		List<String> bd15 = (List<String>) configadm.defaultconfig.getyml().getList("broadcast_5m.mensagem");
            	
        	for(String s1 : bd15) {
        		p.sendMessage(s1.replaceAll("&", "§"));
        	}
        	return true;
        	}
        }
        if(args[0].equalsIgnoreCase("reload")) {
        	if(p.hasPermission("ctimeshop.admin")) {
        	Main.plugin.reloadConfig();
        	configadm.defaultconfig.reload();
        	s.sendMessage("§bReload Realizado!");
        	return true;
        	}
        }
        
        

        
        
        
        if(args[0].equalsIgnoreCase("testaritem")) {
        	
        	if(p.hasPermission("ctimeshop.admin")) {
        		
        		
        		
        		if(args[1].length() < 1) {
        			p.sendMessage("§cDigite um item para ser testado");
        			return true;
        		}
        		try {
        			ConfigurationSection sessaoitem = configadm.defaultconfig.getsessao("ShopItens").getConfigurationSection(args[1]);
        			List<String> comandos = (List<String>) sessaoitem.getList("comandos");
        			for (String string : comandos) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), string.replaceAll("<player>", p.getName()));
					}
        			p.sendMessage("§aCompra de item simulada com sucesso !");
        			return true;
        		}catch (Exception e) {
					p.sendMessage("§cAlgo deu errado, verifique o item digitado");
					return true;
        		}
        		
        	}
        }

        return true;
        }
        
        
        if(s.hasPermission("ctimeshop.admin")) {
		s.sendMessage("§a§l----- §6§lCTimeShop 1.0 §a§l------");
		s.sendMessage("§7Use os seguintes comandos");
		s.sendMessage("§6/timeshop abrir (player) §7- Abre o timeshop para um player.");
		s.sendMessage("§6/timeshop broadcast15m §7- Testa o broadcast de 15 minutos apenas para você.");
		s.sendMessage("§6/timeshop broadcast10m §7- Testa o broadcast de 10 minutos apenas para você.");
		s.sendMessage("§6/timeshop broadcast5m §7- Testa o broadcast de 5 minutos apenas para você.");
		s.sendMessage("§6/timeshop testaritem (item) §7- Simula a compra de um item (sem gastar o limite).");
		s.sendMessage("§a§l-----------");

        }
        
        
        
       
		return true;
		}
		
		
		
		
		
		if(cmd.getName().equalsIgnoreCase("Viajante")) {
			if(args.length == 0) {
				try {
					
				
				World w = Bukkit.getWorld(configadm.defaultconfig.getstring_sessao("local", "mundo"));
				int x = configadm.defaultconfig.getyml().getInt("local.x");
				int y = configadm.defaultconfig.getyml().getInt("local.y");
				int z = configadm.defaultconfig.getyml().getInt("local.z");
				int yaw = configadm.defaultconfig.getyml().getInt("local.yaw");
				int pitch = configadm.defaultconfig.getyml().getInt("local.pitch");
				Location l = new Location(w, x, y, z);
				l.setPitch(pitch);
				l.setYaw(yaw);
				if(s instanceof Player) {
					((Player) s).teleport(l);
					
					s.sendMessage(configadm.defaultconfig.getyml().getString("msg_teleporte").replaceAll("&", "§"));
				}else {
					s.sendMessage("§cApenas players podem teleportar");
				}
				}catch (Exception e) {
					s.sendMessage("§cO local ainda não esta definido");
				}
			}
			if(args.length > 0) {
				
				if(args[0].equalsIgnoreCase("setlocal")) {
					Player p = (Player) s;
					configadm.defaultconfig.getyml().set("local.x", p.getLocation().getX());
					configadm.defaultconfig.getyml().set("local.y", p.getLocation().getY());
					configadm.defaultconfig.getyml().set("local.z", p.getLocation().getZ());
					configadm.defaultconfig.getyml().set("local.mundo", p.getLocation().getWorld().getName());
					configadm.defaultconfig.getyml().set("local.yaw", p.getLocation().getYaw());
					configadm.defaultconfig.getyml().set("local.pitch", p.getLocation().getPitch());
					
					configadm.defaultconfig.save();
		        	Main.plugin.reloadConfig();
		        	configadm.defaultconfig.reload();
		        	s.sendMessage("§aLocal Definido com sucesso");
		        	return true;
				}
				Bukkit.dispatchCommand(s, "viajante");
				
			}
			return true;
		}
		return false;
	}

}
