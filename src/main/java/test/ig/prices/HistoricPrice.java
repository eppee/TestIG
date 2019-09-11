package test.ig.prices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricPrice {

	private String snapshotTime;
	private Price openPrice, highPrice, lowPrice, closePrice;
	private Integer lastTradedVolume;

	public HistoricPrice() {
		super();
	}

	public String getSnapshotTime() {
		return snapshotTime;
	}
	public void setSnapshotTime(String snapshotTime) {
		this.snapshotTime = snapshotTime;
	}
	public Price getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Price openPrice) {
		this.openPrice = openPrice;
	}
	public Price getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Price highPrice) {
		this.highPrice = highPrice;
	}
	public Price getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Price lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Price getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Price closePrice) {
		this.closePrice = closePrice;
	}

	public Integer getLastTradedVolume() {
		return lastTradedVolume;
	}

	public void setLastTradedVolume(Integer lastTradedVolume) {
		this.lastTradedVolume = lastTradedVolume;
	}
}
