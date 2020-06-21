package trail;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Delegate {
	private String name;
	private String surname;
	private String codFisc;
	private SortedMap<String,Location> locations=new TreeMap<>();
	private List<Passage> passages=new LinkedList<>();
	
	public Delegate(String name, String surname, String codFisc) {
		super();
		this.name = name;
		this.surname = surname;
		this.codFisc = codFisc;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the codFisc
	 */
	public String getCodFisc() {
		return codFisc;
	}
	
	public void addLocation(Location location) {
		locations.put(location.getName(), location);
	}
	
	public boolean haveLocation(String location) {
		if(locations.containsKey(location))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(surname);
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(codFisc);
		return builder.toString();
	}
	
	public void addPassage(Passage passage) {
		passages.add(passage);
	}
	
}
