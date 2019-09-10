package test.ig;

import org.apache.http.Header;

import se.perkodar.RestClient;
import se.perkodar.RestClient.RequestMethod;

public class IG {

	private boolean demo;
	private String epic;
	private IGLogin login;

	public IG(String epic, IGUpdatable igUpdatable, boolean demo) {
		this.epic = epic;
		this.demo = demo;

		if (this.demo) {
			login = new IGLogin("xxx", "xxx");
		} else {
			login = new IGLogin("xxx", "xxx", "xxx");
		}
		if (login()) {
			System.out.print("Logged in");
		}
	}

	public boolean login() {
		if (!login.loggedIn()) {
			RestClient client=new RestClient(getSessionUrl());
			client.addHeader("Content-Type", "application/json; charset=UTF-8");
			client.addHeader("Accept", "application/json; charset=UTF-8");
			client.addHeader("X-IG-API-KEY", login.getKey());
			client.addHeader("Version", "2");
			client.setPost(login);
			try {
				client.execute(RequestMethod.POST);
				Header[] responseHeaders = client.getResponseHeaders();
				if (responseHeaders != null) {
					String cst = null;
					String xSecToken = null;
					for (Header header : responseHeaders) {
						if ("cst".equals(header.getName().toLowerCase())) {
							cst = header.getValue();
						}
						if ("x-security-token".equals(header.getName().toLowerCase())) {
							xSecToken = header.getValue();
						}
					}
					login.loggedIn(cst, xSecToken);
				}
			} catch (Exception e) {
				System.err.println(client.getResponseCode() + ":" + client.getResponse());
				e.printStackTrace();
				return false;
			}
		}
		return login.loggedIn();
	}

	private String getBaseUrl() {
		return demo ? "https://demo-api.ig.com" : "https://api.ig.com";
	}

	private String getSessionUrl() {
		return getBaseUrl() + "/gateway/deal/session?fetchSessionTokens=true";
	}
}
