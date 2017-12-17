package net.essence.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.essence.ChatFormat;

public class PlayerChat implements Listener{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e){
		String format = ChatFormat.getChatformat().getFormat();
		
		e.setFormat( format.replaceAll("%player%", e.getPlayer().getName()).replaceAll("%rank%", "Joueur") + "%2$s");
		
		
	}
	
}
