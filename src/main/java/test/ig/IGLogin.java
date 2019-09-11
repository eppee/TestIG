package test.ig;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IGLogin {

	private String key;

	private String identifier;
	private String password;
	private String cst;
	private String xSecToken;

	public IGLogin(String identifier, String password) { //DEMO Konstruktor
		this.identifier = identifier;
		this.password = password;
		this.key = "xxx";
	}

	public IGLogin(String identifier, String password, String key) {
		System.out.println("**************************************************");
		System.out.println("*********************** LIVE *********************");
		System.out.println("**************************************************");
		System.out.println("*********************** LIVE *********************");
		System.out.println("**************************************************");
		this.identifier = identifier;
		this.password = password;
		this.key = key;
	}

	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public void loggedIn(String cst, String xSecToken) {
		this.cst = cst;
		this.xSecToken = xSecToken;
	}
	public boolean loggedIn() {
		return cst != null && xSecToken != null;
	}

	@JsonIgnore
	public String getCst() {
		return cst;
	}

	@JsonIgnore
	public String getXSecToken() {
		return xSecToken;
	}
}
