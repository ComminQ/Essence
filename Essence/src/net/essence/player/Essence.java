package net.essence.player;

import org.bukkit.entity.Player;

public class Essence {
	
	public static EssencePlayer getPlayer(Player player){
		return EssencePlayer.cache.get(player);
	}

}
