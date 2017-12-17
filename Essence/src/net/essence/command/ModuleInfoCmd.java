package net.essence.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.essence.Main;
import net.essence.module.Module;

public class ModuleInfoCmd extends EssenceCmd{

	/**
	 * @author ComminQ_Q
	 * 
	 *         get some information about active module ONLY
	 *         only OPs and Console can see this
	 *         there's no permission according to this command
	 * 
	 */
	
	public ModuleInfoCmd() {
		super("moduleinfo");
		super.setAliases(Arrays.asList(new String[]{"minfo", "mi"}));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(sender instanceof Player){
			if(!sender.isOp()){
				return false;
			}
		}
		if(args.length == 0){
			sender.sendMessage("§bEssence actual version: "+Main.getInstance().getDescription().getVersion());
			sender.sendMessage("§bAuthor: ComminQ_Q");
			return false;
		}
		if(args.length == 1){
			Module module = Main.getInstance().getModuleManager().getModuleByName(args[0]);
			if(module == null){
				sender.sendMessage("§bThere is no module active with this name.");
				return false;
			}
			StringBuilder sb = new StringBuilder();
			for(String s : module.getDescription().getAuthors()){
				sb.append(s);
			}
			sender.sendMessage("§bModule §l"+module.getDescription().getName());
			sender.sendMessage("§bVersion: "+module.getDescription().getVersion());
			sender.sendMessage("§bAuthors: "+sb);
			return true;
		}
		return false;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		if(sender instanceof Player){
			if(!sender.isOp()){
				return null;
			}
		}
		List<String> retur = new ArrayList<>();
		for(Module m : Main.getInstance().getModuleManager().getModules()){
			retur.add(m.getDescription().getName());
		}
		return retur;
		
	}


}
