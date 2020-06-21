package ticketing;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Component {
	private String name;
	private Component predecessor=null;
	private Map<String,Component> subComponents=new HashMap<>();
	
	public Component(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the subComponents
	 */
	public Collection<Component> getSubComponents() { 
		Collection<Component> col=new LinkedList<>();
		for(Component c:subComponents.values()) {
			col.add(c);
			col.addAll(c.getSubComponents());
		}
		return col;
	}
	
	public Set<String> getSubComponentNames(){ 
		Set<String> set=new LinkedHashSet<>();
		for(Component c:subComponents.values()) {
			set.add(c.getName());
			set.addAll(c.getSubComponentNames());
		}
		return set;
	}
	
	public void setPredecessor(Component pred) {
		predecessor=pred;
	}
	
	public Component getPredecessor() {
		return predecessor;
	}
	
	public String getPath() {
		if(predecessor==null)
			return "/"+name;
		String path=predecessor.getPath();
		return path.concat("/"+name);
	}
	
	/**
	 * @param subComponents the subComponents to set
	 */
	public void addSubComponents(Component subComponent) {
		this.subComponents.put(subComponent.getName(), subComponent);
	}
	
	
}
