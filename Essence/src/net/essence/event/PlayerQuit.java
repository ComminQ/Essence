package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.essence.Main;
import net.essence.player.EssencePlayer;

public class PlayerQuit implements Listener{

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		EssencePlayer eplayer = EssencePlayer.cache.get(e.getPlayer());
		
		if(Main.getInstance().leftMessage.equals("null")){
			e.setQuitMessage(null);
			return;
		}
		if(!Main.getInstance().leftMessage.equals("none")){
			e.setQuitMessage(Main.getInstance().leftMessage.replaceAll("%player%", e.getPlayer().getName()).replaceAll("%rankPrefix%", eplayer.getRank().getPrefix()));
			return;
		}
		
	}
	
	
}
