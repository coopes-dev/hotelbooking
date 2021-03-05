package uk.ac.liv.comp220.commands;

public class ServerResponse {
	
	private int responseCode=0;
	private String responseMessage="";
	private ServerInfo serverInfo=new ServerInfo();
	private String challenge="";
	
	public ServerResponse(ResponseCode responseCode,String message) {
		this(responseCode);
		this.responseMessage=message;
	}
	
	public ServerResponse(ResponseCode responseCode) {
		super();
		this.responseCode = responseCode.ordinal();
		this.responseMessage=ResponseCode.class.getSimpleName()+":"+responseCode.toString();
		
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
	
	

}
