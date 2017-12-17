package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.essence.player.EssencePlayer;

public class PlayerJoin implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		new EssencePlayer(e.getPlayer());
	}
	
}
