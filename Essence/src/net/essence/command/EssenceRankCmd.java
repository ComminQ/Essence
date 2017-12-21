package net.essence.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.essence.Main;
import net.essence.player.Essence;
import net.essence.player.EssencePlayer;
import net.essence.rank.Rank;
import net.essence.rank.RankManager;
import net.essence.utils.SerialUtils;

public class EssenceRankCmd extends EssenceCmd{

	public EssenceRankCmd() {
		super("essencerank");
		setAliases(Arrays.asList(new String[]{
				"esr", "er"
		}));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if(args.length == 0 || args[0].toLowerCase().equals("help")){
			if(!super.checkPerm(sender, "essence.rank.help")){
				return false;
			}
			
			sender.sendMessage("§7§m------------<§r §bEssence §7§m>------------");
			sender.sendMessage("§b/er rank create <name>");
			sender.sendMessage("§b/er rank remove <rank>");
			sender.sendMessage("§b/er rank prefix <rank> <value>");
			sender.sendMessage("§b/er rank suffix <rank> <value>");
			sender.sendMessage("§b/er psadd <rank> <permission>");
			sender.sendMessage("§b/er psrem <rank> <permission>");
			sender.sendMessage("§b/er psee <rank>");
			sender.sendMessage("§b/er rank <player> (<rank>) - See or set the defined player's rank");
			sender.sendMessage("§b/er rankl - See all active ranks");
			sender.sendMessage("§7§m------------<§r §bEssence §7§m>------------");
			
			
			return true;
		}
		if(args[0].equals("rank")){
			if(args.length <= 1){
				sender.sendMessage("§b/er rank create <name>");
				sender.sendMessage("§b/er rank remove <rank>");
				sender.sendMessage("§b/er rank prefix <rank> <value>");
				sender.sendMessage("§b/er rank suffix <rank> <value>");
				sender.sendMessage("§b/er r <player> (<rank>) - See or set the defined player's rank");
				return false;
			}
			if(args[1].equals("create")){
				if(!super.checkPerm(sender, "essence.rank.create")){
					return false;
				}
				if(args.length != 3){
					sender.sendMessage("§cUsage: /er rank create <name>");
					return false;
				}
				String rankName = args[2];
				if(!StringUtils.isAlphanumeric(rankName) || rankName.length() > 10){
					sender.sendMessage("§cRank name must be alphanumeric and less than 10 characters.");
					return false;
				}
				if(Main.getInstance().getRankManager().getRankByName(rankName) != null){
					sender.sendMessage("§cThere is already a rank with this name.");
					return false;
				}
				Rank rank = new Rank();
				rank.setName(rankName);
				rank.setDataName(rankName.toLowerCase());
				rank.setPrefix("");
				rank.setSuffix("");
				Main.getInstance().getRankManager().getRanks().add(rank);
				sender.sendMessage("§bNew rank was created ("+rank.getName()+")");
				
				return false;
			}
			if(args[1].equals("remove")){
				if(!super.checkPerm(sender, "essence.rank.remove")){
					return false;
				}
				if(args.length != 3){
					sender.sendMessage("§cUsage: /er rank remove <rank>");
					return false;
				}
				Rank rank = Main.getInstance().getRankManager().getRankByName(args[2]);
				if(rank == null){
					sender.sendMessage("§cThis rank doesn't exist.");
					return true;
				}
				rank.deleted = true;
				sender.sendMessage("§bSuccessfully deleted rank "+rank.getName()+".");
				return false;
			}
			if(args[1].equals("prefix")){
				if(!super.checkPerm(sender, "essence.rank.create")){
					return false;
				}
				if(args.length != 4){
					sender.sendMessage("§cUsage: /er rank prefix <rank> <value>");
					return false;
				}
				Rank rank = Main.getInstance().getRankManager().getRankByName(args[2]);
				if(rank == null){
					sender.sendMessage("§cThis rank doesn't exist.");
					return true;
				}
				String prefix = ChatColor.translateAlternateColorCodes('&', args[3]);
				rank.setPrefix(prefix);
				sender.sendMessage("§b"+rank.getName()+"§b's suffix has been updated to '"+prefix+"' §b.");
				return false;
			}
			if(!super.checkPerm(sender, "essence.rank.seeset")){
				return false;
			}
			if(args.length == 1){
				sender.sendMessage("§cUsage : /er rank <player> (<rank>)");
				return true;
			}
			Player player = super.checkPlayer(sender, args[1]);
			if(player == null) return false;
			EssencePlayer eplayer = EssencePlayer.cache.get(player);
			if(args.length == 2){
				sender.sendMessage("§b"+player.getName()+"'s rank : "+eplayer.getRank().getName() +" [ "+eplayer.getRank().getPrefix()+" §r§b]");
				return true;
			}
			Rank rank = Main.getInstance().getRankManager().getRankByName(args[2]);
			if(rank == null){
				sender.sendMessage("§cThis rank doesn't exist.");
				return true;
			}
			eplayer.setRank(rank);
			player.sendMessage("§bYou have now the §l"+rank.getName()+"§r§b rank");
			sender.sendMessage("§b"+player.getName()+" has now the §l"+rank.getName()+" §r§brank");
			return true;
		}
		if(args[0].equals("psadd")){
			if(!super.checkPerm(sender, "essence.rank.permadd")){
				return false;
			}
			if(args.length != 3){
				sender.sendMessage("§cUsage: /er psadd <rank> <permission>");
				return true;
			}
			Rank rank = Main.getInstance().getRankManager().getRankByName(args[1]);
			if(rank == null){
				sender.sendMessage("§cThis rank doesn't exist.");
				return true;
			}
			if(!rank.getPermissions().contains(args[2])) rank.getPermissions().add(args[2]);
			Essence.updatePermission(rank);
			sender.sendMessage("§bAdded permission '"+args[2]+"' to rank "+rank.getName()+".");
			return true;
		}
		if(args[0].equals("psee")){
			if(!super.checkPerm(sender, "essence.rank.permsee")){
				return false;
			}
			if(args.length != 2){
				sender.sendMessage("§cUsage: /er psee <rank>");
				return true;
			}
			Rank rank = Main.getInstance().getRankManager().getRankByName(args[1]);
			if(rank == null){
				sender.sendMessage("§cThis rank doesn't exist.");
				return false;
			}
			StringBuilder sb = new StringBuilder();
			if(rank.getPermissions().isEmpty()){
				sb.append("§bThere is no permission defined to this rank");
			}else{
				sb.append("§bPermissions of the rank "+rank.getName()+":"+"\n");
				for(String permission : rank.getPermissions()){
					sb.append("'"+permission+"', ");
				}
			}
			sender.sendMessage(sb.toString());
			return false;
		}
		if(args[0].equals("psrem")){
			if(!super.checkPerm(sender, "essence.rank.permrem")){
				return false;
			}
			if(args.length != 3){
				sender.sendMessage("§cUsage: /er psrem <rank> <permission>");
				return true;
			}
			Rank rank = Main.getInstance().getRankManager().getRankByName(args[1]);
			if(rank == null){
				sender.sendMessage("§cThis rank doesn't exist.");
				return true;
			}
			if(rank.getPermissions().contains(args[2])) rank.getPermissions().remove(args[2]);
			Essence.updatePermission(rank);
			sender.sendMessage("§bRemoved permission '"+args[2]+"' from rank "+rank.getName()+".");
			return true;
		}
		if(args[0].equals("rankl")){
			if(!super.checkPerm(sender, "essence.rank.ranklist")){
				return false;
			}	
			StringBuilder sb = new StringBuilder();
			sb.append("§bAvalaible Ranks: ");
			for(Rank rank : Main.getInstance().getRankManager().getRanks()){
				if(!rank.deleted) sb.append(ChatColor.GRAY+rank.getName()+", ");
			}
			sb.replace(sb.length()-2, sb.length(), "");
			sender.sendMessage(sb.toString());
			return true;
		}
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		List<String> ret = new ArrayList<>();
		if(args.length == 2){
			if(args[0].equals("r")){
				Bukkit.getOnlinePlayers().forEach(p->{
					ret.add(p.getName());
					});
				return ret;
			}
		}
		if(args.length == 3){
			if(args[0].equals("r")){
				Main.getInstance().getRankManager().getRanks().forEach(r->{
					ret.add(r.getName());
				});
				return ret;
			}
		}
		
		return ret;
	}

}
