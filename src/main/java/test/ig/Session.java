package test.ig;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Session {

	private String clientId;
	private String accountId;
	private Integer timezoneOffset;
	private String locale;
	private String currency;
	private String lightstreamerEndpoint;
	private String cst, xst;

	public Session() {
		super();
	}

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Integer getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(Integer timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLightstreamerEndpoint() {
		return lightstreamerEndpoint;
	}
	public void setLightstreamerEndpoint(String lightstreamerEndpoint) {
		this.lightstreamerEndpoint = lightstreamerEndpoint;
	}

	@JsonIgnore
	public String getCst() {
		return cst;
	}

	@JsonIgnore
	public void setCst(String cst) {
		this.cst = cst;
	}

	@JsonIgnore
	public String getXst() {
		return xst;
	}

	@JsonIgnore
	public void setXst(String xst) {
		this.xst = xst;
	}
}
