package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.essence.player.EssencePlayer;

public class PlayerQuit implements Listener{

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		EssencePlayer.cache.get(e.getPlayer()).save();;
	}
	
}
