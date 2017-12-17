package net.essence.module.command;

import net.essence.Main;

public class CommandManager {

	public void registerCommand(ModuleCommand cmd){
		cmd.getModuleExecutor().getUsedPlugin().getServer().getCommandMap().register(cmd.getName(), cmd);
	}
	
	public static void registerCmd(ModuleCommand cmd){
		Main.getInstance().getServer().getCommandMap().register(cmd.getName(), cmd);
	}
	
}
