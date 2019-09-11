package test.ig.prices.stream;

import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.Subscription;
import com.lightstreamer.client.SubscriptionListener;

import test.ig.IGUpdatable;

public class IGSubscriptionListener implements SubscriptionListener {

	private IGUpdatable igUpdatable;

	public IGSubscriptionListener(IGUpdatable igUpdatable) {
		this.igUpdatable = igUpdatable;
	}

	public void onClearSnapshot(String itemName, int itemPos) {
		System.out.println("onClearSnapshot");
	}

	public void onCommandSecondLevelItemLostUpdates(int lostUpdates, String key) {
		System.out.println("onCommandSecondLevelItemLostUpdates");
	}

	public void onCommandSecondLevelSubscriptionError(int code, String message, String key) {
		System.out.println("onCommandSecondLevelSubscriptionError");
	}

	public void onEndOfSnapshot(String itemName, int itemPos) {
		System.out.println("onEndOfSnapshot");
	}

	public void onItemLostUpdates(String itemName, int itemPos, int lostUpdates) {
		System.out.println("onItemLostUpdates");
	}

	public void onItemUpdate(ItemUpdate itemUpdate) {
		String bid = itemUpdate.getFieldsByPosition().get(1);
		String ask = itemUpdate.getFieldsByPosition().get(2);
		double doubleBid;
		try {
			doubleBid = (Double.parseDouble(bid) + ((Double.parseDouble(ask) - Double.parseDouble(bid)) / 2.0));
		} catch (NumberFormatException e) {
			System.out.println("Could not transform " + bid + " and " + ask);
			doubleBid = 0;
		}
		if (doubleBid > 0) {
			igUpdatable.newBidAlert(doubleBid);
		}
	}

	public void onListenEnd(Subscription subscription) {
		System.out.println("onListenEnd");
	}

	public void onListenStart(Subscription subscription) {
		System.out.println("onListenStart");
	}

	public void onRealMaxFrequency(String frequency) {
		System.out.println("Frequency is " + frequency);
	}

	public void onSubscription() {}

	public void onSubscriptionError(int code, String message) {
		System.out.println("SubscriptionError: [" + code + "]: " + message);
	}

	public void onUnsubscription() {}
}
