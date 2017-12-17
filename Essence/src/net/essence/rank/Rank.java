package net.essence.rank;

import java.util.ArrayList;
import java.util.List;

public class Rank {

	private List<String> permissions;
	private String name;
	private String prefix;
	private String suffix;
	private String dataName;
	public int ID;
	
	public Rank(int ID){
		this.setPermissions(new ArrayList<>());
		this.ID = ID;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "{EssenceRank = "+this.getName()+"}";
	}


	public String getDataName() {
		return dataName;
	}


	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
}

