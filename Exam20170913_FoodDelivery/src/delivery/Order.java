package delivery;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import delivery.Delivery.OrderStatus;

public class Order {
	private int customerId;
	private int orderId;
	private Map<String,Item> items=new HashMap<>();
	private OrderStatus status=OrderStatus.NEW;
	
	public Order(int customerId,int Id) {
		super();
		this.customerId = customerId;
		this.orderId = Id;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int addItem(Menu menu, int qty) {
		if(items.containsKey(menu.getDescrizione())) {
			Item i=items.get(menu.getDescrizione());
			i.setQty(qty);
			return i.getQty();
		}
        items.put(menu.getDescrizione(), new Item(orderId,menu,qty));
        return qty;
    }
	public List<String> getItems(){
		return items.values().stream().map(Item::toString).collect(Collectors.toList());
	}
	public double getPrezzo() {
		return items.values().stream()
				.collect(Collectors.summingDouble(Item::getPrezzo));
	}
	public OrderStatus getStatus() {
		return status;
	}
	private int stimaTempoB() {
		return items.values().stream()
				.map(Item::getTempo)
				.collect(Collectors.maxBy(Comparator.naturalOrder()))
				.get();
	}
	public int confirm() {
		status=OrderStatus.CONFIRMED;
		return stimaTempoB()+20;
	}
	public int start() {
		status=OrderStatus.PREPARATION;
		return stimaTempoB()+15;
	}
	public int deliver() {
		status=OrderStatus.ON_DELIVERY;
		return 15;
	}
	public void complete() {
		status=OrderStatus.DELIVERED;
	}
	
}
