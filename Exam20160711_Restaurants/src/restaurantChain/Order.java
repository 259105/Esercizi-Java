package restaurantChain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
	private String referentName;
	private List<Menu> menues=new LinkedList<>();
	private Double price;
	
	public Order(String referentName, List<Menu> menues) {
		super();
		this.referentName = referentName;
		this.menues = menues;
		this.price=menues.stream()
				.collect(Collectors.summingDouble(Menu::getPrice));
	}

	/**
	 * @return the referentName
	 */
	public String getReferentName() {
		return referentName;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	public List<Menu> getMenues() {
		return menues;
	}
	
}
