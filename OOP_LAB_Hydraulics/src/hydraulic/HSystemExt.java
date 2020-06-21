package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuffer sb=new StringBuffer("");
		for(Element e : elements) 
			if(e instanceof Source)
				sb.append(e.layout(""));
		return sb.toString();
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public void deleteElement(String name) {
		for(Element e : elements)
			if(!(e instanceof Split))
				if(e.getName().equals(name)) {
					removeElement(e);
					break;
				}
					
	}
	
	public void removeElement(Element e) {
		e.ingresso.uscite.get(0).setElemento(e.uscite.get(0).getElemento());
		e.uscite.get(0).getElemento().ingresso=e.ingresso;
		elements.remove(e);
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		for(Element e : elements) 
			if(e instanceof Source)
				e.simulate(observer,enableMaxFlowCheck);
	}
	
}
