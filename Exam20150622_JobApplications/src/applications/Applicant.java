package applications;
import java.util.*;
import java.util.stream.Collectors;

public class Applicant {
	private String name;
	private SortedMap<Skill,Integer> capabilities;
	private Position position;
	private Position winnedPosition;
	
	public Applicant(String name, SortedMap<Skill, Integer> capabilities) {
		super();
		this.name = name;
		this.capabilities = capabilities;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public String getCapabilitiesToString() {
		if(capabilities.isEmpty()) return "";
		return capabilities.entrySet().stream()
				.map((Map.Entry<Skill,Integer> e) -> e.getKey()+":"+e.getValue() )
				.collect(Collectors.joining(","));
	}
	
	public Set<Skill> getCapabilities(){
		return capabilities.keySet();
	}
	
	public boolean checkSkill(Collection<Skill> skills) {
		boolean b=true;
		for(Skill s:skills)
			if(!capabilities.containsKey(s))
				b=false;
		return b;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Integer getSumOfLevel(Collection<Skill> skills) {
		return capabilities.entrySet().stream()
				.filter(e->skills.contains(e.getKey()))
				.collect(Collectors.summingInt((Map.Entry<Skill,Integer> e) -> e.getValue()));
	}

	public Position getWinnedPosition() {
		return winnedPosition;
	}

	public void setWinnedPosition(Position winnedPosition) {
		this.winnedPosition = winnedPosition;
	}
}
