package net.essence.module.exception;

import java.util.logging.Level;

import org.bukkit.Bukkit;

public class InvalidModuleFormatException extends ModuleException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7741502900847049986L;

	public InvalidModuleFormatException(String errorMessage) {
		super(errorMessage);
	}

	@Override
	public void printStackTrace(){
		super.printStackTrace();
		
		System.out.println("");
		
		Bukkit.getLogger().log(Level.WARNING, "         Basic format : (module.yml)");
		Bukkit.getLogger().log(Level.WARNING, "   Main: path.to.your.MainClass");
		Bukkit.getLogger().log(Level.WARNING, "   Name: <NameOfYourModule>");
		Bukkit.getLogger().log(Level.WARNING, "   Author: <AuthorA> | <AuthorA, AuthorB>");
		Bukkit.getLogger().log(Level.WARNING, "   Version: YourVersion");

		
	}
	
}
