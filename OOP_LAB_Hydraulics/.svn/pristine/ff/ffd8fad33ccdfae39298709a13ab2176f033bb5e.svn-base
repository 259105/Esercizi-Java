package hydraulic;

import java.util.ArrayList;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {
	
	public Source(String name) {
		super(name);
		numOutput=1;
		uscite= new ArrayList<Uscita>(numOutput);
	}
	
	@Override
	public void connect(Element elem){
//		super.connect(elem);
		Uscita u= new Uscita(elem,1.0,SimulationObserver.NO_FLOW);
		uscite.add(0,u);
		elem.connectReverse(this);
	}
	
	@Override
	public Element getOutput(){
		return uscite.get(0).getElemento();
	}
	
	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		uscite.get(0).setFlow(flow);
	}
	
	@Override
	public void simulate(SimulationObserver observer) {
		observer.notifyFlow("Source",name,SimulationObserver.NO_FLOW,uscite.get(0).getFlow());
		if(uscite.get(0).getElemento()!=null)
			uscite.get(0).getElemento().simulate(observer);
	}
	@Override
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		observer.notifyFlow("Source",name,SimulationObserver.NO_FLOW,uscite.get(0).getFlow());
		if(uscite.get(0).getElemento()!=null)
			uscite.get(0).getElemento().simulate(observer,enableMaxFlowCheck);
	}
	
	@Override
	public void connectReverse(Element elem) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public StringBuffer layout(String buff) {
		StringBuffer sb=new StringBuffer("["+name+"]Source ");
		buff=buff+spaceGen(sb.length());
		sb.append(uscite.get(0).getElemento().layout(buff));
		return sb;
	}

	@Override
	public void setMaxFlow(double maxFlow) {
		return;
	}

	
}
