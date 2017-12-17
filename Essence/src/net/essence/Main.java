package net.essence;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.essence.command.ModuleInfoCmd;
import net.essence.command.ModuleListCmd;
import net.essence.event.PlayerChat;
import net.essence.event.module.ModuleDisableEvent;
import net.essence.utils.EssenceFile;
import net.essence.utils.EssenceFileManager;

public class Main extends JavaPlugin {

	private static Main instance;
	public static final String CONFIG = "options.yml";
	public static final boolean DEBUG = false;
	protected EssenceFileManager fileManager;
	private ModuleManager moduleManager;

	@Override
	public void onEnable() {
		instance = this;

		this.fileManager = new EssenceFileManager(this);
		this.fileManager.getYamls().add(new EssenceFile("options.yml", this));

		Arrays.asList(new Command[] {

				new ModuleListCmd(), new ModuleInfoCmd()

		}).forEach(cmd -> {
			this.getServer().getCommandMap().register(cmd.getName(), cmd);
		});

		this.moduleManager = new ModuleManager();
		
		if(this.fileManager.getConfigFile(CONFIG).getBoolean("module_active")){
			System.out.println("Module are now enabled");
			this.moduleManager.loadModules();
		}else{
			System.out.println("Module are now disabled");
		}

		new ChatFormat(this.fileManager.getConfigFile(CONFIG).getString("chat_format"));
		
		PluginManager plm = Bukkit.getPluginManager();
		plm.registerEvents(new PlayerChat(), this);
		

	}

	@Override
	public void onDisable() {
		this.moduleManager.getModules().forEach(module -> {
			module.Disable();
			this.getServer().getPluginManager().callEvent(new ModuleDisableEvent(module));
			System.out.println("Disabling " + module.getDescription().getName() + "...");
		});

		this.moduleManager.getLoader().forEach(loader -> {
			try {
				loader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	public static Main getInstance() {
		return instance;
	}

	public static void setInstance(Main instance) {
		Main.instance = instance;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

}
