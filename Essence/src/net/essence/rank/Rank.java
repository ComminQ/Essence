package net.essence.rank;

import java.util.ArrayList;
import java.util.List;

public class Rank {

	private List<String> permissions;
	private String prefix;
	private String suffix;
	
	public Rank(){
		this.setPermissions(new ArrayList<>());
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
}

