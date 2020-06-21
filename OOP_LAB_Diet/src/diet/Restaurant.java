package diet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant {
	
	private String name;
	private Food food;
	private SortedMap<String,NutritionalElement> menu= new TreeMap<>();
	private List<Interval> orari=new LinkedList<>();
	private Queue<Order> ordini= new PriorityQueue<>();
	
	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		this.name=name;
		this.food=food;
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		if(hm.length%2!=0)
			return; //sono dispari
		for(int i=0;i<hm.length;i+=2) {
			Interval in=new Interval(hm[i],hm[i+1]);
			orari.add(in);
		}
	}
	
	public List<Interval> getHours(){
		return orari;
	}
	
	public Menu getMenu(String name) {
		return (Menu) menu.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m=new Menu(name,food);
		menu.put(name,m);
		return m;
	}
	
	public void addOrder(Order order) {
		ordini.add(order);
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		StringBuffer sb=new StringBuffer("");
		List<Order> ordini=new LinkedList<>(this.ordini);
		ordini.sort(Comparator.naturalOrder());
		for(Order o: this.ordini)
			if(o.getStatus()!=status)
				ordini.remove(o);
		for(Order o: ordini)
			sb.append(o.toString());
		return sb.toString();
	}
}
