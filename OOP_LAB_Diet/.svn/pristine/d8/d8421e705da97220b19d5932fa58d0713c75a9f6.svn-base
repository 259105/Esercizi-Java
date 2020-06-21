package diet;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order implements Comparable<Order>{
	private User user;
	private Restaurant restaurant;
	Orario orario;
	private OrderStatus status;
	private PaymentMethod method;
	private SortedMap<String,Integer> menu=new TreeMap<>();
	
	public Order(User user, Restaurant restaurant, int h, int m) {
		this.user = user;
		this.restaurant = restaurant;
		this.orario= new Orario(h,m);
		this.status =OrderStatus.ORDERED;
		this.method = PaymentMethod.CASH;
	}
	
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.method=method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return method;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.status=newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return status;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		if(this.menu.containsKey(menu)) 
			this.menu.replace(menu,quantity);
		else 
			this.menu.put(menu, quantity);
		return this;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer("");
		sb.append(restaurant.getName()+", "+user.getFirstName()+" "+user.getLastName()+" : ("+orario.toString()+"):\n");
		Set<String> key=menu.keySet();
		for(String sm : key) {
			int qta=menu.get(sm);
			sb.append("\t"+sm+"->"+qta+"\n");
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Order o) {
		int cr=this.restaurant.getName().compareTo(o.restaurant.getName());
		if(cr>0) return 1;
		else if(cr<0) return -1;
		int cn=this.user.getFirstName().compareTo(o.user.getFirstName());
		if(cn>0) return 1;
		else if(cn<0) return -1;
		int cu=this.user.getLastName().compareTo(o.user.getLastName());
		if(cu>0) return 1;
		else if(cu<0) return -1;
		int ct=this.orario.compareTo(o.orario);
		if(ct>0) return 1;
		else if(ct<0) return -1;
		return 0;
	}
	
}
