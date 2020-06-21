package managingProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Building {
	private String id;
	private int nApp;
	private Set<Appartment> appartments = new HashSet<>();
	
	public Building(String id, int nApp) {
		super();
		this.id = id;
		this.nApp = nApp;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the nApp
	 */
	public int getnApp() {
		return nApp;
	}
	
	public void addAppartment(Appartment appartment) {
		appartments.add(appartment);
	}
	
	public List<Request> getTotalRequestOfApartment(){
		return appartments.stream()
				.flatMap(a-> a.getRequest().stream())
				.collect(Collectors.toList());
	}
	
	public int getTotalAmount() {
		return getTotalRequestOfApartment().stream()
				.collect(Collectors.summingInt(Request::getAmount));
	}
}
