package managingProperties;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
public class PropertyManager {
	private static int nReq=1;
	private Map<String,Building> buildings=new HashMap<>();
	private Map<String,Owner> owners=new HashMap<>();
	private Map<String,Appartment> appartments=new HashMap<>();
	private SortedMap<String,Profession> professions = new TreeMap<>();
	private Map<Integer,Request> requests=new HashMap<>();
	private SortedMap<Integer,String> assigns=new TreeMap<>();
	/**
	 * Add a new building 
	 */
	public void addBuilding(String building, int n) throws PropertyException {
		if(buildings.containsKey(building) || n>100 || n<1)
			throw new PropertyException();
		buildings.put(building, new Building(building,n));
	}

	public void addOwner(String owner, String... apartments) throws PropertyException {
		if(owners.containsKey(owner))
			throw new PropertyException();
		for(String s:apartments) {
			if(appartments.containsKey(s))
				throw new PropertyException();
			String[] par=s.split(":");
			String idBuild=par[0];
			int numApp=Integer.parseInt(par[1]);
			if(!buildings.containsKey(idBuild) || numApp<1 || numApp>buildings.get(idBuild).getnApp())
				throw new PropertyException();
		}
		Set <Appartment> apps=new HashSet<>();
		for(String s:apartments) {
			String[] par=s.split(":");
			Building b=buildings.get(par[0]);
			int numApp=Integer.parseInt(par[1]);
			Appartment a=new Appartment(b,numApp);
			apps.add(a);
			b.addAppartment(a);
			appartments.put(s, a);
		}
		Owner o=new Owner(owner,apps);
		owners.put(owner, o);
	}

	/**
	 * Returns a map ( number of apartments => list of buildings ) 
	 * 
	 */
	public SortedMap<Integer, List<String>> getBuildings() {
		return buildings.values().stream()
				.sorted(Comparator.comparing(Building::getId))
				.collect(Collectors.groupingBy(Building::getnApp,
						TreeMap::new,
						Collectors.mapping(Building::getId, Collectors.toList())));
	}

	public void addProfessionals(String profession, String... professionals) throws PropertyException {
		if(professions.containsKey(profession))
			throw new PropertyException();
		List<String> professionists=professions.values().stream()
				.map(Profession::getProfessionista)
				.flatMap(l->l.stream())
				.collect(Collectors.toList());
		SortedSet<String> currProf=new TreeSet<>();
		for(String s:professionals) {
			if(professionists.contains(s))
				throw new PropertyException();
			if(currProf.contains(s)) throw new PropertyException();
			currProf.add(s);
		}
		Profession p =new Profession(profession,currProf.stream().collect(Collectors.toList()));
		professions.put(profession, p);
	}

	/**
	 * Returns a map ( profession => number of workers )
	 *
	 */
	public SortedMap<String, Integer> getProfessions() {
		return professions.values().stream()
				.collect(Collectors.groupingBy(Profession::getName,
						TreeMap::new,
						Collectors.summingInt(p->p.getProfessionista().size())
						));
	}

	public int addRequest(String owner, String apartment, String profession) throws PropertyException {
		if(!owners.containsKey(owner)|| !appartments.containsKey(apartment) || !professions.containsKey(profession) || !owners.get(owner).getAppartments().contains(appartments.get(apartment)))
			throw new PropertyException();
		Appartment a =appartments.get(apartment);
		Request r=new Request(owners.get(owner),a,professions.get(profession),nReq);
		requests.put(nReq, r);
		a.addRequest(r);
		nReq+=1;
		return nReq-1;
	}

	public void assign(int requestN, String professional) throws PropertyException {
		PropertyException e= new PropertyException();
		if(!requests.containsKey(requestN))
			throw e;
		Request r = requests.get(requestN);
		if(r.isAssigned() || !r.getProfession().getProfessionista().contains(professional))
			throw e;
		r.toAssigned();
		assigns.put(requestN, professional);
		r.setProfessionist(professional);
	}

	public List<Integer> getAssignedRequests() {
		return assigns.keySet().stream().collect(Collectors.toList());
	}

	
	public void charge(int requestN, int amount) throws PropertyException {
		if(!requests.containsKey(requestN) || amount >1000 || amount<0)
			throw new PropertyException();
		Request r=requests.get(requestN);
		r.setAmount(amount);
		r.toCompleted();
	}

	/**
	 * Returns the list of request ids
	 * 
	 */
	public List<Integer> getCompletedRequests() {
		return requests.values().stream()
				.filter(Request::isCompleted)
				.map(Request::getnReq)
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns a map ( owner => total expenses )
	 * 
	 */
	public SortedMap<String, Integer> getCharges() {
		return owners.values().stream()
				.filter(o->o.getTotalAmount()>0)
				.collect(Collectors.groupingBy(Owner::getId,
						TreeMap::new,
						Collectors.mapping(Owner::getTotalAmount, Collectors.summingInt(i->i))));
	}

	/**
	 * Returns the map ( building => ( profession => total expenses) ).
	 * Both buildings and professions are sorted alphabetically
	 * 
	 */
	public SortedMap<String, Map<String, Integer>> getChargesOfBuildings() {
		SortedMap<String, Map<String, Integer>> map= new TreeMap<>();
		for(Building b: buildings.values()) {
			if (b.getTotalAmount()<=0) continue;
			Map<String, Integer> mapp = b.getTotalRequestOfApartment().stream()
					.filter(r->r.getAmount()>0)
				.collect(Collectors.groupingBy(Request::getProfessionist,
						TreeMap::new,
						Collectors.summingInt(Request::getAmount)));
			map.put(b.getId(), mapp);
		}
		return map;
	}

}
