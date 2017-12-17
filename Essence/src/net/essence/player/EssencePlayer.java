package net.essence.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

import net.essence.rank.Rank;


public class EssencePlayer {

	protected static Map<Player, EssencePlayer> cache = new HashMap<>();
	
	private Rank rank;
	
	
	public EssencePlayer(Player player){
		Validate.notNull(player);
		cache.put(player, this);
		this.load(player.getUniqueId());
	}
	
	
	
	public void save(){
		
	}
	
	private void load(UUID id){
		
	}
	
	
	
	
}
