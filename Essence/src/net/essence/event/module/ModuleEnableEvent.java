package net.essence.event.module;

import net.essence.event.PremadeEvent;
import net.essence.module.Module;

public class ModuleEnableEvent extends PremadeEvent{

	/**
	 * @author ComminQ_Q
	 * 			
	 * 			Event active when a module is being disabled
	 * 			// TODO You can enable Module IG
	 * 
	 */
	
	private Module module;

	public ModuleEnableEvent(Module module){
		this.module = module;
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
}
