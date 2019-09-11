package test.ig.prices;

public class Price {

	private Double bid, ask, lastTraded;

	public Price() {
		super();
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public Double getLastTraded() {
		return lastTraded;
	}

	public void setLastTraded(Double lastTraded) {
		this.lastTraded = lastTraded;
	}
}
