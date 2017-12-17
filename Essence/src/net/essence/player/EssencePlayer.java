package net.essence.player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import net.essence.Main;
import net.essence.rank.Rank;


public class EssencePlayer {

	public static Map<Player, EssencePlayer> cache = new HashMap<>();
	
	private Rank rank;
	private FileConfiguration conf;
	private final PermissionAttachment permission;
	private Player player;
	private File file;
	
	public EssencePlayer(Player player){
		Validate.notNull(player);
		cache.put(player, this);
		this.rank = Main.getInstance().getRankManager().getDefaultRank();
		this.load(player.getUniqueId());
		this.permission = player.addAttachment(Main.getInstance());
		
		Essence.loadPermission(player);
		this.player = player;
		
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public void setRank(Rank rank){
		this.rank = rank;
		Essence.updatePermissionPlayer(player);
	}
	
	
	public void save(){
		this.conf.set("rank", this.rank.getName());
		try {
			conf.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void load(UUID id){
		
		FileConfiguration conf = null;
		File file = new File("plugins"+File.separator+"Essence"+File.separator+"playerdata"+File.separator+id.toString()+".yml");
		if(file.exists()){
			conf = YamlConfiguration.loadConfiguration(file);
			
			this.rank = Main.getInstance().getRankManager().getRankByName(conf.getString("rank"));
			
		}else{
			try{
				file.createNewFile();
			}catch(SecurityException | IOException e){
				e.printStackTrace();
			}
			conf = YamlConfiguration.loadConfiguration(file);
			conf.set("rank", rank.getName());
		}
		try {
			conf.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.conf = conf;
		this.file = file;
	}

	@Override
	public String toString(){
		return "{EssencePlayer = "+player.getName()+"}";
	}
	
	public PermissionAttachment getPermission() {
		return permission;
	}

	public void setPermission(PermissionAttachment permission) {
		if(this.permission != null)
			try {
				throw new Exception("Cannot set final field permission from this class"+this.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	
	
	
}
