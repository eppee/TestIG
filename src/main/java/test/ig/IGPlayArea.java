package test.ig;

import test.ig.prices.HistoricPrice;
import test.ig.prices.HistoricPrices;

public class IGPlayArea {

	private static boolean DEMO = true;

	public static void main(String[] args) throws Exception {
		IG ig = new IG("IX.D.DOW.IFM.IP", DEMO);
		HistoricPrices prices = ig.getHistoricPrices("MINUTE", 5);
		for (HistoricPrice price : prices.getPrices()) {
			System.out.println(price.getSnapshotTime() + " | " + price.getClosePrice().getBid());
		}
		ig.startPriceStreaming(new IGUpdatable() {

			public void newBidAlert(double bid) {
				System.out.println("Bid alert:" + bid);
			}
		});
	}

}
