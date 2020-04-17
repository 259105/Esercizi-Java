package university;

public class Corso {
	private static final int MAXISCRITTI=100;
	private String titolo;
	private String docente;
	private int cod;
	private Studente studenti[]=new Studente[MAXISCRITTI];
	private Esame esami[]=new Esame[MAXISCRITTI];
	
	public Corso(String titolo,String docente,int cod){
		this.titolo=titolo;
		this.docente=docente;
		this.cod=cod;
	}

	
	public String getTitolo() {
		return titolo;
	}
	public String getDocente() {
		return docente;
	}
	public int getCod() {
		return cod;
	}
	public String getCorso() { // l'esercitatore l'ha chiamato toString con Override
		return getCod() +"," + getTitolo()+ "," + getDocente();
	}
	public void iscrizione(Studente studente) {
		for(int i=0;i<studenti.length;i++) {
			//uno studente non può essere iscritto 2 volte allo stesso corso
			if(studenti[i]==studente) return;
			//lo metto al primo posto libero (non è prevista l'elimiazione)
			if(studenti[i]==null) {
				studenti[i]=studente;
				return;
			}
		}
		System.out.println("Corso pieno.");
	}
	public String printIscritti() {
		StringBuffer sb=new StringBuffer();
		for(int i=0;studenti[i]!=null;i++) {
			sb.append(studenti[i].getStudente()+"\n");
		}
		return sb.toString();
	}
	
	public void regEsame(Esame e) {
		for (int i=0;i<esami.length;i++) {
			if(e.equals(esami[i])) {//controllo se l'esame c'era gia e lo sovrascrivo
				esami[i]=e;
			}
			if(esami[i]==null) {
				esami[i]=e;
				return;
			}
		}
	}
	
	public float avg() {
		if(esami[0]==null) {
			return -1;
		}
		float avg=0;
		int i;
		for(i=0;i<esami.length;i++)
			if(esami[i]==null)
				break;
			else
				avg+=esami[i].getVoto();
		return avg/i;
	}
	
}
