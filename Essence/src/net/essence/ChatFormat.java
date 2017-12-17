package net.essence;

public class ChatFormat {

	private static ChatFormat chatformat;
	private String format;
	
	public ChatFormat(String s){
		chatformat = this;
		this.setFormat(s);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public static ChatFormat getChatformat() {
		return chatformat;
	}

	public static void setChatformat(ChatFormat chatformat) {
		ChatFormat.chatformat = chatformat;
	}
	
	
	
	
}
