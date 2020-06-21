package managingProperties;

import java.util.LinkedList;
import java.util.List;

public class Appartment {
	private Building building;
	private int numApp;
	private List<Request> requests=new LinkedList<>();
	
	public Appartment(Building building, int numApp) {
		super();
		this.building = building;
		this.numApp = numApp;
	}

	/**
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * @return the numApp
	 */
	public int getNumApp() {
		return numApp;
	}
	
	public void addRequest(Request r) {
		requests.add(r);
	}
	
	public List<Request> getRequest(){
		return requests;
	}
}
