package transactions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Region implements Comparable<Region> {
	private String name;
	private SortedSet<String> places;
	private SortedSet<Carrier> carriers=new TreeSet<>();
	
	public Region(String name, SortedSet<String> places) {
		super();
		this.name = name;
		this.places = places;
	}
	public Region(String name, String... placeNames) {
		super();
		this.name = name;
		this.places = Arrays.stream(placeNames).collect(Collectors.toCollection(TreeSet::new));
	}
	
	public String getName() {
		return name;
	}
	public void addPlaces(SortedSet<String> places) {
		for(String place:places) 
			places.add(place);
	}
	public List<String> getPlaces(){
		return new ArrayList<>(places);
	}
	public void addCarrier(Carrier c) {
		carriers.add(c);
	}
	public List<String> getCarriers(){
		return carriers.stream().map(Carrier::getName).collect(Collectors.toList());
	}
	@Override
	public int compareTo(Region arg0) {
		return this.name.compareTo(arg0.name);
	}
}
