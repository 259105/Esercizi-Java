package restaurantChain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chain {	
		private Map<String,Restaurant> restaurants=new HashMap<>();
		
		public void addRestaurant(String name, int tables) throws InvalidName{
			if(restaurants.containsKey(name))
				throw new InvalidName();
			restaurants.put(name, new Restaurant(name,tables));
		}
		
		public Restaurant getRestaurant(String name) throws InvalidName{
			if(!restaurants.containsKey(name))
				throw new InvalidName();
			return restaurants.get(name);
		}
		
		public List<Restaurant> sortByIncome(){
			return restaurants.values().stream()
					.sorted(Comparator.comparing(Restaurant::getIncome).reversed())
					.collect(Collectors.toList());
		}
		
		public List<Restaurant> sortByRefused(){
			return restaurants.values().stream()
					.sorted(Comparator.comparing(Restaurant::getRefused))
					.collect(Collectors.toList());
		}
		
		public List<Restaurant> sortByUnusedTables(){
			return restaurants.values().stream()
					.sorted(Comparator.comparing(Restaurant::getUnusedTables))
					.collect(Collectors.toList());
		}
}
