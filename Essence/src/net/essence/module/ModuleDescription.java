package net.essence.module;

public class ModuleDescription {

	private String name;
	private String[] authors;
	private String version;
	private String description;
	
	public ModuleDescription(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static class ModuleDescriptionBuilder{
		
		public ModuleDescription description;
		
		public ModuleDescriptionBuilder(String name){
			description = new ModuleDescription();
			description.setName(name);
		}
		
		public ModuleDescriptionBuilder withAuthor(String... authors){
			this.description.setAuthors(authors);
			return this;
		}
		
		public ModuleDescriptionBuilder withDescription(String desc){
			this.description.setDescription(desc);
			return this;
		}
		
		public ModuleDescriptionBuilder withVersion(String version){
			this.description.setVersion(version);
			return this;
		}
		
		public ModuleDescription build(){
			return this.description;
		}
		
	}
	
}
