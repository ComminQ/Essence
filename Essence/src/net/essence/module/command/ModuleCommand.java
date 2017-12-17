package net.essence.module.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.essence.module.Module;

public abstract class ModuleCommand extends Command{
	
	private Module moduleExecutor;
	
	public ModuleCommand(Module module, String name) {
		super(name);
		this.moduleExecutor = module;
		CommandManager.registerCmd(this);
	}
	
	@Override
	public abstract boolean execute(CommandSender sender, String label, String args[]);
	public abstract List<String> tabComplete(CommandSender sender, String alias, String[] args);
	
	public Module getModuleExecutor() {
		return moduleExecutor;
	}
	
	public void setModuleExecutor(Module moduleExecutor) {
		this.moduleExecutor = moduleExecutor;
	}
	
	
}
