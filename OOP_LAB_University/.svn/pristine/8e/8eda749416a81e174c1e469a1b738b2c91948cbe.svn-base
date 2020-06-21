package university;

public class Esame {
	private int voto;
	private Studente studente;
	private Corso corso;
	
	public Esame(int voto, Studente studente, Corso corso) {
		this.voto = voto;
		this.studente = studente;
		this.corso = corso;
	}

	public Corso getCorso() {
		return corso;
	}

	public int getVoto() {
		return voto;
	}
	
	public Studente getStudente() {
		return studente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esame other = (Esame) obj;
		
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		
		if (studente == null) {
			if (other.studente != null)
				return false;
		} else if (!studente.equals(other.studente))
			return false;
		
		return true;
	}
	
	
}
