package net.essence.module.event;

import net.essence.Main;

public class ListenerManager {

	public void registerListener(ModuleListener listener){
		Main.getInstance().getServer().getPluginManager().registerEvents(listener, Main.getInstance());
	}

}
