package database.hibernate;

public class ConnectionStatus {
	public ConnectionStatus(boolean ok) {
		this.ok=true;
	}
	public ConnectionStatus(boolean ok, String message) {
		super();
		this.ok = ok;
		this.message = message;
	}
	private boolean ok=false;
	private String message="";
	public boolean isOk() {
		return ok;
	}
	public String getMessage() {
		return message;
	}

}
