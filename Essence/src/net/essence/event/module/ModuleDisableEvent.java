package net.essence.event.module;

import net.essence.event.PremadeEvent;
import net.essence.module.Module;

public class ModuleDisableEvent extends PremadeEvent{

	/**
	 * @author ComminQ_Q
	 * 			
	 * 			Event active when a module is being disabled
	 * 			// TODO You can disable Module IG
	 * 
	 */
	
	private Module module;

	public ModuleDisableEvent(Module module){
		this.module = module;
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
}
