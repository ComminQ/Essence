package net.essence.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SerialUtils {

	public static String locationToSerial(Location location){
		return location.getWorld().getName()+":"+location.getX()+":"+location.getY()+":"+location.getZ()+":"+location.getYaw()+":"+location.getPitch();
	}
	
	public static Location serialToLocation(String serial){
		String[] dataSplited = serial.split("\\:");
		return new Location(Bukkit.getWorld(dataSplited[0])
				, Double.parseDouble(dataSplited[1])
				, Double.parseDouble(dataSplited[2])
				, Double.parseDouble(dataSplited[3])
				, Float.parseFloat(dataSplited[4])
				, Float.parseFloat(dataSplited[5]));
	}
	
	public static String itemToSerial(ItemStack item){
		StringBuilder sb = new StringBuilder();
		if(!item.getItemMeta().hasDisplayName() && !item.getItemMeta().hasLore()){
			if(item.getEnchantments().isEmpty()){
				sb.append("2;"+item.getType().name()+":"+item.getAmount()+":"+item.getDurability()+";");
				sb.replace(sb.length()-1, sb.length(), "");
				return sb.toString();
			}else{
				sb.append("1;"+item.getType().name()+":"+item.getAmount()+":"+item.getDurability()+";");
				item.getEnchantments().forEach( (ench, in)->{
					sb.append(ench.getName()+"@"+in+":");
				});
				sb.replace(sb.length()-1, sb.length(), "");
				return sb.toString();
			}
		}else{
			if(item.getItemMeta().hasLore()){
				sb.append("3;"+item.getType().name()+":"+item.getAmount()+":"+item.getDurability()+";");
				item.getEnchantments().forEach( (ench, in)->{
					sb.append(ench.getName()+"@"+in+":");
				});
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append(";"+item.getI18NDisplayName()+";");
				item.getItemMeta().getLore().forEach(lore->{
					sb.append(lore+":");
				});
				sb.replace(sb.length()-1, sb.length(), "");
				return sb.toString();
			}else{
				sb.append("4;"+item.getType().name()+":"+item.getAmount()+":"+item.getDurability()+";");
				item.getEnchantments().forEach( (ench, in)->{
					sb.append(ench.getName()+"@"+in+":");
				});
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append(";"+item.getI18NDisplayName());
				return sb.toString();
			}
		}
	}
	
	
	public static ItemStack serialToItem(String serial){
		String[] serialSplited = serial.split("\\;");
		if(serialSplited[0].equalsIgnoreCase("1")){
			String[] splitedSplited = serialSplited[1].split("\\:");
			ItemStack m = new ItemStack(Material.valueOf(splitedSplited[0]));
			m.setAmount(Integer.parseInt(splitedSplited[1]));
			m.setDurability(Short.parseShort(splitedSplited[2]));
			
			splitedSplited = serialSplited[2].split("\\:");
			for(String data : splitedSplited){
				String[] dataSplited = data.split("\\@");
				m.addEnchantment(Enchantment.getByName(dataSplited[0]), Integer.parseInt(dataSplited[1]));
			}
			return m;
		}else if(serialSplited[0].equalsIgnoreCase("2")){
			
			String[] splitedSplited = serialSplited[1].split("\\:");
			ItemStack m = new ItemStack(Material.valueOf(splitedSplited[0]));
			m.setAmount(Integer.parseInt(splitedSplited[1]));
			m.setDurability(Short.parseShort(splitedSplited[2]));
			return m;
			
		}else if(serialSplited[0].equalsIgnoreCase("3")){
			String[] splitedSplited = serialSplited[1].split("\\:");
			ItemStack m = new ItemStack(Material.valueOf(splitedSplited[0]));
			m.setAmount(Integer.parseInt(splitedSplited[1]));
			m.setDurability(Short.parseShort(splitedSplited[2]));
			
			splitedSplited = serialSplited[2].split("\\:");
			for(String data : splitedSplited){
				String[] dataSplited = data.split("\\@");
				m.addEnchantment(Enchantment.getByName(dataSplited[0]), Integer.parseInt(dataSplited[1]));
			}
			ItemMeta im = m.getItemMeta();
			im.setDisplayName(serialSplited[3]);
			splitedSplited = serialSplited[4].split("\\:");
			for(String data : splitedSplited){
				im.getLore().add(data);
			}
			m.setItemMeta(im);
			return m;
		}else if(serialSplited[0].equalsIgnoreCase("4")){
			String[] splitedSplited = serialSplited[1].split("\\:");
			ItemStack m = new ItemStack(Material.valueOf(splitedSplited[0]));
			m.setAmount(Integer.parseInt(splitedSplited[1]));
			m.setDurability(Short.parseShort(splitedSplited[2]));
			
			splitedSplited = serialSplited[2].split("\\:");
			for(String data : splitedSplited){
				String[] dataSplited = data.split("\\@");
				m.addEnchantment(Enchantment.getByName(dataSplited[0]), Integer.parseInt(dataSplited[1]));
			}
			ItemMeta im = m.getItemMeta();
			im.setDisplayName(serialSplited[3]);
			m.setItemMeta(im);
			return m;
		}
		return null;
	}
	
}
