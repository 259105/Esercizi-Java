package elezioni;

public class Elettore implements Cittadino {
	private String nome;
	private String cognome;
	private boolean votato=false;
	protected boolean capolista=false;
	protected boolean candidato=false;
	protected long numVoti=0;

	public Elettore(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public String getCognome() {
		// TODO Auto-generated method stub
		return cognome;
	}

	@Override
	public boolean haVotato() {
		// TODO Auto-generated method stub
		return votato;
	}
	
	public void setVotato(boolean votato) {
		this.votato=votato;
	}

	@Override
	public boolean isCapolista() {
		// TODO Auto-generated method stub
		return capolista;
	}
	
	public void setCapolista(boolean cap) {
		capolista=cap;
	}

	@Override
	public boolean isCandidato() {
		// TODO Auto-generated method stub
		return candidato;
	}
	
	public void setCandidato(boolean cand) {
		candidato=cand;
	}

	@Override
	public long getNumeroVoti() {
		// TODO Auto-generated method stub
		return numVoti;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nome).append(" ").append(cognome);
		return builder.toString();
	}

	public void addvoto() {
		numVoti++;
	}
}
