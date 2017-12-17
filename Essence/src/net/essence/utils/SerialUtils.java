package net.essence.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

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
	
}
