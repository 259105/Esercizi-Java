package hydraulic;

import java.util.List;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	
	protected String name=new String();
	protected int numOutput;
	protected Element ingresso;
	protected List<Uscita> uscite;
	
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		this.name=name;
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public abstract void connect(Element elem);
	/**
	 * se a.connect(b)
	 * connette l'entrara di a all'uscita di b
	 * è come se fosse una lista doppio linkata,
	 * posso risalire dalla sink alla Source
	 * @param elem the element that will be placed upstream
	 */
	public abstract void connectReverse(Element elem);
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		return null;
	}
	
	abstract public void simulate(SimulationObserver observer);
	public abstract void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck);
	
	abstract public StringBuffer layout(String buff);
	
	static public double inFlow(Element ingresso,Element currentE) {
		double flow=0;
		for(int i=0;i<ingresso.numOutput;i++)
			if(ingresso.uscite.get(i)!=null) {
				if(ingresso.uscite.get(i).getElemento()==currentE) {
					flow=ingresso.uscite.get(i).getFlow();
					break;
				}
			}else {
				flow=0;
			}
		return flow;
	}
	
	static public String spaceGen(int n) {
		StringBuffer spaceb=new StringBuffer("");
		for(int i=0;i<n;i++)
			spaceb.append(" ");
		return spaceb.toString();
	}
}
