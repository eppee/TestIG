package test.ig.prices.stream;

import com.lightstreamer.client.ClientListener;
import com.lightstreamer.client.LightstreamerClient;

public class StatusListener implements ClientListener {

	public void onListenEnd(LightstreamerClient client) {
		System.out.println("onListenEnd");
	}

	public void onListenStart(LightstreamerClient client) {
		System.out.println("onListenStart");
	}

	public void onPropertyChange(String property) {}

	public void onServerError(int errorCode, String errorMessage) {
		System.out.println("onServerError[" + errorCode + "]: " + errorMessage);
	}

	public void onStatusChange(String status) {
		System.out.println("StatusChange: " + status);
	}

}
