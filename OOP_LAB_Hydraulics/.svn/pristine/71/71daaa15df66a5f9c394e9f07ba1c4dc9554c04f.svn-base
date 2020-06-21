package hydraulic;

import java.util.ArrayList;
/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {
	
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name);
		numOutput=2;
		uscite= new ArrayList<>(numOutput);
		for(int i=0;i<numOutput;i++)
			uscite.add(i,null);
	}
	
    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		if(noutput>=numOutput) return;
		if(elem instanceof Source) return;
		Uscita u= new Uscita(elem,0.5,SimulationObserver.NO_FLOW);
		uscite.set(noutput,u);
		elem.connectReverse(this);
		this.reload_proportion();
	}
	
	@Override
	public void connectReverse(Element elem){
//		super.connectReverse(elem);
		if(elem instanceof Sink) return;
		this.ingresso=elem;
	}
	
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
        Element U[] = new Element[numOutput];
        int i=0;
        for(Uscita u : uscite) {
        	U[i++]=u.getElemento();
        }
        return U;
    }
	
    public double getFlow(int noutput) {
    	if(uscite.get(noutput)==null) return 0;
    	double flow=inFlow(ingresso,this)*uscite.get(noutput).getProporzione();
    	uscite.get(noutput).setFlow(flow);
		return flow;
	}
    public void reload_proportion() {
    	int nConnected=0;
		for(Uscita u : uscite)
			if(u!=null)
				nConnected++;
		for(Uscita u : uscite) {
			if(u!=null)
				u.setProporzione((double)1/nConnected);
		}
    }
    
    @Override
	public void simulate(SimulationObserver observer) {
    	double vFlow[]= new double[numOutput];
    	for(int i=0;i<numOutput;i++)
    		vFlow[i]=this.getFlow(i);
    	
		observer.notifyFlow("Split",name,inFlow(ingresso,this),vFlow);
		for(Uscita u : uscite) 
			if(u!=null)
				u.getElemento().simulate(observer);
	}
    @Override
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
    	double vFlow[]= new double[numOutput];
    	for(int i=0;i<numOutput;i++)
    		vFlow[i]=this.getFlow(i);
    	double inflow=inFlow(ingresso,this);
		observer.notifyFlow("Split",name,inflow,vFlow);
		if(enableMaxFlowCheck && inflow>maxFlow)
			observer.notifyFlowError("Tap", name, inflow, maxFlow);
		for(Uscita u : uscite) 
			if(u!=null)
				u.getElemento().simulate(observer,enableMaxFlowCheck);
	}

	
	@Override
	public StringBuffer layout(String buff) {
		StringBuffer sb=new StringBuffer("-> ["+name+"]Split +");
		
		String space=buff+spaceGen(sb.length()-1);
		
		int i=0;
		for(Uscita u : uscite) {
			if(u!=null) {
				if(i==0) {
					sb.append(u.getElemento().layout(space+"|"));
				}else if(i==numOutput-1) {
					sb.append(space+"+"+u.getElemento().layout(space+" "));
				}
				else {
					sb.append(space+"+"+u.getElemento().layout(space+"|"));
				}
			}else {
				if(i==0) {
					sb.append(" *\n");
				}else if(i==numOutput-1) {
					sb.append(space+"+ *\n");
				}else {
					sb.append(space+"+ *\n");
				}
			}
			i++;
			if(i!=numOutput)
				sb.append(space+"|\n");
		}
		return sb;
	}

	@Override
	public void connect(Element elem) {
		// TODO Auto-generated method stud	
	}
	@Override
	public void setMaxFlow(double maxFlow) {
		this.maxFlow=maxFlow;	
	}

	
}
