package groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Customer {
	private String name;
	private SortedMap<String,Group> groups = new TreeMap<>();
	private List<Bid> votes=new ArrayList<>();
	
	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addGroup(String name,Group group) {
		groups.put(name,group);
	}
	public List<String> getGroupsString(){
		return groups.keySet().stream().collect(Collectors.toList());
	}
	public Collection<Group> getGroups(){
		return groups.values();
	}
	public void addVote(Bid vote) {
		votes.add(vote);
	}
}
