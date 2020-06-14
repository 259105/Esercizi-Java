package transactions;

import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class Carrier implements Comparable<Carrier> {
	private String name;
	private SortedSet<Region> regions;
	
	public Carrier(String name, SortedSet<Region> regions) {
		super();
		this.name = name;
		this.regions = regions;
		addRegion(regions);
	}
	
	public String getName() {
		return this.name;
	}
	public void addRegion(SortedSet<Region> regions) {
		for(Region region:regions) {
			this.regions.add(region);
			region.addCarrier(this);
		}
	}
	public List<String> getRegions(){
		return regions.stream().map(Region::getName).collect(Collectors.toList());
	}

	@Override
	public int compareTo(Carrier o) {
		return this.name.compareTo(o.name);
	}
}
