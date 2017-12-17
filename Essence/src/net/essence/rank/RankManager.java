package net.essence.rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import net.essence.Main;
import net.essence.utils.EssenceFile;

public class RankManager {

	private List<Rank> ranks = new ArrayList<>();
	private Rank defaultRank;
	
	public RankManager(){
		this.load();
	}
	
	public Rank getRankByName(String string){
		for(Rank rank : this.ranks){
			if(rank.getName().contains(string)) return rank;
		}
		return null;
		
	}
	
	public void save(){
		
		EssenceFile rankYML = Main.getInstance().getFileManager().getConfigFile("rank.yml");
		
		for(Rank rank : this.ranks){
			ConfigurationSection sec = rankYML.getConfigurationSection("ranks."+rank.getDataName());
			for(String data : sec.getStringList("permissions")){
				System.out.println(data + " ! " + rank.getPermissions());
				if(!rank.getPermissions().contains("data")){
					sec.getStringList("permissions").remove(data);
				}
			}
			for(String data : rank.getPermissions()){
				System.out.println(data +" ! "+sec.getStringList("permissions"));
				if(!sec.getStringList("permissions").contains(data)){
					sec.getStringList("permissions").add(data);
				}
			}
		}
		
		rankYML.save();
		
		
	}
	
	private void load(){
		
		EssenceFile rankYML = Main.getInstance().getFileManager().getConfigFile("rank.yml");
		
		Set<String> all = rankYML.getConfigurationSection("ranks").getKeys(false);
		int n = all.size();
		for(String data : all){
			Rank rank = new Rank(n);
			
			rank.setName(ChatColor.translateAlternateColorCodes('&', rankYML.getString("ranks."+data+".name")));
			rank.setPrefix(ChatColor.translateAlternateColorCodes('&', rankYML.getString("ranks."+data+".prefix")));
			rank.setSuffix(ChatColor.translateAlternateColorCodes('&', rankYML.getString("ranks."+data+".suffix")));
			rank.setPermissions(rankYML.getConfigurationSection("ranks."+data).getStringList("permissions"));
			rank.setDataName(data);
			if(rankYML.getBoolean("ranks."+data+".default")) this.defaultRank = rank;
			
			ranks.add(rank);
			n--;
		}
		
		
	}
	
	public List<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public Rank getDefaultRank() {
		return defaultRank;
	}

	public void setDefaultRank(Rank defaultRank) {
		this.defaultRank = defaultRank;
	}
	
}
