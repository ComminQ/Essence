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
			if(rank.getName().contains(string) && !rank.deleted) return rank;
		}
		return null;
	}
	
	public void save(){
		
		EssenceFile rankYML = Main.getInstance().getFileManager().getConfigFile("rank.yml");
		
		for(Rank rank : this.ranks){
			
			ConfigurationSection sec1 = rankYML.getConfigurationSection("ranks."+rank.getDataName());
			if(sec1 == null){
				rankYML.createSection("ranks."+rank.getDataName());
				ConfigurationSection sec2 = rankYML.getConfigurationSection("ranks."+rank.getDataName());
				sec2.createSection("name");
				sec2.createSection("prefix");
				sec2.createSection("suffix");
				sec2.createSection("default");
				sec2.createSection("permissions");
				
				sec1 = rankYML.getConfigurationSection("ranks."+rank.getDataName());
				sec1.set("name", rank.getName());
				sec1.set("prefix", rank.getPrefix());
				sec1.set("suffix", rank.getSuffix());
				sec1.set("default", false);
				sec1.set("permissions", rank.getPermissions());
				
			}else{
				if(rank.deleted){
					rankYML.set("ranks."+rank.getDataName(), null);
				}else{
					sec1.set("permissions", rank.getPermissions());
					sec1.set("prefix", rank.getPrefix());
					sec1.set("suffix", rank.getSuffix());
				}
				
			}
			
			
			
			
		}
		
		rankYML.save().reload();
	}
	
	private void load(){
		
		EssenceFile rankYML = Main.getInstance().getFileManager().getConfigFile("rank.yml");
		
		Set<String> all = rankYML.getConfigurationSection("ranks").getKeys(false);
		int n = all.size();
		for(String data : all){
			if(rankYML.get("ranks."+data+".name") == null) continue;
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
