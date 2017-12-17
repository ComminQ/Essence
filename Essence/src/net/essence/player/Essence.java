package net.essence.player;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.essence.Main;
import net.essence.rank.Rank;

public class Essence {
	
	public static EssencePlayer getPlayer(Player player){
		return EssencePlayer.cache.get(player);
	}

	public static void loadPermission(Player player){
		EssencePlayer eplayer = getPlayer(player);
		Rank rank = eplayer.getRank();
		for(String perm : rank.getPermissions()){
			eplayer.getPermission().setPermission(perm, true);
		}
	}
	
	public static void updatePermissionPlayer(Player player){
		new BukkitRunnable() {
			
			@Override
			public void run() {
				EssencePlayer eplayer = EssencePlayer.cache.get(player);
				for(String s : eplayer.getPermission().getPermissions().keySet()){
					eplayer.getPermission().unsetPermission(s);
				}
				for(String s : eplayer.getRank().getPermissions()){
					eplayer.getPermission().setPermission(s, true);
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
	}
	
	public static void updatePermission(Rank rank){
		new BukkitRunnable() {
			@Override
			public void run() {
				for(EssencePlayer eplayer : EssencePlayer.cache.values()){
					if(eplayer.getRank() == rank){
						for(String s : eplayer.getPermission().getPermissions().keySet()){
							eplayer.getPermission().unsetPermission(s);
						}
						for(String s : rank.getPermissions()){
							eplayer.getPermission().setPermission(s, true);
						}
					}
				}
			}
		}.runTaskAsynchronously(Main.getInstance());
	}
	
	public static void createPlayerDataFoler(){
		File file = new File("plugins"+File.separator+"Essence"+File.separator+"playerdata");
		if(!file.exists()){
			try{
				file.mkdirs();
			}catch(SecurityException e){
				e.printStackTrace();
			}
		}
	}
	
}
