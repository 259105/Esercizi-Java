package applications;

import java.util.*;
import java.util.stream.Collectors;


public class Skill implements Comparable<Skill> {
	private String name;
	private SortedMap<String,Position> positions=new TreeMap<>();
	
	public Skill(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {return name;}
	public List<Position> getPositions() {return positions.values().stream().collect(Collectors.toList());}
	
	public boolean checkPosition(String name){
		return positions.containsKey(name);
	}
	
	public Position getPosition(String name) {
		return positions.get(name);
	}
	
	public void addPositions(Position position){
		positions.put(position.getName(), position);
	}

	@Override
	public int compareTo(Skill arg0) {
		return this.name.compareTo(arg0.name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
	public long totalApplicants() {
		long l=0;
		for(Position p:positions.values())
			l+=p.getNOfApplicants();
		return l;
	}
}