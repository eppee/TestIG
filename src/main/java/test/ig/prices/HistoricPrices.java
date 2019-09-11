package test.ig.prices;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricPrices {

	private List<HistoricPrice> prices = new ArrayList<HistoricPrice>();

	public HistoricPrices() {
		super();
	}

	public List<HistoricPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<HistoricPrice> prices) {
		this.prices = prices;
	}
}
