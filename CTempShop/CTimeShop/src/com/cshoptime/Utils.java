package com.cshoptime;

import java.util.HashMap;
import java.util.Map;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.UUID;
import java.lang.reflect.Field;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.Base64;
import java.util.UUID;
import java.lang.reflect.Method;





import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.md_5.bungee.api.ChatColor;

import java.util.Base64;
import java.util.Date;

public class Utils {

	
	
	
	public static String toinvisiblestrng(String str) {
	    
	    StringBuilder b = new StringBuilder();
	    for(char c : str.toCharArray()) {
	    	b.append(ChatColor.COLOR_CHAR).append(c);
	    }
	    return b.toString();
	}
	
	
	
	
	public static String ToVisibleString(String str) {
		return str.replaceAll(String.valueOf(ChatColor.COLOR_CHAR), "");
	}
	
	
	
	
	
	
	
	   public static Map<String, Integer> converterStringParaMap(String dataHoraString) {
	        // Dividir a string em partes usando o caractere "-"
	        String[] partes = dataHoraString.split("-");
	            
	        // Verificar se o array resultante possui o número correto de partes
	        if (partes.length == 3) {
	            // Dividir a primeira parte (data) em dia, mês e ano
	            String[] data = partes[0].split("/");

	            // Verificar se a parte de data possui o número correto de partes
	            if (data.length == 3) {
	                int dia = Integer.parseInt(data[0]);
	                int mes = Integer.parseInt(data[1]);
	                int ano = Integer.parseInt(data[2]);

	                // Obter a hora e o minuto das partes restantes
	                int hora = Integer.parseInt(partes[1]);
	                int minuto = Integer.parseInt(partes[2]);

	                // Criar um mapa com os valores obtidos
	                Map<String, Integer> mapaDataHora = new HashMap<String, Integer>();
	                mapaDataHora.put("dia", dia);
	                mapaDataHora.put("mes", mes);
	                mapaDataHora.put("ano", ano);
	                mapaDataHora.put("hora", hora);
	                mapaDataHora.put("minuto", minuto);

	                return mapaDataHora;
	            }
	        }
	        return null; // Retorna null se o formato da string for inválido
	    }
	   
	   
	   
	   
	   public static int diasNoMes(int mes, int ano) {
	        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	        if (mes == 2 && (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0))) {
	            return 29; // Fevereiro com 29 dias em anos bissextos
	        } else {
	            return diasPorMes[mes];
	        }
	    }
	
	
	   
	   
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   public static Map<String, Integer> calcularDiferencaDatas(Map<String, Integer> data1, Map<String, Integer> data2) {
		    int dia1 = data1.get("dia");
		    int mes1 = data1.get("mes");
		    int ano1 = data1.get("ano");
		    int hora1 = data1.get("hora");
		    int minuto1 = data1.get("minuto");

		    int dia2 = data2.get("dia");
		    int mes2 = data2.get("mes");
		    int ano2 = data2.get("ano");
		    int hora2 = data2.get("hora");
		    int minuto2 = data2.get("minuto");

		    int minutosRestantes = 0;
		    int horasRestantes = 0;
		    int diasRestantes = 0;

		    if (minuto2 < minuto1) {
		        minutosRestantes = 60 - minuto1 + minuto2;
		        hora2--;
		    } else {
		        minutosRestantes = minuto2 - minuto1;
		    }

		    if (hora2 < hora1) {
		        horasRestantes = 24 - hora1 + hora2;
		        dia2--;
		    } else {
		        horasRestantes = hora2 - hora1;
		    }

		    if (dia2 < dia1) {
		        int diasNoMes = diasNoMes(mes1, ano1);
		        diasRestantes = dia2 + diasNoMes - dia1;
		        mes2--;
		    } else {
		        diasRestantes = dia2 - dia1;
		    }

		    while (mes2 < mes1) {
		        mes2 += 12;
		        ano2--;
		    }

		    int anosRestantes = ano2 - ano1;

		    Map<String, Integer> diferenca = new HashMap<>();
		    diferenca.put("anos", anosRestantes);
		    diferenca.put("meses", mes2 - mes1);
		    diferenca.put("dias", diasRestantes);
		    diferenca.put("horas", horasRestantes);
		    diferenca.put("minutos", minutosRestantes);

		    return diferenca;
		}
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   public static int estaEntreDatas(String dataInicioStr, String dataFimStr) {
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy-HH-mm");

		    try {
		        // Obtém a data e hora atual
		        Date dataAgora = new Date();
		        Date dataInicio = sdf.parse(dataInicioStr);
		        Date dataFim = sdf.parse(dataFimStr);

		        // Verifica se a data atual está entre as datas de início e fim
		        if (dataAgora.compareTo(dataInicio) < 0) {
		            return -1; // Data atual é menor do que a data de início
		        } else if (dataAgora.compareTo(dataFim) > 0) {
		            return 1; // Data atual é maior do que a data de fim
		        } else {
		            return 0; // Data atual está entre as duas datas
		        }
		    } catch (ParseException e) {
		        e.printStackTrace();
		        return -2; // Retorna um valor negativo para indicar um erro de análise
		    }
		}
	    
	    
	    public static String formatarDataHoraAtual() {
	        // Obtém a data atual
	        Date dataAtual = new Date();

	        // Define o formato desejado
	        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy-HH-mm");

	        // Formata a data atual para o formato desejado
	        return formato.format(dataAtual);
	    }
	
	    
	    
	    
	    public static ItemStack getCustomTextureHead(String value) {
	        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
	        SkullMeta meta = (SkullMeta) head.getItemMeta();
	        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
	        profile.getProperties().put("textures", new Property("textures", value));
	        Field profileField = null;
	        try {
	            profileField = meta.getClass().getDeclaredField("profile");
	            profileField.setAccessible(true);
	            profileField.set(meta, profile);
	        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	            e.printStackTrace();
	        }
	        head.setItemMeta(meta);
	        return head;
	    }

	    public static ItemStack getitemstackbyformat(String str) {
			int id,dur = 0;
	    	if(str.contains("-")) {
				id = Integer.parseInt(str.split("-")[0]);
				dur = Integer.parseInt(str.split("-")[1]);
			}else {
				id = Integer.parseInt(str);
			}
	    	ItemStack item = new ItemStack(Material.getMaterial(id));
	    
	    	item.setDurability((short) dur);
	    	return item;
	    	
	    }
}
