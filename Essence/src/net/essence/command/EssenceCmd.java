package net.essence.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.essence.Main;

public abstract class EssenceCmd extends Command implements PluginIdentifiableCommand{

	public static String noPerm;
	public static String noPlayer;
	
	public EssenceCmd(String name) {
		super(name);
	}

	@Override
	public abstract boolean execute(CommandSender sender, String label, String args[]);
	public abstract List<String> tabComplete(CommandSender sender, String alias, String[] args);

	@Override
	public Plugin getPlugin() {
		return Main.getInstance();
	}
	
	public Player checkPlayer(CommandSender sender, String string){
		Player player = Bukkit.getPlayer(string);
		if(player == null){
			sender.sendMessage(noPlayer);
			return null;
		}
		return player;
	}
	
	public boolean checkPerm(CommandSender sender, String permission){
		if(sender instanceof ConsoleCommandSender) return true;
		if(sender instanceof Player){
			if(!sender.hasPermission(permission)){
				sender.sendMessage(noPerm);
				return false;
			}
		}
		return true;
	}
	
	
}
