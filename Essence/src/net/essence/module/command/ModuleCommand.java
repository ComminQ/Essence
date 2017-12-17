package net.essence.module.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import net.essence.Main;
import net.essence.module.Module;

public abstract class ModuleCommand extends Command implements PluginIdentifiableCommand{
	
	private Module moduleExecutor;
	
	public ModuleCommand(Module module, String name) {
		super(name);
		this.moduleExecutor = module;
		CommandManager.registerCmd(this);
	}
	
	public abstract void execute(CommandSender sender, String[] args);
	public abstract String[] onTabComplete(CommandSender sender, String[] actualArgs);
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args){
		return Arrays.asList(this.onTabComplete(sender, args));
	}
	 
	@Override
	public boolean execute(CommandSender sender, String label, String args[]){
		this.execute(sender, args);
		return true;
	}
	
	public Module getModuleExecutor() {
		return moduleExecutor;
	}
	
	public void setModuleExecutor(Module moduleExecutor) {
		this.moduleExecutor = moduleExecutor;
	}
	
	@Override
	public Plugin getPlugin() {
		return Main.getInstance();
	}
	
	
}
