package groups;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {
	private String name;
	private Product product;
	private Map<String,Customer> customers=new HashMap<>();
	private Map<String,Supplier> suppliers=new HashMap<>();
	private Map<String,Bid> bids=new HashMap<>();
	
	public Group(String name, Product product, Map<String, Customer> customers) {
		super();
		this.name=name;
		this.product = product;
		this.customers = customers;
	}

	public String getName() {
		return name;
	}
	public Product getProduct() {
		return product;
	}
	public void setSuppliers(Map<String,Supplier> suppliers) {
		this.suppliers=suppliers;
	}
	public void addBid(Bid bid) {
		bids.put(bid.getSupplierName(),bid);
		
	}
	public Collection<Customer> getCustomer() {
		return customers.values();
	}
	public Collection<Supplier> getSuppliers() {
		return suppliers.values();
	}
	public Bid getBidOfSupplier(String supplier) {
		return bids.get(supplier);
	}
	public Collection<Bid> getBids(){
		return bids.values();
	}
	public String getBidsString() {
		return bids.values().stream()
				.sorted(Comparator.comparing(Bid::getPrice).thenComparing(Comparator.comparing(Bid::getSupplierName)))
				.map(Bid::toStringSupplierPrice)
				.collect(Collectors.joining(","));
	}
}
