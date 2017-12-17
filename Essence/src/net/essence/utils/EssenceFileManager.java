package net.essence.utils;

import java.util.ArrayList;
import java.util.List;

import net.essence.Main;

public class EssenceFileManager {

	private List<EssenceFile> yamls;

	public EssenceFileManager(Main m) {
		this.yamls = new ArrayList<>();
	}

	public EssenceFile getConfigFile(String fileName) {
		for (EssenceFile y : yamls) {
			if (y.getFileName().equalsIgnoreCase(fileName))
				return y;
		}
		return null;
	}

	public List<EssenceFile> getYamls() {
		return yamls;
	}

	public void saveAll() {
		for (EssenceFile y : yamls) {
			y.save();
		}
	}

	public void setYamls(List<EssenceFile> yamls) {
		this.yamls = yamls;
	}

}
