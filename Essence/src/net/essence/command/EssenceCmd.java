package net.essence.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class EssenceCmd extends Command{

	public EssenceCmd(String name) {
		super(name);
		
	}

	@Override
	public abstract boolean execute(CommandSender sender, String label, String args[]);
	public abstract List<String> tabComplete(CommandSender sender, String alias, String[] args);

}
