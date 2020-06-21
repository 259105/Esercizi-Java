package trail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Location implements Comparable<Location> {
	private String name;
	private int orderNum;
	private Map<String,Delegate> delegates=new HashMap<>();
	private List<Passage> passages=new LinkedList<>();

    public Location(String name, int orderNum) {
		super();
		this.name = name;
		this.orderNum = orderNum;
	}

	public String getName(){
        return name;
    }

    public int getOrderNum(){
        return orderNum;
    }

	@Override
	public int compareTo(Location o) {
		return this.orderNum-o.orderNum;
	}
	
	public void addDelegate(Delegate delegate) {
		delegates.put(delegate.getCodFisc(), delegate);
	}
	
	public List<String> getDelegatesToString(){
		return delegates.values().stream()
				.map(Delegate::toString)
				.sorted()
				.collect(Collectors.toList());
	}
	
	public void addPassage(Passage passage) {
		passages.add(passage);
	}
	
	public List<Passage> getPassages(){
		return passages;
	}
	
	public List<Runner> getPassagesOfRunners(){
		return passages.stream()
				.map(Passage::getRunner)
				.collect(Collectors.toList());
	}
}
