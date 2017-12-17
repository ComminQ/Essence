package net.essence.module.exception;

public abstract class ModuleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4203162022348693854L;

	public ModuleException(String errorMessage){
		super("ModuleException : "+errorMessage);
	}
	
}
