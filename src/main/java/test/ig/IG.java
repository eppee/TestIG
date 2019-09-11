package test.ig;

import org.apache.http.Header;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;
import com.lightstreamer.client.SubscriptionListener;

import se.perkodar.RestClient;
import se.perkodar.RestClient.RequestMethod;
import test.ig.prices.HistoricPrices;
import test.ig.prices.stream.IGSubscriptionListener;
import test.ig.prices.stream.StatusListener;

public class IG {

	private boolean demo;
	private String ticker;
	private IGLogin login;

	public IG(String ticker, boolean demo) throws Exception {
		this.ticker = ticker;
		this.demo = demo;

		if (this.demo) {
			login = new IGLogin("xxx", "xxx");
		} else {
			login = new IGLogin("xxx", "xxx", "xxx");
		}
		if (login()) {
			System.out.println("Logged in!");
		}
	}

	public void startPriceStreaming(IGUpdatable igUpdatable) throws Exception {
		if (login.loggedIn()) {
			Session session;
			if ((session = getSession(login)) != null) {
				SubscriptionListener listener = new IGSubscriptionListener(igUpdatable);
				LightstreamerClient lsClient = new LightstreamerClient(session.getLightstreamerEndpoint() + "", "DEFAULT");
				lsClient.connectionDetails.setUser(session.getAccountId() + "");
				lsClient.connectionDetails.setPassword("CST-" + session.getCst() + "|XST-" + session.getXst());

				lsClient.addListener(new StatusListener());

				Subscription stocks = new Subscription("MERGE", new String[] {"MARKET:" + ticker}, new String[] {"BID", "OFFER"});
				stocks.addListener(listener);

				lsClient.subscribe(stocks);
				lsClient.connect();
			}
		}
		throw new RuntimeException("Not logged in");
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
				client.getResponseCode();
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

	public HistoricPrices getHistoricPrices (String resolution, int maxCandles) throws Exception {
		if (login.loggedIn()) {
			RestClient client=new RestClient(getBaseUrl() + "/gateway/deal/prices/" + ticker + "?resolution=" + resolution + "&max=" + maxCandles + "&pageSize=" + maxCandles);
			client.addHeader("Content-Type", "application/json; charset=UTF-8");
			client.addHeader("Accept", "application/json; charset=UTF-8");
			client.addHeader("X-IG-API-KEY", login.getKey());
			client.addHeader("Version", "3");
			client.addHeader("CST", login.getCst());
			client.addHeader("X-SECURITY-TOKEN", login.getXSecToken());
			ObjectMapper mapper = new ObjectMapper();
			client.execute(RequestMethod.GET);
			String response = client.getResponse();
			if (client.getResponseCode() != 200) {
				System.err.println(response);
			}
			return mapper.readValue(response, HistoricPrices.class);
		}
		throw new RuntimeException("Not logged in");
	}

	public Session getSession(IGLogin login) throws Exception {
		String sessionCst = null, sessionXst = null;
		if (login.loggedIn()) {
			RestClient client=new RestClient(getSessionUrl());
			client.addHeader("Content-Type", "application/json; charset=UTF-8");
			client.addHeader("Accept", "application/json; charset=UTF-8");
			client.addHeader("X-IG-API-KEY", login.getKey());
			client.addHeader("Version", "1");
			client.addHeader("CST", login.getCst());
			client.addHeader("X-SECURITY-TOKEN", login.getXSecToken());
			ObjectMapper mapper = new ObjectMapper();
			client.execute(RequestMethod.GET);
			Header[] responseHeaders = client.getResponseHeaders();
			if (responseHeaders != null) {
				for (Header header : responseHeaders) {
					if ("cst".equals(header.getName().toLowerCase())) {
						sessionCst = header.getValue();
					}
					if ("x-security-token".equals(header.getName().toLowerCase())) {
						sessionXst = header.getValue();
					}
				}
			}
			String response = client.getResponse();
			Session session = mapper.readValue(response, Session.class);
			session.setCst(sessionCst);
			session.setXst(sessionXst);
			return session;
		}
		return null;
	}

	private String getBaseUrl() {
		return demo ? "https://demo-api.ig.com" : "https://api.ig.com";
	}

	private String getSessionUrl() {
		return getBaseUrl() + "/gateway/deal/session?fetchSessionTokens=true";
	}
}
