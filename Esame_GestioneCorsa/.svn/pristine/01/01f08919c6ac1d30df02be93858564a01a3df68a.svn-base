package trail;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Trail {
	private int bibNumbers=1;
	private int locationNumbers=0;
	private SortedSet<Runner> runners=new TreeSet<>();
	private SortedMap<String,Location> locations=new TreeMap<>();
	private Map<String,Delegate> delegates=new HashMap<>();

    public int newRunner(String name, String surname){
        runners.add(new Runner(name,surname,bibNumbers));
        return bibNumbers++;
    }
    
    public Runner getRunner(int bibNumber){
        for(Runner r:runners)
        	if (r.getBibNumber()==bibNumber)
        		return r;
        return null;
    }
    
    public Collection<Runner> getRunner(String surname){
        return runners.stream()
        		.filter(r->r.getSurname().equals(surname))
        		.collect(Collectors.toList());
    }
    
    public List<Runner> getRunners(){
        return runners.stream()
        		.collect(Collectors.toList());
    }

    public List<Runner> getRunnersByName(){
        return runners.stream()
        		.sorted(Comparator.comparing(Runner::getSurname)
        				.thenComparing(Comparator.comparing(Runner::getName))
        				.thenComparing(Comparator.comparing(Runner::getBibNumber)))
        		.collect(Collectors.toList());
    }
    
    public void addLocation(String location){
        locations.put(location, new Location(location,locationNumbers++));
    }
//    public void addLocation(String name, double lat, double lon, double elevation){
//        
//    }

    public Location getLocation(String location){
        return locations.get(location);
    }

    public List<Location> getPath(){
        return locations.values().stream()
        		.sorted()
        		.collect(Collectors.toList());
    }
    
    public void newDelegate(String name, String surname, String id){
        delegates.put(id, new Delegate(name,surname,id));
    }

    public List<String> getDelegates(){
        return delegates.values().stream()
        		.map(Delegate::toString)
        		.sorted()
        		.collect(Collectors.toList());
    }
    

    public void assignDelegate(String location, String delegate) throws TrailException {
       if(!locations.containsKey(location) || !delegates.containsKey(delegate))
    	   throw new TrailException();
       Location l=locations.get(location);
       Delegate d=delegates.get(delegate);
       l.addDelegate(d);
       d.addLocation(l);
    }
 
    public List<String> getDelegates(String location){
        return locations.get(location).getDelegatesToString();
    }
    
    public long recordPassage(String delegate, String location, int bibNumber) throws TrailException {
        long time=System.currentTimeMillis();
        Runner r;
        Delegate d;
    	if(!delegates.containsKey(delegate) || !locations.containsKey(location) || (r=getRunner(bibNumber))==null
    			|| !(d=delegates.get(delegate)).haveLocation(location)) 
        	throw new TrailException();
    	Location l=locations.get(location);
        Passage p=new Passage(d,l,r,time);
        r.addPassage(p);
        l.addPassage(p);
        d.addPassage(p);
        return time;
    }
    
    public long getPassTime(String position, int bibNumber) throws TrailException {
    	Runner r;
    	if(!locations.containsKey(position) || (r=getRunner(bibNumber))==null) 
    		throw new TrailException();
    	Location l=locations.get(position);
    	for(Passage p:r.getPassages())
    		if(p.getLocation().equals(l))
    			return p.getTime();
        return -1;
    }
    
    public List<Runner> getRanking(String location){
        return locations.get(location).getPassagesOfRunners();
    }

    public List<Runner> getRanking(){
    	List<Location> locRever = locations.values().stream()
        		.sorted(Comparator.reverseOrder())
        		.collect(Collectors.toList());
    	Set<Runner> rank = new LinkedHashSet<>();
        for(Location l:locRever)
        	rank.addAll(getRanking(l.getName()));
        return rank.stream()
        		.collect(Collectors.toList());
    }
}
