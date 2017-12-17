package net.essence.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class EssenceFile extends YamlConfiguration {

	public Plugin plugin;
	private File file;
	private String fileName;

	public EssenceFile(String fileName, Plugin pl) {
		this.fileName = fileName;
		this.plugin = pl;
		this.file = new File(pl.getDataFolder(), fileName);
		if (!file.exists()) {
			plugin.saveResource(file.getName(), false);
		}
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(this.file);
		Reader stream = null;
		this.options().copyDefaults(true);
		try {
			stream = new InputStreamReader(plugin.getResource(fileName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (stream == null) {
			YamlConfiguration y = YamlConfiguration.loadConfiguration(file);
			this.setDefaults(y);
		}
		saveDefault();
		reload();
	}

	public EssenceFile reload() {
		try {
			super.load(this.file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return this;
	}

	public EssenceFile save() {
		try {
			super.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public EssenceFile saveDefault() {
		plugin.saveResource(file.getName(), false);
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}