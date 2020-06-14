package warehouse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {
	private String code;
	private String description;
	private int quantity;
	private Map<String,Supplier> suppliers=new HashMap<>();
	private Map<String,Order> orders=new HashMap<>();

	
	public Product(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getCode(){
		return code;
	}

	public String getDescription(){
		return description;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}

	public void decreaseQuantity(){
		if(quantity>0)
			quantity--;
	}

	public int getQuantity(){
		return quantity;
	}
	
	public long getNumDeliveredOrder() {
		return orders.values().stream()
				.filter(Order::delivered)
				.collect(Collectors.counting());
	}
	
	public void newSupplier(Supplier supplier){
		suppliers.put(supplier.getCodice(), supplier);
	}

	public List<Supplier> suppliers(){
		return suppliers.values().stream()
				.sorted(Comparator.comparing(Supplier::getNome))
				.collect(Collectors.toList());
	}
	
	public void newOrder(Order order) {
		orders.put(order.getCode(), order);
	}

	public List<Order> pendingOrders(){
		return orders.values().stream()
				.filter(o->!o.delivered())
				.sorted(Comparator.comparing(Order::getQuantity).reversed())
				.collect(Collectors.toList());
	}
}
