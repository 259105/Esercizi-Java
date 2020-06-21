package elezioni;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Lista {
	private String nome;
	private String motto;
	private Cittadino capolista;
	private Set<Cittadino> candidati=new HashSet<>();
	private long numVoti=0;
	private Elezione e;
	
	public Lista(String nome, String motto){
		this.nome=nome;
		this.motto=motto;
	}
	
	public String getNome(){
		return nome;
	}

	public String getMotto(){
		return motto;
	}
	
	public void assegnaElezione(Elezione ele) {
		e=ele;
	}
	
	public void assegnaCapolista(Cittadino capolista)
			throws CandidatoNonValido {
		if(capolista.isCapolista())
			throw new CandidatoNonValido();
		Elettore c = (Elettore) capolista;
		c.setCapolista(true);
		c.setCandidato(true);
		this.capolista=c;
	}

	public void aggiungiCandidato(Cittadino candidato)
			throws CandidatoNonValido {
		if(candidato.isCandidato())
			throw new CandidatoNonValido();
		Elettore c = (Elettore) candidato;
		c.setCandidato(true);
		candidati.add(c);
	}

	public Cittadino getCapolista(){
		return capolista;
	}

	/**
	 * Restuisce la collezione dei candidati
	 * (NON include il capolista)
	 */
	public Collection<Cittadino> getCandidati(){
		return candidati;
	}
	
	
	public long getNumeroVoti(){
		return numVoti;
	}
	
	public void addVoto() {
		numVoti++;
	}

	public double getPercentualeVoti(){
		return ((double)getNumeroVoti())/((double)e.getNumeroVotanti());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nome;
	}
}
