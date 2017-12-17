package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.essence.ChatFormat;
import net.essence.player.Essence;
import net.essence.player.EssencePlayer;

public class PlayerChat implements Listener{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e){
		String format = ChatFormat.getChatformat().getFormat();
		
		EssencePlayer player = Essence.getPlayer(e.getPlayer());
		
		e.setFormat( format.replaceAll("%player%", e.getPlayer().getName()).replaceAll("%rankPrefix%", player.getRank().getPrefix()) + "%2$s" );
		
		
	}
	
}
