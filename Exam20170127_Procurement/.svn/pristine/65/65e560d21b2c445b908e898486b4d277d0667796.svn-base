package warehouse;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Warehouse {
	private static int nordine=1;
	private Map<String,Product> products=new HashMap<>();
	private Map<String,Supplier> suppliers=new HashMap<>();
	private Map<String,Order> orders=new HashMap<>();
	
	public Product newProduct(String code, String description){
		Product p =new Product(code,description);
		products.put(code, p);
		return p;
	}
	
	public Collection<Product> products(){
		return products.values();
	}

	public Product findProduct(String code){
		return products.get(code);
	}

	public Supplier newSupplier(String code, String name){
		Supplier s =new Supplier(code,name);
		suppliers.put(code, s);
		return s;
	}
	
	public Supplier findSupplier(String code){
		return suppliers.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		if(!supp.supplies().contains(prod))
			throw new InvalidSupplier();
		Order o=new Order(nordine,prod,supp,quantity);
		orders.put("ORD"+nordine, o);
		nordine++;
		prod.newOrder(o);
		return o;
	}

	public Order findOrder(String code){
		return orders.get(code);
	}
	
	public List<Order> pendingOrders(){
		return orders.values().stream()
				.filter(o->!o.delivered())
				.sorted(Comparator.comparing(Order::getCodeProduct))
				.collect(Collectors.toList());
	}

	public Map<String,List<Order>> ordersByProduct(){
	    return orders.values().stream()
	    		.collect(Collectors.groupingBy(Order::getCodeProduct,
	    				HashMap::new,
	    				Collectors.toList()));
	}
	
	public Map<String,Long> orderNBySupplier(){
	    return orders.values().stream()
	    		.filter(Order::delivered)
	    		.collect(Collectors.groupingBy(Order::getNomeSupplier,
	    				TreeMap::new,
	    				Collectors.counting()));
	}
	
	public List<String> countDeliveredByProduct(){
	    return products.values().stream()
	    		.collect(Collectors.collectingAndThen(Collectors.groupingBy(Product::getCode,
	    				HashMap::new,
	    				Collectors.summingLong(Product::getNumDeliveredOrder))
	    				, (Map<String,Long> m) -> m.entrySet().stream()
	    				.sorted(Comparator.comparing( (e)-> -e.getValue() ) )
	    				.map(e-> e.getKey()+" - "+e.getValue())
	    				.collect(Collectors.toList())));
	}
}
