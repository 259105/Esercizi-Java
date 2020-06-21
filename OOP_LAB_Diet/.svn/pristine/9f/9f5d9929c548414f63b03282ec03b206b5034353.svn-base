package diet;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	
	private SortedMap<String,User> users=new TreeMap<>();
	private SortedMap<String,Restaurant> restaurants=new TreeMap<>();
	
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		restaurants.put(r.getName(), r);
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		return restaurants.keySet();
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u=new User(firstName,lastName,email,phoneNumber);
		users.put(u.getLastName()+" "+u.getFirstName(), u);
		return u;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		return users.values();
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Restaurant r=restaurants.get(restaurantName);
		Orario firstOpen=r.getHours().get(0).getOpen();
		Orario o=new Orario(h,m);
		for(Interval in : r.getHours()) {
			if(o.compareTo(in.getOpen())==1 && o.compareTo(in.getClose())==-1) {
				Order or=new Order(user,r,h,m);
				r.addOrder(or);
				return or;
			}
			if(o.compareTo(in.getOpen())==-1) {
				Order or=new Order(user,r,in.getOpen().getOra(),in.getOpen().getMinuti());
				r.addOrder(or);
				return or;
			}
		}
		Order or=new Order(user,r,firstOpen.getOra(),firstOpen.getMinuti());
		r.addOrder(or);
		return or;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		List<Restaurant> openedR=new LinkedList<>(restaurants.values());
		int open;
		Orario o=new Orario(time);
		for(Restaurant r : restaurants.values()) {
			open=0;
			for(Interval in : r.getHours()) {
				if(o.compareTo(in.getOpen())==1 && o.compareTo(in.getClose())==-1) {
					open=1;
					break;
				}
				if(o.getOra()==in.getClose().getOra() && o.getMinuti()==in.getClose().getMinuti())
					open=0;
			}
			if(open==0) 
				openedR.remove(r);
			
		}
		return openedR;
	}

}
