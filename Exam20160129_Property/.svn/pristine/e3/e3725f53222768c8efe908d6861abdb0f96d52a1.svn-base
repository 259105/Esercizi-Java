package managingProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Owner {
	private String id;
	private Set<Appartment> appartments=new HashSet<>();
	
	public Owner(String id, Set<Appartment> appartments) {
		super();
		this.id = id;
		this.appartments = appartments;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the appartments
	 */
	public Set<Appartment> getAppartments() {
		return appartments;
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
