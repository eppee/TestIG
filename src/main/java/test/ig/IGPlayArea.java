package test.ig;

public class IGPlayArea {

	private static boolean DEMO = true;

	public static void main(String[] args) {
		IG ig = new IG("IX.D.DOW.IFM.IP", new IGUpdatable() {

			public void newBidAlert(double bid) {
				System.out.println("Bid alert:" + bid);
			}
		}, DEMO);
	}

}
