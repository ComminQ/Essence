package net.essence;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;

import net.essence.event.module.ModuleEnableEvent;
import net.essence.event.module.ModuleLoadEvent;
import net.essence.module.Module;
import net.essence.module.ModuleDescription;
import net.essence.module.ModuleDescription.ModuleDescriptionBuilder;
import net.essence.module.exception.InvalidModuleFormatException;
import net.essence.module.exception.InvalidModuleInheritException;

public class ModuleManager {

	private List<Module> modules;
	private List<ModuleClassLoader> loader;

	public ModuleManager() {
		this.modules = new ArrayList<>();
		this.loader = new ArrayList<>();
	}

	public void loadModules() {
		File f = new File("plugins/module");
		if (f.exists()) {
			if (f.isDirectory()) {
				for (File file : f.listFiles()) {
					if (!file.isDirectory()) {
						try {
							this.loadModule(file);
						} catch (InvalidModuleFormatException e) {
							e.printStackTrace();
						} catch (InvalidModuleInheritException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} else {
			try {
				f.mkdir();
			} catch (SecurityException e) {
				e.printStackTrace();
				if (Main.DEBUG) {
					System.out.println("Cannot create folder 'Module' (Permission ?)");
				}
			}
		}

		this.modules.stream().forEach(module -> {
			module.Enable();
			Main.getInstance().getServer().getPluginManager().callEvent(new ModuleEnableEvent(module));
			module.setEnabled(true);
			System.out.println("Enabling " + module.getDescription().getName() + "...");
		});

	}


	public Module getModuleByName(String name){
		if(name.equals("")) return null;
		
		for(Module m  : this.modules){
			if(m.getDescription().getName().contains(name)) return m;
		}
		return null;
	}
	
	private void loadModule(File f) throws InvalidModuleFormatException, InvalidModuleInheritException {
		try {
			Module module = null;

			if (!f.getName().contains(".jar")) {
				return;
			}
			JarFile jar = new JarFile(f);
			JarEntry entry = jar.getJarEntry("module.yml");

			if (entry == null) {
				jar.close();
				throw new InvalidModuleFormatException("Module doesn't contains description file");
				
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(jar.getInputStream(entry)));

			Map<String, String> data = this.data(reader);

			ModuleClassLoader loader = null;

			loader = new ModuleClassLoader(data, f, reader, this.getClass().getClassLoader());
			this.loader.add(loader);

			module = loader.module;
			ModuleLoadEvent event = new ModuleLoadEvent(module);
			Bukkit.getPluginManager().callEvent(event);
			this.modules.add(module);
			module.Load();
			System.out.println("Loading " + module.getDescription().getName() + "...");
			jar.close();
		} catch (IOException e) {
			if (Main.DEBUG) {
				System.out.println("" + f.getName() + "is not a jarfile, ignoring...");
			}
		}

	}

	private Map<String, String> data(BufferedReader reader) {
		Map<String, String> map = new HashMap<>();
		reader.lines().forEach(string -> {
			String[] data = string.split("\\: ");
			map.put(data[0], data[1].substring(0, data[1].length()));
		});
		return map;
	}

	public List<Module> getModules() {
		return modules;
	}

	private void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<ModuleClassLoader> getLoader() {
		return loader;
	}

	public void setLoader(List<ModuleClassLoader> loader) {
		this.loader = loader;
	}

}
