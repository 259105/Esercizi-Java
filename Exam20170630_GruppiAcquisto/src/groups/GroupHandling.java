package groups;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GroupHandling {
	private SortedMap<String,Product> products =new TreeMap<>();
	private Map<String,Supplier> suppliers =new HashMap<>();
	private SortedMap<String,Group> groups=new TreeMap<>();
	private Map<String,Customer> customers=new HashMap<>();
	//R1	
	public void addProduct (String productTypeName, String... supplierNames) 
			throws GroupHandlingException {
		if(products.containsKey(productTypeName))
			throw new GroupHandlingException();
		Map<String,Supplier> suppliers =new HashMap<>();
		for(String s:supplierNames) {
			if(this.suppliers.containsKey(s)) {
				suppliers.put(s, this.suppliers.get(s));
				continue;
			}
			Supplier supp= new Supplier(s);
			this.suppliers.put(s, supp);
			suppliers.put(s, supp);
		}
		Product p=new Product(productTypeName);
		products.put(productTypeName,p);
		for(Supplier s:suppliers.values())
			s.addProduct(productTypeName,p);
	}
	
	public List<String> getProductTypes (String supplierName) {
		return suppliers.get(supplierName).getProducts();
	}
	
//R2	
	public void addGroup (String name, String productName, String... customerNames) 
			throws GroupHandlingException {
		if(groups.containsKey(name) || !products.containsKey(productName))
			throw new GroupHandlingException("unknown product");
		Map<String,Customer> customers =new HashMap<>();
		for(String s : customerNames) {
			if(this.customers.containsKey(s)) {
				customers.put(s, this.customers.get(s));
				continue;
			}
			Customer c=new Customer(s);
			this.customers.put(s, c);
			customers.put(s, c);
		}
		Group g=new Group(name,products.get(productName),customers);
		groups.put(name, g);
		for(Customer cust:customers.values())
			cust.addGroup(name, g);
	}
	
	public List<String> getGroups (String customerName) {
        return customers.get(customerName).getGroupsString();
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames)
			throws GroupHandlingException {
		for(String s:supplierNames) {
			if(!suppliers.containsKey(s)  
					|| !suppliers.get(s).getProducts().contains(groups.get(groupName).getProduct().getName()))
				throw  new GroupHandlingException(s+" unsuitable");
		}
		groups.get(groupName).setSuppliers(Stream.of(supplierNames)
				.map(s -> suppliers.get(s))
				.collect(Collectors.toMap(Supplier::getName,(s)->s)));
		for(String s:supplierNames)
			suppliers.get(s).addGroup(groups.get(groupName));
	}
	
	public void addBid (String groupName, String supplierName, int price)
			throws GroupHandlingException {
		if(!groups.get(groupName).getSuppliers().contains(suppliers.get(supplierName)))
			throw new GroupHandlingException(supplierName+" not in "+groupName);
		Group g = groups.get(groupName);
		Bid b=new Bid(g,suppliers.get(supplierName),price);
		g.addBid(b);
		suppliers.get(supplierName).addBid(b);;
	}
	
	public String getBids (String groupName) {
        return groups.get(groupName).getBidsString();
	}
	
	
//R4	
	public void vote (String groupName, String customerName, String supplierName)
			throws GroupHandlingException {
		if(!customers.get(customerName).getGroupsString().contains(groupName)
				|| !suppliers.get(supplierName).getGroupByBids().contains(groups.get(groupName)))
			throw new GroupHandlingException();
		Customer c=customers.get(customerName);
		Group g=groups.get(groupName);
		g.getBidOfSupplier(supplierName).addVote(c);
	}
	
	public String getVotes (String groupName) {
		return groups.get(groupName).getBids().stream()
			.filter(b-> b.getNumVotes()>0)
			.map(b->b.getSupplierName()+":"+b.getNumVotes())
			.collect(Collectors.joining(","));
	}
	
	public String getWinningBid (String groupName) {
        Optional<Bid> max= groups.get(groupName).getBids().stream()
        		.collect(Collectors.maxBy(Comparator.comparing(Bid::getNumVotes).thenComparing(Comparator.comparing(Bid::getPrice).reversed())));
        if(!max.isPresent())
        	return null;
        Bid maxreal=max.get();
        return maxreal.getSupplierName()+":"+maxreal.getNumVotes();
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
		SortedMap<String,Integer> mappa=new TreeMap<>();
		for(Group g: groups.values()) {
			if(g.getBids().size()==0)
				continue;
			mappa.put(g.getProduct().getName(), g.getBids().stream().map(Bid::getPrice).collect(Collectors.maxBy(Comparator.naturalOrder()) ).get()  );
		}
		return mappa;
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
        return suppliers.values().stream()
        		.filter(s->s.getNumOfBids()>0)
        		.collect(Collectors.groupingBy(Supplier::getNumOfBids,
        				()->new TreeMap<Integer,List<String>>(Comparator.reverseOrder()),
        				Collectors.mapping(Supplier::getName,Collectors.toList())));
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
        return customers.values().stream()
        		.filter(c->c.getGroups().size()>0)
        		.flatMap(c->c.getGroups().stream())
        		.collect(Collectors.groupingBy((Group g) -> g.getProduct().getName(),
        				TreeMap::new,
        				Collectors.mapping(g -> g.getCustomer().stream(), Collectors.counting())));
	}
	
}
