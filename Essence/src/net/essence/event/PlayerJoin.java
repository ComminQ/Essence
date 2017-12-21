package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.essence.Main;
import net.essence.player.EssencePlayer;

public class PlayerJoin implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		EssencePlayer eplayer = new EssencePlayer(e.getPlayer());
		
		if(Main.getInstance().joinMessage.equals("null")){
			e.setJoinMessage(null);
			return;
		}
		if(!Main.getInstance().joinMessage.equals("none")){
			e.setJoinMessage(Main.getInstance().joinMessage.replaceAll("%player%", e.getPlayer().getName()).replaceAll("%rankPrefix%", eplayer.getRank().getPrefix()));
			return;
		}
		
	}
	
}
