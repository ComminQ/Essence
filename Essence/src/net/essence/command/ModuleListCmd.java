package net.essence.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.essence.Main;

public class ModuleListCmd extends EssenceCmd{

	/**
	 * @author ComminQ_Q
	 * 
	 *         see all modules active AND inactive
	 *         only OPs and Console can see this
	 *         there's no permission according to this command
	 * 
	 */

	public ModuleListCmd() {
		super("modules");
		super.setAliases(Arrays.asList(new String[] { "ml", "mls" }));
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if (sender instanceof Player) {
			if (!sender.isOp()) {
				return false;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("§bModules (" + Main.getInstance().getModuleManager().getModules().size() + ") : ");
		Main.getInstance().getModuleManager().getModules().forEach(module -> {
			sb.append((module.isEnabled() ? ChatColor.DARK_GREEN : ChatColor.DARK_RED)
					+ module.getDescription().getName() + "§r§7, ");
		});
		sb.replace(sb.length() - 2, sb.length(), "");
		sender.sendMessage(sb.toString());
		return false;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		return new ArrayList<>();
	}

}
