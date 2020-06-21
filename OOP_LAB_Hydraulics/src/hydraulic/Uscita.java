package hydraulic;

public class Uscita {
	
	Element elemento;
	double proporzione;
	double flow;
	
	public Uscita(Element elemento, double proporzione, double flow) {
		this.elemento = elemento;
		this.proporzione = proporzione;
		this.flow = flow;
	}

	public Element getElemento() {
		return elemento;
	}

	public void setElemento(Element elemento) {
		this.elemento = elemento;
	}

	public double getProporzione() {
		return proporzione;
	}

	public void setProporzione(double proporzione) {
		this.proporzione = proporzione;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}
	
}
