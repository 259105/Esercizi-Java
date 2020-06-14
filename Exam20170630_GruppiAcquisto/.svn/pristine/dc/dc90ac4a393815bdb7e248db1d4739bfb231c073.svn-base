package groups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Supplier {
	private String name;
	private SortedMap<String,Product> products = new TreeMap<>();
	private Map<String,Group> groups=new HashMap<>();
	private List<Bid> bids=new ArrayList<>();
	
	public Supplier(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addProduct(String nameProduct,Product product) {
		products.put(nameProduct,product);
	}
	public List<String> getProducts(){
		return products.keySet().stream().collect(Collectors.toList());
	}
	public void addGroup (Group g) {
		groups.put(g.getName(), g);
	}
	public void addBid(Bid bid) {
		bids.add(bid);
	}
	public int getNumOfBids() {
		return bids.size();
	}
	public List<Group> getGroupByBids(){
		return bids.stream().map(Bid::getGroup).collect(Collectors.toList());
	}

}
