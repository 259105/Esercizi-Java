package transactions;
import java.util.*;
import java.util.stream.Collectors;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {
	private SortedSet<String> places=new TreeSet<>(); // per fare l'ultimo stream non funzionante, fare meglio i places con una classe..
	private SortedMap<String,Region> regions=new TreeMap<>();
	private SortedMap<String,Carrier> carriers=new TreeMap<>();
	private Map<String,Request> requests = new HashMap<>();
	private Map<String,Offer> offers = new HashMap<>();
	private Map<String,Transaction> transactions= new HashMap<>();
	
//R1
	public List<String> addRegion(String regionName, String... placeNames) {
			SortedSet<String> places=new TreeSet<>();
			for(String place:placeNames) {
				if(!this.places.contains(place))
					places.add(place);
				this.places.add(place);
			}
		if(regions.size()==0)
			places.addAll(Arrays.stream(placeNames).collect(Collectors.toList()));
		if(regions.containsKey(regionName)) {
			Region r=regions.get(regionName);
			r.addPlaces(places);
			return r.getPlaces(); 
		}
		regions.put(regionName, new Region(regionName,places));
		return regions.get(regionName).getPlaces(); 		
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		if(carriers.containsKey(carrierName)) {
			Carrier c=carriers.get(carrierName);
			c.addRegion(Arrays.stream(regionNames)
					.filter(s->regions.containsKey(s))
					.map(s->regions.get(s))
					.collect(Collectors.toCollection(TreeSet::new)));
			return c.getRegions();
		}
		carriers.put(carrierName, new Carrier(carrierName,Arrays.stream(regionNames)
				.filter(s->regions.containsKey(s))
				.map(s->regions.get(s))
				.collect(Collectors.toCollection(TreeSet::new))));
		return carriers.get(carrierName).getRegions();
	}
	
	public List<String> getCarriersForRegion(String regionName) {
		return regions.get(regionName).getCarriers();
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
		if(requests.containsKey(requestId) || !places.contains(placeName) )
			throw new TMException();
		requests.put(requestId, new Request(requestId,placeName,productId));
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
		if(offers.containsKey(offerId) || !places.contains(placeName) )
			throw new TMException();
		offers.put(offerId,new Offer(offerId,placeName,productId));
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
		Request r=requests.get(requestId);
		Offer o=offers.get(offerId);
		Carrier c=carriers.get(carrierName);
		Region rr=null,ro=null;
		for(Region region:regions.values()){
			if(region.getPlaces().contains(r.getPlaceName())) 
				rr=region;
			if(region.getPlaces().contains(o.getPlaceName()))
				ro=region;
		}
		if(rr==null || ro==null 
				|| r.getProductId().compareTo(o.getProductId())!=0
				|| !c.getRegions().contains(rr.getName()) 
				|| !c.getRegions().contains(ro.getName()) )
			throw new TMException();
		Collection<Transaction> vals=transactions.values();
		for(Transaction val:vals) {
			if(val.getRequest().equals(r) || val.getOffer().equals(o)) 
				throw new TMException();
		}
		transactions.put(transactionId, new Transaction(transactionId,c,r,o));
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		transactions.get(transactionId).setScore(score);
		if(score>=1 && score<=10)
			return true;
		return false;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		return null;		
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		return transactions.values().stream()
				.filter(t->t.getScore()>=minimumScore)
				.collect(Collectors.groupingBy(t -> t.getCarrier().getName(),
						TreeMap::new,
						Collectors.summingInt(Transaction::getScore)));
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		return transactions.values().stream()
				.collect(Collectors.groupingBy(t-> t.getRequest().getProductId(),
						TreeMap::new,
						Collectors.counting()));
	}
	
	
}

